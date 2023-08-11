package uk.gov.moj.sdt.producers.comx.config.submitquery;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.SubmitQueryRequest;
import uk.gov.moj.sdt.domain.api.IErrorLog;

class SubmitQueryConfigMcolDefenceTwoTest extends SubmitQueryConfigTestBase {

    private SubmitQueryConfigMcolDefenceTwo submitQueryConfigMcolDefenceTwo;

    @Override
    protected void setUpLocalTests() {
        submitQueryConfigMcolDefenceTwo = new SubmitQueryConfigMcolDefenceTwo();
    }

    @Test
    void testSubmitQueryRequest() {
        SubmitQueryRequest submitQueryRequest = submitQueryConfigMcolDefenceTwo.submitQueryRequest();

        IErrorLog expectedErrorLog = new ErrorLog();
        expectedErrorLog.setErrorCode("77");
        expectedErrorLog.setErrorText("No defence notifications found for requested period.");

        assertSubmitQueryRequest(submitQueryRequest,
                                 0,
                                 STATUS_ERROR,
                                 null,
                                 expectedErrorLog);
    }
}
