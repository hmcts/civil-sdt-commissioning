package uk.gov.moj.sdt.producers.comx.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.moj.sdt.domain.BulkSubmission;
import uk.gov.moj.sdt.domain.api.IBulkSubmission;
import uk.gov.moj.sdt.domain.api.IErrorLog;
import uk.gov.moj.sdt.domain.api.IIndividualRequest;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static uk.gov.moj.sdt.domain.api.IIndividualRequest.IndividualRequestStatus.ACCEPTED;
import static uk.gov.moj.sdt.domain.api.IIndividualRequest.IndividualRequestStatus.FORWARDED;
import static uk.gov.moj.sdt.domain.api.IIndividualRequest.IndividualRequestStatus.RECEIVED;
import static uk.gov.moj.sdt.domain.api.IIndividualRequest.IndividualRequestStatus.REJECTED;

class BulkFeedbackFactoryTest extends AbstractSdtUnitTestBase {

    private static final String CUST_REQ_REF_ONE = "12345678-1";
    private static final String CUST_REQ_REF_TWO = "12345678-2";
    private static final String REQ_TYPE_MCOL_CLAIM = "mcolClaim";
    private static final String REQ_TYPE_MCOL_JUDGMENT = "mcolJudgment";
    private static final String ERROR_CODE = "99";
    private static final String ERROR_CODE_NONE = "";
    private static final String ERROR_TEXT = "Error Text";
    private static final String ERROR_TEXT_NONE = "";
    private static final String TARGET_APP_RESPONSE = "Target application response";
    private static final String TARGET_APP_RESPONSE_NONE = "";
    private static final String SDT_BULK_REFERENCE = "SDT bulk reference";

    private static final String ERROR_BULK_SUBMISSION_NULL = "BulkSubmission should not be null";
    private static final String ERROR_UNEXPECTED_NUM_REQUESTS =
            "BulkSubmission has unexpected number of individual requests";

    private BulkFeedbackFactory bulkFeedbackFactory;

    @Override
    protected void setUpLocalTests() {
        BulkSubmission bulkSubmission = new BulkSubmission();
        bulkFeedbackFactory = new BulkFeedbackFactory(bulkSubmission);
    }

    @Test
    void testCreateIndividualRequest() {
        bulkFeedbackFactory.createIndividualRequest(CUST_REQ_REF_ONE,
                                                    REQ_TYPE_MCOL_CLAIM,
                                                    RECEIVED.getStatus(),
                                                    ERROR_CODE_NONE,
                                                    ERROR_TEXT_NONE,
                                                    TARGET_APP_RESPONSE_NONE);

        IBulkSubmission bulkSubmission = bulkFeedbackFactory.getBulkSubmission();
        assertNotNull(bulkSubmission, ERROR_BULK_SUBMISSION_NULL);

        List<IIndividualRequest> individualRequests = bulkSubmission.getIndividualRequests();
        assertEquals(1, individualRequests.size(), ERROR_UNEXPECTED_NUM_REQUESTS);

        IIndividualRequest individualRequest = individualRequests.get(0);
        assertIndividualRequest(individualRequest, CUST_REQ_REF_ONE, REQ_TYPE_MCOL_CLAIM, RECEIVED.getStatus());

        assertNull(individualRequest.getErrorLog(), "IndividualRequest should not have an error log");

        assertEquals(0,
                     bulkFeedbackFactory.getTargetResponseMap().size(),
                     "TargetResponseMap should be empty");
    }

    @ParameterizedTest
    @MethodSource("rejectionCodeAndReason")
    void testCreateIndividualRequestWithRejection(String rejectCode, String rejectReason, boolean rejectionCreated) {
        bulkFeedbackFactory.createIndividualRequest(CUST_REQ_REF_ONE,
                                                    REQ_TYPE_MCOL_CLAIM,
                                                    REJECTED.getStatus(),
                                                    rejectCode,
                                                    rejectReason,
                                                    TARGET_APP_RESPONSE_NONE);

        IBulkSubmission bulkSubmission = bulkFeedbackFactory.getBulkSubmission();
        assertNotNull(bulkSubmission, ERROR_BULK_SUBMISSION_NULL);

        List<IIndividualRequest> individualRequests = bulkSubmission.getIndividualRequests();
        assertEquals(1, individualRequests.size(), ERROR_UNEXPECTED_NUM_REQUESTS);

        IIndividualRequest individualRequest = individualRequests.get(0);
        IErrorLog errorLog = individualRequest.getErrorLog();

        if (rejectionCreated) {
            assertNotNull(errorLog, "ErrorLog should not be null");
            assertEquals(rejectCode, errorLog.getErrorCode(), "ErrorLog has unexpected error code");
            assertEquals(rejectReason, errorLog.getErrorText(), "ErrorLog has unexpected error text");
        } else {
            assertNull(errorLog, "ErrorLog should be null");
        }
    }

