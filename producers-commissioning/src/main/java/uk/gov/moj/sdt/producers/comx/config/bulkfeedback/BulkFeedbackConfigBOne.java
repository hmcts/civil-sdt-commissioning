package uk.gov.moj.sdt.producers.comx.config.bulkfeedback;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.moj.sdt.domain.BulkSubmission;
import uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactory;

@Configuration
@EnableAutoConfiguration
public class BulkFeedbackConfigBOne extends BulkFeedbackConfigBase {

    @Bean("createIndividualRequestB0000001")
    public void invokingBean()
        throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetObject(bulkFeedbackFactory());
        methodInvokingFactoryBean.setTargetMethod("createIndividualRequests");
        methodInvokingFactoryBean.setArguments(createIndividualRequestsList());
        methodInvokingFactoryBean.prepare();
        methodInvokingFactoryBean.invoke();
    }

    @Bean("uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactoryB00000001")
    public BulkFeedbackFactory bulkFeedbackFactory() {
        return new BulkFeedbackFactory(bulkSubmissionB00000001());
    }

    private BulkSubmission bulkSubmissionB00000001() {
        LocalDateTime createdDate = LocalDateTime.of(2014, 1, 22, 13, 0);
        return createBulkSubmission("USER_FILE_REFERENCE_B1", 20, BULK_SUBMISSION_STATUS_VALIDATED, createdDate);
    }

    private List<List<String>> createIndividualRequestsList() {
        List<List<String>> individualRequestsList = new ArrayList<>();

        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B1a",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "DUP_CUST_REQID",
                "Duplicate User Request Identifier submitted USER_REQUEST_ID_B1a.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B1b",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "DUPLD_CUST_REQID",
                "Unique Request Identifier has been specified more than once within the originating Bulk Request.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B1b",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "DUPLD_CUST_REQID",
                "Unique Request Identifier has been specified more than once within the originating Bulk Request.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B2",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "8",
                "First defendant's postcode is not in England or Wales.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B3",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_INITIALLY_ACCEPTED,
                "",
                "",
                """
                   <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                   <mresp:firstPaymentDate>2014-02-14</mresp:firstPaymentDate>
                   </ind:mcolResponseDetail>
                """
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B4",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_INITIALLY_ACCEPTED,
                "",
                "",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B5",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "24",
                "This judgment request is invalid on the referenced claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B6",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_INITIALLY_ACCEPTED,
                "",
                "",
                """
                  <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                  <mresp:warrantNumber>0Z000150</mresp:warrantNumber>
                  <mresp:enforcingCourtCode>127</mresp:enforcingCourtCode>
                  <mresp:enforcingCourtName>BIRMINGHAM</mresp:enforcingCourtName>
                  <mresp:fee>10000</mresp:fee>
                  </ind:mcolResponseDetail>
                """
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B7",
                REQUEST_TYPE_JUDGMENT_WARRANT,
                REQUEST_STATUS_INITIALLY_ACCEPTED,
                "",
                "",
                """
                 <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                    <mresp:firstPaymentDate>2014-03-03</mresp:firstPaymentDate>
                    <mresp:warrantNumber>0Z000151</mresp:warrantNumber>
                    <mresp:enforcingCourtCode>127</mresp:enforcingCourtCode>
                    <mresp:enforcingCourtName>BIRMINGHAM</mresp:enforcingCourtName>
                    <mresp:fee>10000</mresp:fee>
                 </ind:mcolResponseDetail>
                """
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B8",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "67",
                "This claim status update is invalid on the referenced claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B9",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "28",
                "Defendant 2 is specified but there is only 1 defendant on the claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B10",
                REQUEST_TYPE_JUDGMENT_WARRANT,
                REQUEST_STATUS_REJECTED,
                "24",
                "This judgment request is invalid on the referenced claim.",
                """
                  <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                      <mresp:judgmentWarrantStatus>Judgment Request error</mresp:judgmentWarrantStatus>
                  </ind:mcolResponseDetail>
                 """
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B11",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_INITIALLY_ACCEPTED,
                "",
                "",
                """
                 <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                     <mresp:claimNumber>A0ZZ0045</mresp:claimNumber>
                     <mresp:fee>21000</mresp:fee>
                 </ind:mcolResponseDetail>
                """
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B12",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_FORWARDED,
                "",
                "",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B13",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_AWAITING_DATA,
                "",
                "",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B14",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_RECEIVED,
                "",
                "",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B15",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_RECEIVED,
                "",
                "",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B16",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_INITIALLY_ACCEPTED,
                "",
                "",
                """
                  <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                  <mresp:fee>5000</mresp:fee>
                  </ind:mcolResponseDetail>
                 """
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B17",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_INITIALLY_ACCEPTED,
                "",
                "",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_B18",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_REJECTED,
                "INVALID_CMC_REQUEST",
                "Individual request USER_REQUEST_ID_B18 for CMC has an invalid request type mcolSetAside",
                ""
            )
        );

        return individualRequestsList;
    }
}
