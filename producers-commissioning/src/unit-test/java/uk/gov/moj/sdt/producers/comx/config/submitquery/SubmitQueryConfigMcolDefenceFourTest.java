package uk.gov.moj.sdt.producers.comx.config.submitquery;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.SubmitQueryRequest;
import uk.gov.moj.sdt.domain.api.IErrorLog;

class SubmitQueryConfigMcolDefenceFourTest extends SubmitQueryConfigTestBase {

    private SubmitQueryConfigMcolDefenceFour submitQueryConfigMcolDefenceFour;

    @Override
    protected void setUpLocalTests() {
        submitQueryConfigMcolDefenceFour = new SubmitQueryConfigMcolDefenceFour();
    }

    @Test
    void testSubmitQueryRequest() {
        SubmitQueryRequest submitQueryRequest = submitQueryConfigMcolDefenceFour.submitQueryRequest();

        IErrorLog expectedErrorLog = new ErrorLog();
        expectedErrorLog.setErrorCode("1");
        expectedErrorLog.setErrorText("Unknown MCOL customer number specified.");

        assertSubmitQueryRequest(submitQueryRequest,
                                 0,
                                 STATUS_ERROR,
                                 null,
                                 expectedErrorLog);
    }
}
