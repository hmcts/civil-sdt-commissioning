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
public class BulkFeedbackConfigCOne {

    @Bean("uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactoryC00000001")
    public BulkFeedbackFactory bulkFeedbackFactory() {
        return new BulkFeedbackFactory(bulkSubmissionC00000001());
    }

    private BulkSubmission bulkSubmissionC00000001() {
        BulkSubmission bulkSubmission = new BulkSubmission();
        bulkSubmission.setCustomerReference("USER_FILE_REFERENCE_C1");
        bulkSubmission.setNumberOfRequest(108);
        bulkSubmission.setSubmissionStatus("Completed");
        bulkSubmission.setCreatedDate(LocalDateTime.of(2014, 1, 22, 13, 0));
        return bulkSubmission;
    }

    @Bean("createIndividualRequestC0000001")
    public void invokingBean() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetObject(bulkFeedbackFactory());
        methodInvokingFactoryBean.setTargetMethod("createIndividualRequests");
        methodInvokingFactoryBean.setArguments(Lists.newArrayList(
            Lists.newArrayList(
                "USER_REQUEST_ID_c1",
                "mcolClaim",
                "Rejected",
                "1",
                "Unknown MCOL customer number specified.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c2",
                "mcolClaim",
                "Rejected",
                "2",
                "MCOL customer number specified has not been set up for SDT use on MCOL.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c3",
                "mcolClaim",
                "Rejected",
                "3",
                "Direct debit facility is not available.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c4",
                "mcolClaim",
                "Rejected",
                "4",
                "Claimant details provided in error.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c5",
                "mcolClaim",
                "Rejected",
                "5",
                "Claimant details are missing.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c6",
                "mcolClaim",
                "Rejected",
                "6",
                "Claimant correspondence details provided in error.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c7",
                "mcolClaim",
                "Rejected",
                "7",
                "Claimant correspondence details are missing.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c8",
                "mcolClaim",
                "Rejected",
                "8",
                "First defendant's postcode is not in England or Wales.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c9",
                "mcolClaim",
                "Rejected",
                "9",
                "Second defendant cannot have an identical name to the first defendant.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c10",
                "mcolClaim",
                "Rejected",
                "10",
                "Second defendant's postcode is not in England or Wales.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c11",
                "mcolClaim",
                "Rejected",
                "11",
                "Interest data is only required if the right to claim interest is reserved.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c12",
                "mcolClaim",
                "Rejected",
                "12",
                "Interest data is required if the right to claim interest is reserved.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c13",
                "mcolClaim",
                "Rejected",
                "13",
                "Date money became owed is invalid - it must be in the past.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c14",
                "mcolClaim",
                "Rejected",
                "14",
                "Date you are issuing the claim must be after the date the money became owed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c15",
                "mcolClaim",
                "Rejected",
                "15",
                "Claim amount (interest) cannot be greater than the maximum claim amount allowed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c16",
                "mcolClaim",
                "Rejected",
                "16",
                "Daily rate of interest since judgment must not be greater than GBP 9,999.99.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c17",
                "mcolClaim",
                "Rejected",
                "17",
                "Maximum number of lines for the claim particulars exceeded.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c18",
                "mcolClaim",
                "Rejected",
                "18",
                "Amount claimed cannot be greater than the maximum claim amount allowed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c19",
                "mcolClaim",
                "Rejected",
                "19",
                "Solicitor's costs can only be claimed by customers enrolled as a solicitor on MCOL.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c20",
                "mcolClaim",
                "Rejected",
                "20",
                "Solicitor's costs cannot be greater than the maximum solicitor's costs allowed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c21",
                "mcolClaim",
                "Rejected",
                "21",
                "Costs too high for the amount claimed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c22",
                "mcolJudgment",
                "Rejected",
                "22",
                "Defendant ID must be specified if not a joint judgment.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c23",
                "mcolJudgment",
                "Rejected",
                "23",
                "Specified claim does not belong to the requesting customer.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c24",
                "mcolJudgment",
                "Rejected",
                "24",
                "This judgment request is invalid on the referenced claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c25",
                "mcolJudgment",
                "Rejected",
                "25",
                "Joint judgment cannot be requested for a claim with only 1 defendant.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c26",
                "mcolJudgmentWarrant",
                "Rejected",
                "26",
                "Claim and defendant information must be consistent for a combined judgment and warrant request.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c27",
                "mcolJudgment",
                "Rejected",
                "27",
                "Defendant ID must not be specified on a joint judgment request.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c28",
                "mcolJudgment",
                "Rejected",
                "28",
                "Defendant 2 is specified but there is only 1 defendant on the claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c29",
                "mcolJudgment",
                "Rejected",
                "29",
                "Not enough days have passed since date of service to allow judgment by default.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c30",
                "mcolJudgment",
                "Rejected",
                "30",
                "Particulars of claim have not been sent separately as specified on the claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c31",
                "mcolJudgment",
                "Rejected",
                "31",
                "It was not specified on the claim that the particulars of claim would be sent separately.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c32",
                "mcolJudgment",
                "Rejected",
                "32",
                "Postcode for defendant is not in England or Wales.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c33",
                "mcolJudgment",
                "Rejected",
                "33",
                "Defendant's date of birth must be in the past.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c34",
                "mcolJudgment",
                "Rejected",
                "34",
                "Address for second defendant cannot be specified as there is only 1 defendant on the claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c35",
                "mcolJudgment",
                "Rejected",
                "35",
                "Address for second defendant cannot be specified as this is not a joint judgment request.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c36",
                "mcolJudgment",
                "Rejected",
                "36",
                "Postcode for second defendant is not in England or Wales.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c37",
                "mcolJudgment",
                "Rejected",
                "37",
                "Date of birth for second defendant cannot be specified as there is only 1 defendant on the claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c38",
                "mcolJudgment",
                "Rejected",
                "38",
                "Date of birth for second defendant cannot be specified as this is not a joint judgment request.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c39",
                "mcolJudgment",
                "Rejected",
                "39",
                "Second defendant's date of birth must be in the past.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c40",
                "mcolClaimStatusUpdate",
                "Rejected",
                "40",
                "Compliance to section 38 of CPR specified in error.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c41",
                "mcolClaimStatusUpdate",
                "Rejected",
                "41",
                "Compliance to section 38 of CPR must be true if discontinued update is requested.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c42",
                "mcolClaimStatusUpdate",
                "Rejected",
                "42",
                "Claim status update already submitted for this claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c44",
                "mcolJudgment",
                "Rejected",
                "44",
                "Instalment amount must be less than the full claim amount.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c45",
                "mcolClaimStatusUpdate",
                "Rejected",
                "45",
                "Defendant cannot be specified on a paid update pre-judgment.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c46",
                "mcolJudgment",
                "Rejected",
                "46",
                "Case data could not be populated: INVALID PARTICULARS OF CLAIM.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c47",
                "mcolSetAside",
                "Rejected",
                "47",
                "This application to set aside judgment is invalid on the referenced claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c48",
                "mcolSetAside",
                "Rejected",
                "48",
                "There is no active judgment for this application to set aside judgment.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c49",
                "mcolJudgment",
                "Rejected",
                "49",
                "Paid in full date must be in the future but not tomorrow.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c50",
                "mcolJudgment",
                "Rejected",
                "50",
                "Interest specified but right to claim interest was not reserved on the claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c51",
                "mcolJudgment",
                "Rejected",
                "51",
                "Interest since date of claim must not be greater than the maximum allowed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c52",
                "mcolJudgment",
                "Rejected",
                "52",
                "Interest cannot be specified when requesting a judgment by admission following a part admission.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c52b",
                "mcolJudgment",
                "Rejected",
                "DUPLD_CUST_REQID",
                "Unique Request Identifier has been specified more than once within the originating Bulk Request.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c52b",
                "mcolJudgment",
                "Rejected",
                "DUPLD_CUST_REQID",
                "Unique Request Identifier has been specified more than once within the originating Bulk Request.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c53",
                "mcolSetAside",
                "Rejected",
                "53",
                "Application to set aside cannot be made for the requested defendant(s).",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c54",
                "mcolJudgment",
                "Rejected",
                "54",
                "Costs must not be greater than the maximum allowed for the amount claimed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c55",
                "mcolJudgment",
                "Rejected",
                "55",
                "Deducted amount must not be greater than the maximum deduction allowed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c56",
                "mcolJudgment",
                "Rejected",
                "56",
                "Deducted amount results in negative judgment total.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c57",
                "mcolWarrant",
                "Rejected",
                "57",
                "This warrant request is invalid on the referenced claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c58",
                "mcolWarrant",
                "Rejected",
                "58",
                "Warrant cannot be requested for this defendant.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c59",
                "mcolSetAside",
                "Rejected",
                "59",
                "Other detail must be specified if applicant type is Other.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c60",
                "mcolWarrant",
                "Rejected",
                "60",
                "Balance due at date of request must be greater than the minimum amount allowed and not greater than the maximum allowed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c61",
                "mcolWarrant",
                "Rejected",
                "61",
                "Balance due at date of request must not be greater than the judgment total.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c62",
                "mcolWarrant",
                "Rejected",
                "62",
                "Warrant amount must not be less than the minimum warrant amount and not greater than the balance due at date of request.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c63",
                "mcolWarrant",
                "Rejected",
                "63",
                "Amount of warrant plus issue fee plus solicitor's costs are greater than the maximum total warrant allowed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c64",
                "mcolWarrant",
                "Rejected",
                "64",
                "Solicitor's costs can only be included on the warrant if they were included on the claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c65",
                "mcolWarrant",
                "Rejected",
                "65",
                "Solicitor's costs included on the warrant when the warrant amount is too small.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c66",
                "mcolWarrant",
                "Rejected",
                "66",
                "Solicitor's costs must not be greater than the maximum allowed.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c67",
                "mcolClaimStatusUpdate",
                "Rejected",
                "67",
                "This claim status update is invalid on the referenced claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c68",
                "mcolClaimStatusUpdate",
                "Rejected",
                "68",
                "Defendant number specified for a claim with a single defendant.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c69",
                "mcolClaimStatusUpdate",
                "Rejected",
                "69",
                "Defendant cannot be specified for an update type of Settled or Discontinued.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c70",
                "mcolClaimStatusUpdate",
                "Rejected",
                "70",
                "Invalid status update requested for this defendant.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c71",
                "mcolClaimStatusUpdate",
                "Rejected",
                "71",
                "Paid in full date specified in error.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c72",
                "mcolClaimStatusUpdate",
                "Rejected",
                "72",
                "Paid in full date missing.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c73",
                "mcolClaimStatusUpdate",
                "Rejected",
                "73",
                "Paid in full date cannot be in the future.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75a",
                "mcolClaim",
                "Rejected",
                "75",
                "Rejected by CCBC - rejection number 24.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75b",
                "mcolJudgment",
                "Rejected",
                "75",
                "Judgement: Rejected by CCBC - rejection number 6.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75c",
                "mcolWarrant",
                "Rejected",
                "75",
                "Warrant: Rejected by CCBC - rejection number 6.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75d",
                "mcolJudgmentWarrant",
                "Rejected",
                "75",
                "Judgment: Rejected by CCBC - rejection number 6.",
                """
                   <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                       <mresp:judgmentWarrantStatus>Judgment rejected by CCBC</mresp:judgmentWarrantStatus>
                   </ind:mcolResponseDetail>
                 """
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75e",
                "mcolJudgmentWarrant",
                "Rejected",
                "75",
                "Judgment: Rejected by CCBC - rejection number 6.  Warrant: Rejected by CCBC - rejection number 6.",
                """
                    <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                        <mresp:judgmentWarrantStatus>Judgment rejected by CCBC. Warrant rejected by CCBC.</mresp:judgmentWarrantStatus>
                    </ind:mcolResponseDetail>
                 """
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75f",
                "mcolClaimStatusUpdate",
                "Rejected",
                "75",
                "Rejected by CCBC - rejection number 6.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75g",
                "mcolSetAside",
                "Rejected",
                "75",
                "Rejected by CCBC - rejection number 97.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75h",
                "mcolBreathingSpace",
                "Rejected",
                "75",
                "Rejected by CCBC - rejection number 9.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75i",
                "mcolBreathingSpace",
                "Rejected",
                "75",
                "Rejected by CCBC - rejection number 26.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75j",
                "mcolBreathingSpace",
                "Rejected",
                "75",
                "Rejected by CCBC - rejection number 27.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c75k",
                "mcolBreathingSpace",
                "Rejected",
                "75",
                "Rejected by CCBC - rejection number 32.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c76",
                "mcolSetAside",
                "Rejected",
                "76",
                "Other detail must not be specified if applicant type is not Other.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c83",
                "mcolClaimStatusUpdate",
                "Rejected",
                "83",
                "No updates allowed to claim following application to set judgment aside.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c84",
                "mcolClaimStatusUpdate",
                "Rejected",
                "84",
                "Update type of Discontinued not allowed once judgment has been issued.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c85",
                "mcolSetAside",
                "Rejected",
                "85",
                "Representing who must be specified if applicant type is Legal Representative.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c86",
                "mcolSetAside",
                "Rejected",
                "86",
                "Service address should only be supplied if serving target is specified.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c87",
                "mcolSetAside",
                "Rejected",
                "87",
                "Evidence must be provided if supporting information choice is 'EV'.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c89",
                "mcolWarrant",
                "Rejected",
                "89",
                "Address previously supplied for the defendant is not in England or Wales.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c90",
                "mcolJudgment",
                "Rejected",
                "90",
                "Claim amount admitted can only be specified when requesting a judgment by admission following a part admission.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c91",
                "mcolJudgment",
                "Rejected",
                "91",
                "Claim amount admitted must be specified when requesting a judgment by admission following a part admission.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c92",
                "mcolJudgment",
                "Rejected",
                "92",
                "Claim legal costs admitted can only be specified when requesting a judgment by admission following a part admission.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c93",
                "mcolJudgment",
                "Rejected",
                "93",
                "Claim legal costs must be specified when requesting a judgment by admission following a part admission.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c94",
                "mcolJudgment",
                "Rejected",
                "94",
                "Claim court fees can only be specified when requesting a judgment by admission following a part admission.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c95",
                "mcolJudgment",
                "Rejected",
                "95",
                "Claim court fees must be specified when requesting a judgment by admission following a part admission.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c96",
                "mcolJudgment",
                "Rejected",
                "96",
                "Claim legal costs can only be claimed by customers enrolled as a solicitor on MCOL.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c97",
                "mcolJudgment",
                "Rejected",
                "97",
                "Claim amount admitted cannot be greater than the original claim amount, including interest up to date of claim but not legal costs or court fees.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c98",
                "mcolJudgment",
                "Rejected",
                "98",
                "Claim amount admitted cannot be zero.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c99",
                "mcolBreathingSpace",
                "Rejected",
                "99",
                "This breathing space request is invalid on the referenced claim.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c100",
                "mcolBreathingSpace",
                "Rejected",
                "100",
                "The defendant is already in active Breathing Space.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c101",
                "mcolBreathingSpace",
                "Rejected",
                "101",
                "The defendant is not currently in active Breathing Space.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c102",
                "mcolBreathingSpace",
                "Rejected",
                "102",
                "Incorrect Breathing Space ceasing event type.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c103",
                "mcolBreathingSpace",
                "Rejected",
                "103",
                "A party on the case is currently in active breathing space.",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c106",
                "mcolClaim",
                "Rejected",
                "106",
                "Claimant or witness on behalf of claimant has been identified as vulnerable but no details have been provided",
                ""
            ),
            Lists.newArrayList(
                "USER_REQUEST_ID_c107",
                "mcolClaim",
                "Rejected",
                "107",
                "Details of vulnerable claimant or witness on behalf of claimant have been provided, but the claimant or witness on behalf of claimant has not been identified as vulnerable",
                ""
            )
        ));
        methodInvokingFactoryBean.prepare();
        methodInvokingFactoryBean.invoke();
    }
}
