package uk.gov.moj.sdt.producers.comx.config.bulkfeedback;

import uk.gov.moj.sdt.domain.api.IBulkSubmission;
import uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactory;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BulkFeedbackConfigTestBase extends AbstractSdtUnitTestBase {

    public static final String SUBMISSION_STATUS_COMPLETED = "Completed";
    public static final String SUBMISSION_STATUS_VALIDATED = "Validated";

    public void assertBulkFeedbackFactory(BulkFeedbackFactory bulkFeedbackFactory,
                                          String expectedCustomerReference,
                                          long expectedNumberOfRequest,
                                          String expectedSubmissionStatus,
                                          LocalDateTime expectedCreatedDate) {
        IBulkSubmission bulkSubmission = bulkFeedbackFactory.getBulkSubmission();

        assertEquals(expectedCustomerReference,
                     bulkSubmission.getCustomerReference(),
                     "BulkSubmission created by BulkFeedbackFactory has unexpected customer reference");
        assertEquals(expectedNumberOfRequest,
                     bulkSubmission.getNumberOfRequest(),
                     "BulkSubmission created by BulkFeedbackFactory has unexpected number of request");
        assertEquals(expectedSubmissionStatus,
                     bulkSubmission.getSubmissionStatus(),
                     "BulkSubmission created by BulkFeedbackFactory has unexpected submission status");
        assertEquals(expectedCreatedDate,
                     bulkSubmission.getCreatedDate(),
                     "BulkSubmission created by BulkFeedbackFactory has unexpected created date");
    }
}
