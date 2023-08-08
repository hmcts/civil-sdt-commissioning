package uk.gov.moj.sdt.producers.comx.config.bulkfeedback;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactory;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;

class BulkFeedbackConfigBOneTest extends BulkFeedbackConfigTestBase {

    private BulkFeedbackConfigBOne bulkFeedbackConfigBOne;

    @Override
    protected void setUpLocalTests() {
        bulkFeedbackConfigBOne = new BulkFeedbackConfigBOne();
    }

    @Test
    void testInvokingBean() {
        try {
            bulkFeedbackConfigBOne.invokingBean();
        } catch (ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException | IllegalAccessException e) {
            fail(e.getClass().getName() + " should not be raised");
        }
    }

    @Test
    void testBulkFeedbackFactory() {
        LocalDateTime expectedCreatedDate =
            LocalDateTime.of(2014, 1, 22, 13, 0);

        BulkFeedbackFactory bulkFeedbackFactory = bulkFeedbackConfigBOne.bulkFeedbackFactory();
        assertBulkFeedbackFactory(bulkFeedbackFactory,
                                  "USER_FILE_REFERENCE_B1",
                                  19,
                                  SUBMISSION_STATUS_VALIDATED,
                                  expectedCreatedDate);
    }
}
