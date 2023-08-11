package uk.gov.moj.sdt.producers.comx.config.submitquery;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.SubmitQueryRequest;
import uk.gov.moj.sdt.domain.api.IErrorLog;

class SubmitQueryConfigMcolDefenceSixTest extends SubmitQueryConfigTestBase {

    private SubmitQueryConfigMcolDefenceSix submitQueryConfigMcolDefenceSix;

    @Override
    protected void setUpLocalTests() {
        submitQueryConfigMcolDefenceSix = new SubmitQueryConfigMcolDefenceSix();
    }

    @Test
    void testSubmitQueryRequest() {
        SubmitQueryRequest submitQueryRequest = submitQueryConfigMcolDefenceSix.submitQueryRequest();

        IErrorLog expectedErrorLog = new ErrorLog();
        expectedErrorLog.setErrorCode("74");
        expectedErrorLog.setErrorText("To Date and Time must be later than From Date and Time.");

        assertSubmitQueryRequest(submitQueryRequest,
                                 0,
                                 STATUS_ERROR,
                                 null,
                                 expectedErrorLog);
    }
}
