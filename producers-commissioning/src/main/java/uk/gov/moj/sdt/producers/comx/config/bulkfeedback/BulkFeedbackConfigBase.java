package uk.gov.moj.sdt.producers.comx.config.bulkfeedback;

import uk.gov.moj.sdt.domain.BulkSubmission;

import java.time.LocalDateTime;

public class BulkFeedbackConfigBase {

    protected static final String BULK_SUBMISSION_STATUS_COMPLETED = "Completed";
    protected static final String BULK_SUBMISSION_STATUS_VALIDATED = "Validated";

    protected static final String REQUEST_STATUS_ACCEPTED = "Accepted";
    protected static final String REQUEST_STATUS_AWAITING_DATA = "Awaiting Data";
    protected static final String REQUEST_STATUS_INITIALLY_ACCEPTED = "Initially Accepted";
    protected static final String REQUEST_STATUS_FORWARDED = "Forwarded";
    protected static final String REQUEST_STATUS_RECEIVED = "Received";
    protected static final String REQUEST_STATUS_REJECTED = "Rejected";

    protected static final String REQUEST_TYPE_BREATHING_SPACE = "mcolBreathingSpace";
    protected static final String REQUEST_TYPE_CLAIM = "mcolClaim";
    protected static final String REQUEST_TYPE_CLAIM_STATUS_UPDATE = "mcolClaimStatusUpdate";
    protected static final String REQUEST_TYPE_JUDGMENT = "mcolJudgment";
    protected static final String REQUEST_TYPE_JUDGMENT_WARRANT = "mcolJudgmentWarrant";
    protected static final String REQUEST_TYPE_SET_ASIDE = "mcolSetAside";
    protected static final String REQUEST_TYPE_WARRANT = "mcolWarrant";

    protected BulkSubmission createBulkSubmission(String customerRef,
                                                  int numRequests,
                                                  String submissionStatus,
                                                  LocalDateTime createdDate) {
        BulkSubmission bulkSubmission = new BulkSubmission();

        bulkSubmission.setCustomerReference(customerRef);
        bulkSubmission.setNumberOfRequest(numRequests);
        bulkSubmission.setSubmissionStatus(submissionStatus);
        bulkSubmission.setCreatedDate(createdDate);

        return bulkSubmission;
    }
}
