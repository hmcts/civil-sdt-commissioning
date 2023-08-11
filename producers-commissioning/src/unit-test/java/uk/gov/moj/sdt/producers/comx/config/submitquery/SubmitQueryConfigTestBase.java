package uk.gov.moj.sdt.producers.comx.config.submitquery;

import uk.gov.moj.sdt.domain.SubmitQueryRequest;
import uk.gov.moj.sdt.domain.api.IErrorLog;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SubmitQueryConfigTestBase extends AbstractSdtUnitTestBase {

    public static final String STATUS_OK = "Ok";
    public static final String STATUS_ERROR = "Error";

    public void assertSubmitQueryRequest(SubmitQueryRequest submitQueryRequest,
                                         int expectedResultCount,
                                         String expectedStatus,
                                         String expectedTargetAppResponse,
                                         IErrorLog expectedErrorLog) {
        assertNotNull(submitQueryRequest, "SubmitQueryRequest should not be null");

        assertEquals(expectedResultCount,
                     submitQueryRequest.getResultCount(),
                     "SubmitQueryRequest has unexpected result count");

        assertEquals(expectedStatus,
                     submitQueryRequest.getStatus(),
                     "SubmitQueryRequest has unexpected status");

        String actualTargetApplicationResponse = submitQueryRequest.getTargetApplicationResponse();
        if (expectedTargetAppResponse != null && !expectedTargetAppResponse.isEmpty()) {
            assertNotNull(actualTargetApplicationResponse,
                          "SubmitQueryRequest target application response should not be null");
            assertEquals(expectedTargetAppResponse,
                         actualTargetApplicationResponse,
                         "SubmitQueryRequest has unexpected target application response");
        } else {
            assertNull(actualTargetApplicationResponse,
                       "SubmitQueryRequest target application response should be null");
        }

        IErrorLog actualErrorLog = submitQueryRequest.getErrorLog();
        if (expectedErrorLog == null) {
            assertNull(actualErrorLog, "SubmitQueryRequest ErrorLog should be null");
        } else {
            assertNotNull(actualErrorLog, "SubmitQueryRequest ErrorLog should not be null");
            assertEquals(expectedErrorLog.getErrorCode(),
                         actualErrorLog.getErrorCode(),
                         "SubmitQueryRequest ErrorLog has unexpected error code");
            assertEquals(expectedErrorLog.getErrorText(),
                         actualErrorLog.getErrorText(),
                         "SubmitQueryRequest ErrorLog has unexpected error text");
        }
    }
}
