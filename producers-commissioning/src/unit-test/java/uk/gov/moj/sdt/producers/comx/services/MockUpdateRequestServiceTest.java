package uk.gov.moj.sdt.producers.comx.services;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.domain.BulkSubmission;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.IndividualRequest;
import uk.gov.moj.sdt.domain.api.IBulkSubmission;
import uk.gov.moj.sdt.domain.api.IErrorLog;
import uk.gov.moj.sdt.domain.api.IIndividualRequest;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockUpdateRequestServiceTest extends AbstractSdtUnitTestBase {

    private static final long BULK_SUBMISSION_ID = 1L;

    private static final String ERROR_LOG_CODE = "3";

    private static final long INDIVIDUAL_REQ_ID = 2L;
    private static final String CUST_REQ_REF = "CustReqRef";
    private static final String REQ_STATUS = "ReqStatus";
    private static final String SDT_BULK_REF = "SdtBulkRef";
    private static final int LINE_NUM = 4;
    private static final String SDT_REQ_REF = "SdtReqRef";
    private static final int FORWARDING_ATTEMPTS = 1;
    private static final String TARGET_APP_RESP = "TargetAppResponse";
    private static final String REQ_PAYLOAD = "ReqPayload";
    private static final String INTERNAL_SYS_ERROR = "InternalSysError";
    private static final String REQ_TYPE = "ReqType";

    private MockUpdateRequestService mockUpdateRequestService;

    @Override
    protected void setUpLocalTests() {
        mockUpdateRequestService = new MockUpdateRequestService();
    }

    @Test
    void testUpdateIndividualRequest() {
        LocalDateTime currentDate = LocalDateTime.now();
        IIndividualRequest individualRequest = createIndividualRequest(currentDate);

        mockUpdateRequestService.updateIndividualRequest(individualRequest);

        // updateIndividualRequest() has no implementation so just check that individual request hasn't been changed
        assertIndividualRequest(individualRequest, currentDate);
    }

    private IIndividualRequest createIndividualRequest(LocalDateTime currentDate) {
        IBulkSubmission bulkSubmission = new BulkSubmission();
        bulkSubmission.setId(BULK_SUBMISSION_ID);

        IErrorLog errorLog = new ErrorLog();
        errorLog.setErrorCode(ERROR_LOG_CODE);

        IIndividualRequest individualRequest = new IndividualRequest();
        individualRequest.setId(INDIVIDUAL_REQ_ID);
        individualRequest.setBulkSubmission(bulkSubmission);
        individualRequest.setCustomerRequestReference(CUST_REQ_REF);
        individualRequest.setRequestStatus(REQ_STATUS);
        individualRequest.setSdtBulkReference(SDT_BULK_REF);
        individualRequest.setLineNumber(LINE_NUM);
        individualRequest.setSdtRequestReference(SDT_REQ_REF);
        individualRequest.setCreatedDate(currentDate);
        individualRequest.setUpdatedDate(currentDate);
        individualRequest.setCompletedDate(currentDate);
        individualRequest.setForwardingAttempts(FORWARDING_ATTEMPTS);
        individualRequest.setTargetApplicationResponse(TARGET_APP_RESP.getBytes(StandardCharsets.UTF_8));
        individualRequest.setErrorLog(errorLog);
        individualRequest.setRequestPayload(REQ_PAYLOAD.getBytes(StandardCharsets.UTF_8));
        individualRequest.setInternalSystemError(INTERNAL_SYS_ERROR);
        individualRequest.setRequestType(REQ_TYPE);
        individualRequest.setDeadLetter(true);

        return individualRequest;
    }

    private void assertIndividualRequest(IIndividualRequest individualRequest, LocalDateTime currentDate) {
        assertNotNull(individualRequest, "IndividualRequest should not be null");
        assertEquals(INDIVIDUAL_REQ_ID, individualRequest.getId(), "IndividualRequest has unexpected id");

        IBulkSubmission bulkSubmission = individualRequest.getBulkSubmission();
        assertNotNull(bulkSubmission, "BulkSubmission should not be null");
        assertEquals(BULK_SUBMISSION_ID, bulkSubmission.getId(), "BulkSubmission has unexpected id");

        assertEquals(CUST_REQ_REF,
                     individualRequest.getCustomerRequestReference(),
                     "IndividualRequest has unexpected customer request ref");
        assertEquals(REQ_STATUS,
                     individualRequest.getRequestStatus(),
                     "IndividualRequest has unexpected request status");
        assertEquals(SDT_BULK_REF,
                     individualRequest.getSdtBulkReference(),
                     "IndividualRequest has unexpected SDT bulk reference");
        assertEquals(LINE_NUM,
                     individualRequest.getLineNumber(),
                     "IndividualRequest has unexpected line number");
        assertEquals(SDT_REQ_REF,
                     individualRequest.getSdtRequestReference(),
                     "IndividualRequest has unexpected SDT request reference");
        assertEquals(currentDate,
                     individualRequest.getCreatedDate(),
                     "IndividualRequest has unexpected created date");
        assertEquals(currentDate,
                     individualRequest.getUpdatedDate(),
                     "IndividualRequest has unexpected updated date");
        assertEquals(currentDate,
                     individualRequest.getCompletedDate(),
                     "IndividualRequest has unexpected completed date");
        assertEquals(FORWARDING_ATTEMPTS,
                     individualRequest.getForwardingAttempts(),
                     "IndividualRequest has unexpected number of forwarding attempts");
        assertEquals(TARGET_APP_RESP,
                     new String(individualRequest.getTargetApplicationResponse(), StandardCharsets.UTF_8),
                     "IndividualRequest has unexpected target application response");

        IErrorLog errorLog = individualRequest.getErrorLog();
        assertNotNull(errorLog, "ErrorLog should not be null");
        assertEquals(ERROR_LOG_CODE, errorLog.getErrorCode(), "ErrorLog has unexpected error code");

        assertEquals(REQ_PAYLOAD,
                     new String(individualRequest.getRequestPayload(), StandardCharsets.UTF_8),
                     "IndividualRequest has unexpected request payload");
        assertEquals(INTERNAL_SYS_ERROR,
                     individualRequest.getInternalSystemError(),
                     "IndividualRequest has unexpected internal system error");
        assertEquals(REQ_TYPE,
                     individualRequest.getRequestType(),
                     "IndividualRequest has unexpected request type");
        assertTrue(individualRequest.isDeadLetter(), "IndividualRequest dead letter flag should be true");
    }
}
