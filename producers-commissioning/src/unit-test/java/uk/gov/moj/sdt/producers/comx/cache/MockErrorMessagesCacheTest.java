package uk.gov.moj.sdt.producers.comx.cache;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.moj.sdt.domain.ErrorMessage;
import uk.gov.moj.sdt.domain.api.IErrorMessage;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
class MockErrorMessagesCacheTest extends AbstractSdtUnitTestBase {

    private static final String ERROR_CODE_NOT_IN_CACHE = "999";

    /**
     * Unit test for {@link MockErrorMessagesCache}.
     */
    private MockErrorMessagesCache mockErrorMessagesCache;

    /**
     * Set up common for all tests.
     */
    @Override
    protected void setUpLocalTests () {
        mockErrorMessagesCache = new MockErrorMessagesCache();
    }

    @ParameterizedTest
    @MethodSource("errorCodesAndMessages")
    void testErrorCodeAgainstExpectedMessage(String errorCode, String expectedMessage) {
        ErrorMessage errorMessage = mockErrorMessagesCache.getValue(ErrorMessage.class, errorCode);

        assertNotNull(errorMessage, "ErrorMessage should not be null");
        assertEquals(errorCode, errorMessage.getErrorCode(), "ErrorMessage has unexpected error code");
        assertEquals(expectedMessage, errorMessage.getErrorText(), "ErrorMessage has unexpected error text");
    }

    @Test
    void testErrorCodeNotInCache() {
        ErrorMessage errorMessage = mockErrorMessagesCache.getValue(ErrorMessage.class, ERROR_CODE_NOT_IN_CACHE);

        assertNotNull(errorMessage, "ErrorMessage should not be null");
        assertNull(errorMessage.getErrorCode(), "ErrorMessage error code should be null");
        assertNull(errorMessage.getErrorText(), "ErrorMessage error text should be null");
    }

    /**
     * Create arguments for the parameterised xsdValidationFailure test.
     * @return The arguments for the parameterised xsdValidationFailure test
     */
    static Stream<Arguments> errorCodesAndMessages() {
        return Stream.of(
            arguments(IErrorMessage.ErrorCode.BULK_REF_INVALID.toString(), MockErrorMessagesCache.BULK_REF_INVALID_TEXT),
            arguments(IErrorMessage.ErrorCode.CUST_NOT_SETUP.toString(), MockErrorMessagesCache.CUST_NOT_SETUP_TEXT),
            arguments(IErrorMessage.ErrorCode.CUST_ID_INVALID.toString(), MockErrorMessagesCache.CUST_ID_INVALID_TEXT),
            arguments(IErrorMessage.ErrorCode.DUP_CUST_FILEID.toString(), MockErrorMessagesCache.DUP_CUST_FILEID_TEXT),
            arguments(IErrorMessage.ErrorCode.DUP_CUST_REQID.toString(), MockErrorMessagesCache.DUP_CUST_REQID_TEXT),
            arguments(IErrorMessage.ErrorCode.DUPLD_CUST_REQID.toString(),MockErrorMessagesCache.DUPLD_CUST_REQID_TEXT),
            arguments(IErrorMessage.ErrorCode.REQ_COUNT_MISMATCH.toString(), MockErrorMessagesCache.REQ_COUNT_MISMATCH_TEXT),
            arguments(IErrorMessage.ErrorCode.SDT_INT_ERR.toString(), MockErrorMessagesCache.SDT_INT_ERR_TEXT)
        );
    }
}
