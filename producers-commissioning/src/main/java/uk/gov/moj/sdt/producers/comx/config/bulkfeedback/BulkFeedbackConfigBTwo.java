package uk.gov.moj.sdt.producers.comx.config.bulkfeedback;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.moj.sdt.domain.BulkSubmission;
import uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactory;

@Configuration
@EnableAutoConfiguration
public class BulkFeedbackConfigBTwo {

    @Bean("createIndividualRequestB0000002")
    public void invokingBean() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetObject(bulkFeedbackFactory());
        methodInvokingFactoryBean.setTargetMethod("createIndividualRequests");

        methodInvokingFactoryBean.setArguments(Lists.newArrayList(
            Lists.newArrayList(
                "USER_REQUEST_ID_B1a",
                "mcolClaim",
                "Rejected",
                "DUP_CUST_REQID",
                "Duplicate User Request Identifier submitted USER_REQUEST_ID_B1a.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B1b",
                "mcolJudgment",
                "Rejected",
                "DUPLD_CUST_REQID",
                "Unique Request Identifier has been specified more than once within the originating Bulk Request.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B1b",
                "mcolJudgment",
                "Rejected",
                "DUPLD_CUST_REQID",
                "Unique Request Identifier has been specified more than once within the originating Bulk Request.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B2",
                "mcolClaim",
                "Rejected",
                "8",
                "First defendant's postcode is not in England or Wales.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B3",
                "mcolJudgment",
                "Rejected",
                "75",
                "Rejected by CCBC - rejection number 6.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B4",
                "mcolClaimStatusUpdate",
                "Accepted",
                "",
                "",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B5",
                "mcolJudgment",
                "Rejected",
                "24",
                "This judgment request is invalid on the referenced claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B6",
                "mcolWarrant",
                "Accepted",
                "",
                "",
                """
                  <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                      <mresp:issueDate>2014-01-23</mresp:issueDate>
                      <mresp:warrantNumber>0Z000150</mresp:warrantNumber>
                      <mresp:enforcingCourtCode>127</mresp:enforcingCourtCode>
                      <mresp:enforcingCourtName>BIRMINGHAM</mresp:enforcingCourtName>
                      <mresp:fee>10000</mresp:fee>
                      <mresp:judgmentWarrantStatus>Warrant accepted by CCBC</mresp:judgmentWarrantStatus>
                  </ind:mcolResponseDetail>
                 """
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B7",
                "mcolJudgmentWarrant",
                "Accepted",
                "",
                "",
                """
                 <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                     <mresp:issueDate>2014-01-23</mresp:issueDate>
                     <mresp:judgmentEnteredDate>2014-01-23</mresp:judgmentEnteredDate>
                     <mresp:firstPaymentDate>2014-03-03</mresp:firstPaymentDate>
                     <mresp:warrantNumber>0Z000151</mresp:warrantNumber>
                     <mresp:enforcingCourtCode>127</mresp:enforcingCourtCode>
                     <mresp:enforcingCourtName>BIRMINGHAM</mresp:enforcingCourtName>
                     <mresp:fee>10000</mresp:fee>
                     <mresp:judgmentWarrantStatus>Judgment accepted by CCBC. Warrant accepted by CCBC</mresp:judgmentWarrantStatus>
                 </ind:mcolResponseDetail>
                 """
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B8",
                "mcolClaimStatusUpdate",
                "Rejected",
                "67",
                "This claim status update is invalid on the referenced claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B9",
                "mcolWarrant",
                "Rejected",
                "28",
                "Defendant 2 is specified but there is only 1 defendant on the claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B10",
                "mcolJudgmentWarrant",
                "Rejected",
                "24",
                "This judgment request is invalid on the referenced claim.",
                """
                  <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                      <mresp:judgmentWarrantStatus>Judgment Request error</mresp:judgmentWarrantStatus>
                  </ind:mcolResponseDetail>
                 """
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B11",
                "mcolClaim",
                "Accepted",
                "",
                "",
                """
                 <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                     <mresp:claimNumber>A0ZZ0045</mresp:claimNumber>
                     <mresp:issueDate>2014-01-23</mresp:issueDate>
                     <mresp:serviceDate>2014-01-28</mresp:serviceDate>
                     <mresp:fee>21000</mresp:fee>
                 </ind:mcolResponseDetail>
                 """
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B12",
                "mcolWarrant",
                "Accepted",
                "",
                "",
                """
                  <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                      <mresp:issueDate>2014-01-23</mresp:issueDate>
                      <mresp:warrantNumber>0Z000152</mresp:warrantNumber>
                      <mresp:enforcingCourtCode>127</mresp:enforcingCourtCode>
                      <mresp:enforcingCourtName>BIRMINGHAM</mresp:enforcingCourtName>
                      <mresp:fee>10000</mresp:fee>
                      <mresp:judgmentWarrantStatus>Warrant accepted by CCBC</mresp:judgmentWarrantStatus>
                  </ind:mcolResponseDetail>
                 """
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B13",
                "mcolJudgment",
                "Accepted",
                "",
                "",
                """
                  <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                      <mresp:judgmentEnteredDate>2014-01-23</mresp:judgmentEnteredDate>
                      <mresp:firstPaymentDate>2014-02-21</mresp:firstPaymentDate>
                      <mresp:judgmentWarrantStatus>Judgment accepted by CCBC</mresp:judgmentWarrantStatus>
                  </ind:mcolResponseDetail>
                """
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B14",
                "mcolClaim",
                "Accepted",
                "",
                "",
                """
                   <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                       <mresp:claimNumber>A0ZZ0046</mresp:claimNumber>
                       <mresp:issueDate>2014-01-23</mresp:issueDate>
                       <mresp:serviceDate>2014-01-28</mresp:serviceDate>
                       <mresp:fee>34000</mresp:fee>
                   </ind:mcolResponseDetail>
                 """
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B15",
                "mcolJudgment",
                "Rejected",
                "CUST_XML_ERR",
                "Individual Request format could not be processed by the Target Application. Please check the data and resubmit the request, or  contact 'SDT Contact Details' for assistance.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B16",
                "mcolSetAside",
                "Accepted",
                "",
                "",
                """
                   <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                       <mresp:issueDate>2014-01-23</mresp:issueDate>
                       <mresp:fee>5000</mresp:fee>
                   </ind:mcolResponseDetail>
                 """
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_B17",
                "mcolBreathingSpace",
                "Accepted",
                "",
                "",
                ""
            )
        ));
        methodInvokingFactoryBean.prepare();
        methodInvokingFactoryBean.invoke();
    }

    @Bean("uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactoryB00000002")
    public BulkFeedbackFactory bulkFeedbackFactory() {
        return new BulkFeedbackFactory(bulkSubmissionB00000001());
    }

    private BulkSubmission bulkSubmissionB00000001() {
        BulkSubmission bulkSubmission = new BulkSubmission();
        bulkSubmission.setCustomerReference("USER_FILE_REFERENCE_B2");
        bulkSubmission.setNumberOfRequest(19);
        bulkSubmission.setSubmissionStatus("Completed");
        bulkSubmission.setCreatedDate(LocalDateTime.of(2014, 1, 22, 13, 0));
        return bulkSubmission;
    }
}
