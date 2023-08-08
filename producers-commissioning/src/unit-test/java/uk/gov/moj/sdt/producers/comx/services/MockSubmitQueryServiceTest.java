package uk.gov.moj.sdt.producers.comx.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.SubmitQueryRequest;
import uk.gov.moj.sdt.domain.api.IErrorLog;
import uk.gov.moj.sdt.domain.api.ISubmitQueryRequest;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;
import uk.gov.moj.sdt.utils.SdtContext;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static uk.gov.moj.sdt.domain.api.ISubmitQueryRequest.Status.ERROR;

@ExtendWith(MockitoExtension.class)
class MockSubmitQueryServiceTest extends AbstractSdtUnitTestBase {

    private static final String QUERY_REF_1 = "MCOLDefence1";
    private static final String ERROR_CODE_1 = "1";
    private static final String ERROR_TEXT_1 = "Error1";
    private static final String TARGET_APP_RESP_1 = "TargetAppResponse1";

    private static final String QUERY_REF_2 = "MCOLDefence2";
    private static final String ERROR_CODE_2 = "2";
    private static final String ERROR_TEXT_2 = "Error2";
    private static final String TARGET_APP_RESP_2 = "TargetAppResponse2";

    private static final String QUERY_REF_3 = "MCOLDefence3";
    private static final String ERROR_CODE_3 = "3";
    private static final String ERROR_TEXT_3 = "Error3";
    private static final String TARGET_APP_RESP_3 = "TargetAppResponse3";

    private static final String QUERY_REF_4 = "MCOLDefence4";
    private static final String ERROR_CODE_4 = "4";
    private static final String ERROR_TEXT_4 = "Error4";
    private static final String TARGET_APP_RESP_4 = "TargetAppResponse4";

    private static final String QUERY_REF_5 = "MCOLDefence5";
    private static final String ERROR_CODE_5 = "5";
    private static final String ERROR_TEXT_5 = "Error5";
    private static final String TARGET_APP_RESP_5 = "TargetAppResponse5";

    private static final String QUERY_REF_6 = "MCOLDefence6";
    private static final String ERROR_CODE_6 = "6";
    private static final String ERROR_TEXT_6 = "Error6";
    private static final String TARGET_APP_RESP_6 = "TargetAppResponse6";

    private static final String TEST_QUERY_REF = "TestQueryRef";

    @Mock
    private SdtContext mockSdtContext;

    private MockSubmitQueryService mockSubmitQueryService;

    @Override
    protected void setUpLocalTests() {
        SubmitQueryRequest mcolDefSubmitQuery1 =
            createSubmitQueryRequest(QUERY_REF_1, 1, ERROR.getStatus(),
                                     ERROR_CODE_1, ERROR_TEXT_1, TARGET_APP_RESP_1);
        SubmitQueryRequest mcolDefSubmitQuery2 =
            createSubmitQueryRequest(QUERY_REF_2, 2, ERROR.getStatus(),
                                     ERROR_CODE_2, ERROR_TEXT_2, TARGET_APP_RESP_2);
        SubmitQueryRequest mcolDefSubmitQuery3 =
            createSubmitQueryRequest(QUERY_REF_3, 3, ERROR.getStatus(),
                                     ERROR_CODE_3, ERROR_TEXT_3, TARGET_APP_RESP_3);
        SubmitQueryRequest mcolDefSubmitQuery4 =
            createSubmitQueryRequest(QUERY_REF_4, 4, ERROR.getStatus(),
                                     ERROR_CODE_4, ERROR_TEXT_4, TARGET_APP_RESP_4);
        SubmitQueryRequest mcolDefSubmitQuery5 =
            createSubmitQueryRequest(QUERY_REF_5, 5, ERROR.getStatus(),
                                     ERROR_CODE_5, ERROR_TEXT_5, TARGET_APP_RESP_5);
        SubmitQueryRequest mcolDefSubmitQuery6 =
            createSubmitQueryRequest(QUERY_REF_6, 6, ERROR.getStatus(),
                                     ERROR_CODE_6, ERROR_TEXT_6, TARGET_APP_RESP_6);

        mockSubmitQueryService =
            new MockSubmitQueryService(mcolDefSubmitQuery1, mcolDefSubmitQuery2, mcolDefSubmitQuery3,
                                       mcolDefSubmitQuery4, mcolDefSubmitQuery5, mcolDefSubmitQuery6);
    }

    @ParameterizedTest
    @MethodSource("submitQueryRequests")
    void testSubmitQuery(String queryReference,
                         int resultCount,
                         String status,
                         String errorCode,
                         String errorText,
                         String targetAppResponse) {
        ISubmitQueryRequest submitQueryRequest = new SubmitQueryRequest();
        submitQueryRequest.setQueryReference(queryReference);

        try (MockedStatic<SdtContext>mockStaticSdtContext = mockStatic(SdtContext.class)) {
            mockStaticSdtContext.when(SdtContext::getContext).thenReturn(mockSdtContext);

            mockSubmitQueryService.submitQuery(submitQueryRequest);

            assertSubmitQueryRequest(submitQueryRequest, resultCount, status, errorCode, errorText, targetAppResponse);

            mockStaticSdtContext.verify(SdtContext::getContext);
            verify(mockSdtContext).setRawOutXml(targetAppResponse);
        }
    }

    @Test
    void testSubmitQueryDefault() {
        ISubmitQueryRequest submitQueryRequest = new SubmitQueryRequest();
        submitQueryRequest.setQueryReference("does not exist");

        try (MockedStatic<SdtContext>mockStaticSdtContext = mockStatic(SdtContext.class)) {
            mockStaticSdtContext.when(SdtContext::getContext).thenReturn(mockSdtContext);

            mockSubmitQueryService.submitQuery(submitQueryRequest);

            assertSubmitQueryRequest(submitQueryRequest, 1, ERROR.getStatus(),
                                     ERROR_CODE_1, ERROR_TEXT_1, TARGET_APP_RESP_1);

            mockStaticSdtContext.verify(SdtContext::getContext);
            verify(mockSdtContext).setRawOutXml(TARGET_APP_RESP_1);
        }
    }