    @Test
    void testCreateIndividualRequestWithTargetResponse() {
        bulkFeedbackFactory.createIndividualRequest(CUST_REQ_REF_ONE,
                                                    REQ_TYPE_MCOL_CLAIM,
                                                    ACCEPTED.getStatus(),
                                                    ERROR_CODE_NONE,
                                                    ERROR_TEXT_NONE,
                                                    TARGET_APP_RESPONSE);

        Map<String, String> targetResponseMap = bulkFeedbackFactory.getTargetResponseMap();
        assertEquals(1, targetResponseMap.size(), "TargetResponseMap has unexpected number of entries");

        String targetResponse = targetResponseMap.get(CUST_REQ_REF_ONE);
        assertNotNull(targetResponse, "Expected target response not found in TargetResponseMap");
        assertEquals(TARGET_APP_RESPONSE, targetResponse, "Target response has unexpected value");
    }

    @Test
    void testCreateIndividualRequests() {
        List<String> firstRequestParams =
                createIndividualRequestParams(CUST_REQ_REF_ONE,
                                              REQ_TYPE_MCOL_CLAIM,
                                              RECEIVED.getStatus());

        List<String> secondRequestParams =
                createIndividualRequestParams(CUST_REQ_REF_TWO,
                                              REQ_TYPE_MCOL_JUDGMENT,
                                              FORWARDED.getStatus());

        List<List<String>> params = new ArrayList<>();
        params.add(firstRequestParams);
        params.add(secondRequestParams);

        bulkFeedbackFactory.createIndividualRequests(params);

        IBulkSubmission bulkSubmission = bulkFeedbackFactory.getBulkSubmission();
        assertNotNull(bulkSubmission, ERROR_BULK_SUBMISSION_NULL);

        List<IIndividualRequest> individualRequests = bulkSubmission.getIndividualRequests();
        assertEquals(2, individualRequests.size(), ERROR_UNEXPECTED_NUM_REQUESTS);

        assertIndividualRequest(individualRequests.get(0),
                                CUST_REQ_REF_ONE,
                                REQ_TYPE_MCOL_CLAIM,
                                RECEIVED.getStatus());
        assertIndividualRequest(individualRequests.get(1),
                                CUST_REQ_REF_TWO,
                                REQ_TYPE_MCOL_JUDGMENT,
                                FORWARDED.getStatus());
    }

    @Test
    void testSetBulkSubmission() {
        IBulkSubmission bulkSubmission = new BulkSubmission();
        bulkSubmission.setSdtBulkReference(SDT_BULK_REFERENCE);

        bulkFeedbackFactory.setBulkSubmission(bulkSubmission);

        IBulkSubmission returnedBulkSubmission = bulkFeedbackFactory.getBulkSubmission();
        assertNotNull(returnedBulkSubmission, ERROR_BULK_SUBMISSION_NULL);
        assertEquals(SDT_BULK_REFERENCE,
                returnedBulkSubmission.getSdtBulkReference(),
                "Unexpected BulkSubmission returned");
    }

    private void assertIndividualRequest(IIndividualRequest individualRequest,
                                         String custReqRef,
                                         String reqType,
                                         String reqStatus) {
        assertEquals(custReqRef,
                individualRequest.getCustomerRequestReference(),
                "IndividualRequest has unexpected customer request reference");
        assertEquals(reqType,
                individualRequest.getRequestType(),
                "IndividualRequest has unexpected request type");
        assertEquals(reqStatus,
                individualRequest.getRequestStatus(),
                "IndividualRequest has unexpected request status");
    }

    private List<String> createIndividualRequestParams(String custReqRef, String reqType, String reqStatus) {
        List<String> requestParams = new ArrayList<>();

        requestParams.add(custReqRef);
        requestParams.add(reqType);
        requestParams.add(reqStatus);
        requestParams.add(ERROR_CODE_NONE);
        requestParams.add(ERROR_TEXT_NONE);
        requestParams.add(TARGET_APP_RESPONSE_NONE);

        return requestParams;
    }

    static Stream<Arguments> rejectionCodeAndReason() {
        return Stream.of(
                arguments(ERROR_CODE_NONE, ERROR_TEXT, false),
                arguments(ERROR_CODE, ERROR_TEXT_NONE, false),
                arguments(ERROR_CODE, ERROR_TEXT, true)
        );
    }
}
