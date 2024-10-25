package uk.gov.moj.sdt.producers.comx.config.bulkfeedback;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.moj.sdt.domain.BulkSubmission;
import uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactory;

@Configuration
@EnableAutoConfiguration
public class BulkFeedbackConfigAOne extends BulkFeedbackConfigBase {

    @Bean("createIndividualRequestA00000001")
    public void invokingBean()
        throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetObject(bulkFeedbackFactory());
        methodInvokingFactoryBean.setTargetMethod("createIndividualRequest");
        methodInvokingFactoryBean.setArguments("USER_REQUEST_ID_A1",
                                               REQUEST_TYPE_CLAIM,
                                               REQUEST_STATUS_REJECTED,
                                               "DUP_CUST_REQID",
                                               "Duplicate User Request Identifier submitted USER_REQUEST_ID_A1.",
                                               "");

        methodInvokingFactoryBean.prepare();
        methodInvokingFactoryBean.invoke();
    }

    @Bean("uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactoryA00000001")
    public BulkFeedbackFactory bulkFeedbackFactory() {
        return new BulkFeedbackFactory(bulkSubmissionA00000001());
    }

    private BulkSubmission bulkSubmissionA00000001() {
        LocalDateTime createdDate = LocalDateTime.of(2013, 7, 22, 13, 0);
        return createBulkSubmission("USER_FILE_REFERENCE_A1", 1, BULK_SUBMISSION_STATUS_COMPLETED, createdDate);
    }
}
