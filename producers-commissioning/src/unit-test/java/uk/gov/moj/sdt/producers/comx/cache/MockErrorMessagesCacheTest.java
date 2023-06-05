package uk.gov.moj.sdt.producers.comx.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.moj.sdt.domain.ErrorMessage;
import uk.gov.moj.sdt.domain.api.IErrorMessage;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MockErrorMessagesCacheTest extends AbstractSdtUnitTestBase {

    /**
     * Unit test for {@link MockErrorMessagesCache}.
     */
    private MockErrorMessagesCache mockErrorMessagesCache;

    /**
     * Set up common for all tests.
     */
    @BeforeEach
    @Override
    public void setUp () {
        mockErrorMessagesCache = new MockErrorMessagesCache();
    }

    @Test
    void testDupCustFileId() {
        testGivenErrorCode(IErrorMessage.ErrorCode.DUP_CUST_FILEID, MockErrorMessagesCache.DUP_CUST_FILEID_TEXT);
    }

    @Test
    void testDupCustReqId() {
        testGivenErrorCode(IErrorMessage.ErrorCode.DUP_CUST_REQID, MockErrorMessagesCache.DUP_CUST_REQID_TEXT);
    }

    @Test
    void testCustNotSetup() {
        testGivenErrorCode(IErrorMessage.ErrorCode.CUST_NOT_SETUP, MockErrorMessagesCache.CUST_NOT_SETUP_TEXT);
    }

    @Test
    void testBulkRefInvalid() {
        testGivenErrorCode(IErrorMessage.ErrorCode.BULK_REF_INVALID, MockErrorMessagesCache.BULK_REF_INVALID_TEXT);
    }

    @Test
    void testCustIdInvalid() {
        testGivenErrorCode(IErrorMessage.ErrorCode.CUST_ID_INVALID, MockErrorMessagesCache.CUST_ID_INVALID_TEXT);
    }

    @Test
    void testReqCountMismatch() {
        testGivenErrorCode(IErrorMessage.ErrorCode.REQ_COUNT_MISMATCH, MockErrorMessagesCache.REQ_COUNT_MISMATCH_TEXT);
    }

    @Test
    void testSdtIntErr() {
        testGivenErrorCode(IErrorMessage.ErrorCode.SDT_INT_ERR, MockErrorMessagesCache.SDT_INT_ERR_TEXT);
    }

    private void testGivenErrorCode(IErrorMessage.ErrorCode errorCode, String expectedMessage) {
        ErrorMessage errorMessage =
                mockErrorMessagesCache.getValue(ErrorMessage.class, errorCode.toString());

        assertEquals(errorCode.toString(), errorMessage.getErrorCode());
        assertEquals(expectedMessage, errorMessage.getErrorText());
    }

}