    @Test
    void testSubmitQueryNullTargetAppResponse() {
        SubmitQueryRequest mcolDefSubmitQuery2 =
            createSubmitQueryRequest(QUERY_REF_2, 2, ERROR.getStatus(),
                                     ERROR_CODE_2, ERROR_TEXT_2, null);

        Map<String, SubmitQueryRequest> submitQueryRequestMap = new HashMap<>();
        submitQueryRequestMap.put(QUERY_REF_2, mcolDefSubmitQuery2);

        mockSubmitQueryService.setResponseContentMap(submitQueryRequestMap);

        ISubmitQueryRequest submitQueryRequest = new SubmitQueryRequest();
        submitQueryRequest.setQueryReference(QUERY_REF_2);

        try (MockedStatic<SdtContext>mockStaticSdtContext = mockStatic(SdtContext.class)) {

            mockSubmitQueryService.submitQuery(submitQueryRequest);

            assertSubmitQueryRequest(submitQueryRequest, 2, ERROR.getStatus(),
                                     ERROR_CODE_2, ERROR_TEXT_2, null);

            mockStaticSdtContext.verify(SdtContext::getContext, never());
        }
    }

    @Test
    void testResponseMap() {
        SubmitQueryRequest submitQueryRequest = new SubmitQueryRequest();
        submitQueryRequest.setQueryReference(TEST_QUERY_REF);

        Map<String, SubmitQueryRequest> submitQueryRequestMap = new HashMap<>();
        submitQueryRequestMap.put(TEST_QUERY_REF, submitQueryRequest);

        mockSubmitQueryService.setResponseContentMap(submitQueryRequestMap);
        Map<String, SubmitQueryRequest> returnedResponseContentMap = mockSubmitQueryService.getResponseContentMap();

        assertNotNull(returnedResponseContentMap, "Response content map should not be null");
        assertEquals(1, returnedResponseContentMap.size(),
                     "Response content map has unexpected number of entries");

        SubmitQueryRequest returnedSubmitQueryRequest = returnedResponseContentMap.get(TEST_QUERY_REF);
        assertNotNull(returnedSubmitQueryRequest, "SubmitQueryRequest should not be null");
        assertEquals(TEST_QUERY_REF, returnedSubmitQueryRequest.getQueryReference(),
                     "SubmitQueryRequest has unexpected query reference");
    }

    private SubmitQueryRequest createSubmitQueryRequest(String queryRef,
                                                        int resultCount,
                                                        String status,
                                                        String errorCode,
                                                        String errorText,
                                                        String targetAppResponse) {

        IErrorLog errorLog = new ErrorLog();
        errorLog.setErrorCode(errorCode);
        errorLog.setErrorText(errorText);

        SubmitQueryRequest submitQueryRequest = new SubmitQueryRequest();
        submitQueryRequest.setQueryReference(queryRef);
        submitQueryRequest.setResultCount(resultCount);
        submitQueryRequest.setStatus(status);
        submitQueryRequest.setErrorLog(errorLog);
        submitQueryRequest.setTargetApplicationResponse(targetAppResponse);

        return submitQueryRequest;
    }

    private void assertSubmitQueryRequest(ISubmitQueryRequest submitQueryRequest,
                                          int resultCount,
                                          String status,
                                          String errorCode,
                                          String errorText,
                                          String targetAppResponse) {

        assertEquals(resultCount, submitQueryRequest.getResultCount(),
                     "SubmitQueryRequest has unexpected result count");
        assertEquals(status, submitQueryRequest.getStatus(),
                     "SubmitQueryRequest has unexpected status");

        IErrorLog errorLog = submitQueryRequest.getErrorLog();
        assertEquals(errorCode, errorLog.getErrorCode(),
                     "SubmitQueryRequest ErrorLog has unexpected code");
        assertEquals(errorText, errorLog.getErrorText(),
                     "SubmitQueryRequest ErrorLog has unexpected text");

        if (targetAppResponse == null) {
            assertNull(submitQueryRequest.getTargetApplicationResponse(),
                       "SubmitQueryRequest target application response should be null");

        } else {
            assertEquals(targetAppResponse, submitQueryRequest.getTargetApplicationResponse(),
                         "SubmitQueryRequest has unexpected target application response");
        }
    }

    static Stream<Arguments> submitQueryRequests() {
        return Stream.of(
            arguments(QUERY_REF_1, 1, ERROR.getStatus(), ERROR_CODE_1, ERROR_TEXT_1, TARGET_APP_RESP_1),
            arguments(QUERY_REF_2, 2, ERROR.getStatus(), ERROR_CODE_2, ERROR_TEXT_2, TARGET_APP_RESP_2),
            arguments(QUERY_REF_3, 3, ERROR.getStatus(), ERROR_CODE_3, ERROR_TEXT_3, TARGET_APP_RESP_3),
            arguments(QUERY_REF_4, 4, ERROR.getStatus(), ERROR_CODE_4, ERROR_TEXT_4, TARGET_APP_RESP_4),
            arguments(QUERY_REF_5, 5, ERROR.getStatus(), ERROR_CODE_5, ERROR_TEXT_5, TARGET_APP_RESP_5),
            arguments(QUERY_REF_6, 6, ERROR.getStatus(), ERROR_CODE_6, ERROR_TEXT_6, TARGET_APP_RESP_6)
        );
    }
}
