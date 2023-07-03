package uk.gov.moj.sdt.producers.comx.config.bulkfeedback;

import java.time.LocalDateTime;

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.moj.sdt.domain.BulkSubmission;
import uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactory;

@Configuration
@EnableAutoConfiguration
public class BulkFeedbackConfigAOne {

    @Bean("createIndividualRequestA00000001")
    public void invokingBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetObject(bulkFeedbackFactory());
        methodInvokingFactoryBean.setTargetMethod("createIndividualRequest");
        methodInvokingFactoryBean.setArguments("USER_REQUEST_ID_A1", "mcolClaim", "Rejected", "DUP_CUST_REQID",
                                               "Duplicate User Request Identifier submitted USER_REQUEST_ID_A1.");
    }

    @Bean("uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactoryA00000001")
    public BulkFeedbackFactory bulkFeedbackFactory() {
        return new BulkFeedbackFactory(bulkSumissionA00000001());
    }

    private BulkSubmission bulkSumissionA00000001() {
        BulkSubmission bulkSubmission = new BulkSubmission();
        bulkSubmission.setCustomerReference("USER_FILE_REFERENCE_A1");
        bulkSubmission.setNumberOfRequest(1);
        bulkSubmission.setSubmissionStatus("Completed");
        bulkSubmission.setCreatedDate(LocalDateTime.of(2013, 7, 22, 13, 0));
        return bulkSubmission;
    }
}
