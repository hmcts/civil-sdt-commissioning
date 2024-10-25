package uk.gov.moj.sdt.producers.comx.config.bulkfeedback;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactory;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;

class BulkFeedbackConfigBTwoTest extends BulkFeedbackConfigTestBase {

    private BulkFeedbackConfigBTwo bulkFeedbackConfigBTwo;

    @Override
    protected void setUpLocalTests() {
        bulkFeedbackConfigBTwo = new BulkFeedbackConfigBTwo();
    }

    @Test
    void testInvokingBean() {
        try {
            bulkFeedbackConfigBTwo.invokingBean();
        } catch (ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException | IllegalAccessException e) {
            fail(e.getClass().getName() + " should not be raised");
        }
    }

    @Test
    void testBulkFeedbackFactory() {
        LocalDateTime expectedCreatedDate =
            LocalDateTime.of(2014, 1, 22, 13, 0);

        BulkFeedbackFactory bulkFeedbackFactory = bulkFeedbackConfigBTwo.bulkFeedbackFactory();
        assertBulkFeedbackFactory(bulkFeedbackFactory,
                                  "USER_FILE_REFERENCE_B2",
                                  20,
                                  SUBMISSION_STATUS_COMPLETED,
                                  expectedCreatedDate);
    }
}
