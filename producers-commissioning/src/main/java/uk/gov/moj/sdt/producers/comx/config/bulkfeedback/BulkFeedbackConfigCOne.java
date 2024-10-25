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
public class BulkFeedbackConfigCOne extends BulkFeedbackConfigBase {

    @Bean("createIndividualRequestC0000001")
    public void invokingBean()
        throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetObject(bulkFeedbackFactory());
        methodInvokingFactoryBean.setTargetMethod("createIndividualRequests");
        methodInvokingFactoryBean.setArguments(createIndividualRequestsList());
        methodInvokingFactoryBean.prepare();
        methodInvokingFactoryBean.invoke();
    }

    @Bean("uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactoryC00000001")
    public BulkFeedbackFactory bulkFeedbackFactory() {
        return new BulkFeedbackFactory(bulkSubmissionC00000001());
    }

    private BulkSubmission bulkSubmissionC00000001() {
        LocalDateTime createdDate = LocalDateTime.of(2014, 1, 22, 13, 0);
        return createBulkSubmission("USER_FILE_REFERENCE_C1", 109, BULK_SUBMISSION_STATUS_COMPLETED, createdDate);
    }

    private List<List<String>> createIndividualRequestsList() {
        List<List<String>> individualRequestsList = new ArrayList<>();

        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c0",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "0",
                "Bad Data.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c1",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "1",
                "Unknown MCOL customer number specified.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c2",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "2",
                "MCOL customer number specified has not been set up for SDT use on MCOL.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c3",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "3",
                "Direct debit facility is not available.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c4",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "4",
                "Claimant details provided in error.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c5",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "5",
                "Claimant details are missing.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c6",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "6",
                "Claimant correspondence details provided in error.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c7",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "7",
                "Claimant correspondence details are missing.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c8",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "8",
                "First defendant's postcode is not in England or Wales.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c9",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "9",
                "Second defendant cannot have an identical name to the first defendant.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c10",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "10",
                "Second defendant's postcode is not in England or Wales.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c11",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "11",
                "Interest data is only required if the right to claim interest is reserved.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c12",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "12",
                "Interest data is required if the right to claim interest is reserved.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c13",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "13",
                "Date money became owed is invalid - it must be in the past.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c14",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "14",
                "Date you are issuing the claim must be after the date the money became owed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c15",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "15",
                "Claim amount (interest) cannot be greater than the maximum claim amount allowed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c16",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "16",
                "Daily rate of interest since judgment must not be greater than GBP 9,999.99.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c17",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "17",
                "Maximum number of lines for the claim particulars exceeded.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c18",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "18",
                "Amount claimed cannot be greater than the maximum claim amount allowed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c19",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "19",
                "Solicitor's costs can only be claimed by customers enrolled as a solicitor on MCOL.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c20",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "20",
                "Solicitor's costs cannot be greater than the maximum solicitor's costs allowed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c21",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "21",
                "Costs too high for the amount claimed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c22",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "22",
                "Defendant ID must be specified if not a joint judgment.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c23",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "23",
                "Specified claim does not belong to the requesting customer.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c24",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "24",
                "This judgment request is invalid on the referenced claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c25",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "25",
                "Joint judgment cannot be requested for a claim with only 1 defendant.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c26",
                REQUEST_TYPE_JUDGMENT_WARRANT,
                REQUEST_STATUS_REJECTED,
                "26",
                "Claim and defendant information must be consistent for a combined judgment and warrant request.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c27",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "27",
                "Defendant ID must not be specified on a joint judgment request.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c28",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "28",
                "Defendant 2 is specified but there is only 1 defendant on the claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c29",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "29",
                "Not enough days have passed since date of service to allow judgment by default.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c30",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "30",
                "Particulars of claim have not been sent separately as specified on the claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c31",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "31",
                "It was not specified on the claim that the particulars of claim would be sent separately.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c32",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "32",
                "Postcode for defendant is not in England or Wales.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c33",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "33",
                "Defendant's date of birth must be in the past.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c34",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "34",
                "Address for second defendant cannot be specified as there is only 1 defendant on the claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c35",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "35",
                "Address for second defendant cannot be specified as this is not a joint judgment request.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c36",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "36",
                "Postcode for second defendant is not in England or Wales.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c37",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "37",
                "Date of birth for second defendant cannot be specified as there is only 1 defendant on the claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c38",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "38",
                "Date of birth for second defendant cannot be specified as this is not a joint judgment request.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c39",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "39",
                "Second defendant's date of birth must be in the past.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c40",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "40",
                "Compliance to section 38 of CPR specified in error.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c41",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "41",
                "Compliance to section 38 of CPR must be true if discontinued update is requested.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c42",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "42",
                "Claim status update already submitted for this claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c44",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "44",
                "Instalment amount must be less than the full claim amount.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c45",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "45",
                "Defendant cannot be specified on a paid update pre-judgment.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c46",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "46",
                "Case data could not be populated: INVALID PARTICULARS OF CLAIM.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c47",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_REJECTED,
                "47",
                "This application to set aside judgment is invalid on the referenced claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c48",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_REJECTED,
                "48",
                "There is no active judgment for this application to set aside judgment.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c49",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "49",
                "Paid in full date must be in the future but not tomorrow.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c50",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "50",
                "Interest specified but right to claim interest was not reserved on the claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c51",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "51",
                "Interest since date of claim must not be greater than the maximum allowed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c52",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "52",
                "Interest cannot be specified when requesting a judgment by admission following a part admission.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c52b",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "DUPLD_CUST_REQID",
                "Unique Request Identifier has been specified more than once within the originating Bulk Request.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c52b",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "DUPLD_CUST_REQID",
                "Unique Request Identifier has been specified more than once within the originating Bulk Request.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c53",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_REJECTED,
                "53",
                "Application to set aside cannot be made for the requested defendant(s).",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c54",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "54",
                "Costs must not be greater than the maximum allowed for the amount claimed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c55",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "55",
                "Deducted amount must not be greater than the maximum deduction allowed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c56",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "56",
                "Deducted amount results in negative judgment total.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c57",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "57",
                "This warrant request is invalid on the referenced claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c58",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "58",
                "Warrant cannot be requested for this defendant.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c59",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_REJECTED,
                "59",
                "Other detail must be specified if applicant type is Other.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c60",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "60",
                "Balance due at date of request must be greater than the minimum amount allowed and not greater than the maximum allowed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c61",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "61",
                "Balance due at date of request must not be greater than the judgment total.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c62",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "62",
                "Warrant amount must not be less than the minimum warrant amount and not greater than the balance due at date of request.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c63",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "63",
                "Amount of warrant plus issue fee plus solicitor's costs are greater than the maximum total warrant allowed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c64",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "64",
                "Solicitor's costs can only be included on the warrant if they were included on the claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c65",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "65",
                "Solicitor's costs included on the warrant when the warrant amount is too small.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c66",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "66",
                "Solicitor's costs must not be greater than the maximum allowed.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c67",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "67",
                "This claim status update is invalid on the referenced claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c68",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "68",
                "Defendant number specified for a claim with a single defendant.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c69",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "69",
                "Defendant cannot be specified for an update type of Settled or Discontinued.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c70",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "70",
                "Invalid status update requested for this defendant.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c71",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "71",
                "Paid in full date specified in error.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c72",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "72",
                "Paid in full date missing.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c73",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "73",
                "Paid in full date cannot be in the future.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75a",
                REQUEST_TYPE_CLAIM,
                REQUEST_STATUS_REJECTED,
                "75",
                "Rejected by CCBC - rejection number 24.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75b",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "75",
                "Judgement: Rejected by CCBC - rejection number 6.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75c",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "75",
                "Warrant: Rejected by CCBC - rejection number 6.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75d",
                REQUEST_TYPE_JUDGMENT_WARRANT,
                REQUEST_STATUS_REJECTED,
                "75",
                "Judgment: Rejected by CCBC - rejection number 6.",
                """
                   <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                       <mresp:judgmentWarrantStatus>Judgment rejected by CCBC</mresp:judgmentWarrantStatus>
                   </ind:mcolResponseDetail>
                 """
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75e",
                REQUEST_TYPE_JUDGMENT_WARRANT,
                REQUEST_STATUS_REJECTED,
                "75",
                "Judgment: Rejected by CCBC - rejection number 6.  Warrant: Rejected by CCBC - rejection number 6.",
                """
                    <ind:mcolResponseDetail xmlns:ind="http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema" xmlns:mresp="http://ws.sdt.moj.gov.uk/2013/mcol/ResponseDetailSchema">
                        <mresp:judgmentWarrantStatus>Judgment rejected by CCBC. Warrant rejected by CCBC.</mresp:judgmentWarrantStatus>
                    </ind:mcolResponseDetail>
                 """
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75f",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "75",
                "Rejected by CCBC - rejection number 6.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75g",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_REJECTED,
                "75",
                "Rejected by CCBC - rejection number 97.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75h",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "75",
                "Rejected by CCBC - rejection number 9.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75i",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "75",
                "Rejected by CCBC - rejection number 26.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75j",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "75",
                "Rejected by CCBC - rejection number 27.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c75k",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "75",
                "Rejected by CCBC - rejection number 32.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c76",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_REJECTED,
                "76",
                "Other detail must not be specified if applicant type is not Other.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c83",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "83",
                "No updates allowed to claim following application to set judgment aside.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c84",
                REQUEST_TYPE_CLAIM_STATUS_UPDATE,
                REQUEST_STATUS_REJECTED,
                "84",
                "Update type of Discontinued not allowed once judgment has been issued.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c85",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_REJECTED,
                "85",
                "Representing who must be specified if applicant type is Legal Representative.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c86",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_REJECTED,
                "86",
                "Service address should only be supplied if serving target is specified.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c87",
                REQUEST_TYPE_SET_ASIDE,
                REQUEST_STATUS_REJECTED,
                "87",
                "Evidence must be provided if supporting information choice is 'EV'.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c89",
                REQUEST_TYPE_WARRANT,
                REQUEST_STATUS_REJECTED,
                "89",
                "Address previously supplied for the defendant is not in England or Wales.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c90",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "90",
                "Claim amount admitted can only be specified when requesting a judgment by admission following a part admission.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c91",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "91",
                "Claim amount admitted must be specified when requesting a judgment by admission following a part admission.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c92",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "92",
                "Claim legal costs admitted can only be specified when requesting a judgment by admission following a part admission.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c93",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "93",
                "Claim legal costs must be specified when requesting a judgment by admission following a part admission.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c94",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "94",
                "Claim court fees can only be specified when requesting a judgment by admission following a part admission.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c95",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "95",
                "Claim court fees must be specified when requesting a judgment by admission following a part admission.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c96",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "96",
                "Claim legal costs can only be claimed by customers enrolled as a solicitor on MCOL.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c97",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "97",
                "Claim amount admitted cannot be greater than the original claim amount, including interest up to date of claim but not legal costs or court fees.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c98",
                REQUEST_TYPE_JUDGMENT,
                REQUEST_STATUS_REJECTED,
                "98",
                "Claim amount admitted cannot be zero.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c99",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "99",
                "This breathing space request is invalid on the referenced claim.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c100",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "100",
                "The defendant is already in active Breathing Space.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c101",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "101",
                "The defendant is not currently in active Breathing Space.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c102",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "102",
                "Incorrect Breathing Space ceasing event type.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c103",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "103",
                "A party on the case is currently in active breathing space.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c200",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "200",
                "Case Offline.",
                ""
            )
        );
        individualRequestsList.add(
            Lists.newArrayList(
                "USER_REQUEST_ID_c201",
                REQUEST_TYPE_BREATHING_SPACE,
                REQUEST_STATUS_REJECTED,
                "201",
                "Request already processed.",
                ""
            )
        );

        return individualRequestsList;
    }
}
