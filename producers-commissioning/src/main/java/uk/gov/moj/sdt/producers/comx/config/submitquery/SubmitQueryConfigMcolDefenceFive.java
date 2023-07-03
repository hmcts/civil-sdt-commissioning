package uk.gov.moj.sdt.producers.comx.config.submitquery;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.SubmitQueryRequest;

@Configuration
@EnableAutoConfiguration
public class SubmitQueryConfigMcolDefenceFive {

    @Bean("uk.gov.moj.sdt.domain.api.ISubmitQueryRequestMCOLDefence5")
    public SubmitQueryRequest submitQueryRequest() {
        SubmitQueryRequest submitQueryRequest = new SubmitQueryRequest();
        submitQueryRequest.setResultCount(0);
        submitQueryRequest.setStatus("Error");
        submitQueryRequest.setErrorLog(errorLog());
        return submitQueryRequest;
    }

    private ErrorLog errorLog() {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setErrorCode("2");
        errorLog.setErrorText("MCOL customer number specified has not been set up for SDT use on MCOL.");
        return errorLog;
    }
}
