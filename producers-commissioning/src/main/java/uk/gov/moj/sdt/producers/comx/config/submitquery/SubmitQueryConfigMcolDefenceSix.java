package uk.gov.moj.sdt.producers.comx.config.submitquery;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.SubmitQueryRequest;

@Configuration
@EnableAutoConfiguration
public class SubmitQueryConfigMcolDefenceSix {

    @Bean("uk.gov.moj.sdt.domain.api.ISubmitQueryRequestMCOLDefence6")
    public SubmitQueryRequest submitQueryRequest() {
        SubmitQueryRequest submitQueryRequest = new SubmitQueryRequest();
        submitQueryRequest.setResultCount(0);
        submitQueryRequest.setStatus("Error");
        submitQueryRequest.setErrorLog(errorLog());
        return submitQueryRequest;
    }

    private ErrorLog errorLog() {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setErrorCode("74");
        errorLog.setErrorText("To Date and Time must be later than From Date and Time.");
        return errorLog;
    }
}
