package uk.gov.moj.sdt.producers.comx.config.bulkfeedback;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactory;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;

class BulkFeedbackConfigAOneTest extends BulkFeedbackConfigTestBase {

    private BulkFeedbackConfigAOne bulkFeedbackConfigAOne;

    @Override
    protected void setUpLocalTests() {
        bulkFeedbackConfigAOne = new BulkFeedbackConfigAOne();
    }

    @Test
    void testInvokingBean() {
        try {
            bulkFeedbackConfigAOne.invokingBean();
        } catch (ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException | IllegalAccessException e) {
            fail(e.getClass().getName() + " should not be raised");
        }
    }

    @Test
    void testBulkFeedbackFactory() {
        LocalDateTime expectedCreatedDate =
            LocalDateTime.of(2013, 7, 22, 13, 0);

        BulkFeedbackFactory bulkFeedbackFactory = bulkFeedbackConfigAOne.bulkFeedbackFactory();
        assertBulkFeedbackFactory(bulkFeedbackFactory,
                                  "USER_FILE_REFERENCE_A1",
                                  1,
                                  SUBMISSION_STATUS_COMPLETED,
                                  expectedCreatedDate);
    }
}
