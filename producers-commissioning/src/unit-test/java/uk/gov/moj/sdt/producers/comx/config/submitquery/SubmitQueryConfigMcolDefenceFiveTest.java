package uk.gov.moj.sdt.producers.comx.config.submitquery;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.SubmitQueryRequest;
import uk.gov.moj.sdt.domain.api.IErrorLog;

class SubmitQueryConfigMcolDefenceFiveTest extends SubmitQueryConfigTestBase {

    private SubmitQueryConfigMcolDefenceFive submitQueryConfigMcolDefenceFive;

    @Override
    protected void setUpLocalTests() {
        submitQueryConfigMcolDefenceFive = new SubmitQueryConfigMcolDefenceFive();
    }

    @Test
    void testSubmitQueryRequest() {
        SubmitQueryRequest submitQueryRequest = submitQueryConfigMcolDefenceFive.submitQueryRequest();

        IErrorLog expectedErrorLog = new ErrorLog();
        expectedErrorLog.setErrorCode("2");
        expectedErrorLog.setErrorText("MCOL customer number specified has not been set up for SDT use on MCOL.");

        assertSubmitQueryRequest(submitQueryRequest,
                                 0,
                                 STATUS_ERROR,
                                 null,
                                 expectedErrorLog);
    }
}
