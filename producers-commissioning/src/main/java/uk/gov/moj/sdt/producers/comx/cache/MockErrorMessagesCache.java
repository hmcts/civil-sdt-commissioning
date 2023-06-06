package uk.gov.moj.sdt.producers.comx.cache;

import org.springframework.stereotype.Component;
import uk.gov.moj.sdt.domain.ErrorMessage;
import uk.gov.moj.sdt.domain.api.IDomainObject;
import uk.gov.moj.sdt.domain.api.IErrorMessage;
import uk.gov.moj.sdt.domain.cache.api.ICacheable;
import uk.gov.moj.sdt.services.cache.api.IErrorMessagesCache;

import java.util.HashMap;

/**
 * Mock implementation of the Error Messages Cache.
 *
 * @author d164190
 */
@Component("MockErrorMessagesCache")
public class MockErrorMessagesCache implements ICacheable, IErrorMessagesCache {

    private final HashMap<String, String> hmErrorMessages = new HashMap<>();

    public static final String DUP_CUST_FILEID_TEXT =
        "Duplicate User File Reference {0} supplied. This was previously used to " +
                "submit a Bulk Request on {1} and the SDT Bulk Reference {2} was allocated.";
    public static final String REQ_COUNT_MISMATCH_TEXT =
        "Unexpected Total Number of Requests identified. {0} requested identified, " +
                "{1} requests expected in Bulk Request {2}.";
    public static final String SDT_INT_ERR_TEXT =
        "A system error has occurred. Please contact {0} for assistance.";
    public static final String CUST_NOT_SETUP_TEXT =
        "The Bulk Customer organisation is not setup to send Service Request " +
                "messages to the {0}. Please contact {1} for assistance.";
    public static final String CUST_ID_INVALID_TEXT =
        "The Bulk Customer organisation does not have an SDT Customer ID set up. " +
                "Please contact {0} for assistance.";
    public static final String BULK_REF_INVALID_TEXT =
        "There is no Bulk Request submission associated with your account for the" +
                " supplied SDT Bulk Reference {0}.";
    public static final String DUP_CUST_REQID_TEXT =
                "Duplicate Unique Request Identifier submitted {0}.";
    public static final String DUPLD_CUST_REQID_TEXT =
                "Unique Request Identifier has been specified more than once " +
                        "within the originating Bulk Request.";

    MockErrorMessagesCache() {
        hmErrorMessages.put(IErrorMessage.ErrorCode.BULK_REF_INVALID.toString(), BULK_REF_INVALID_TEXT);
        hmErrorMessages.put(IErrorMessage.ErrorCode.CUST_ID_INVALID.toString(), CUST_ID_INVALID_TEXT);
        hmErrorMessages.put(IErrorMessage.ErrorCode.CUST_NOT_SETUP.toString(), CUST_NOT_SETUP_TEXT);
        hmErrorMessages.put(IErrorMessage.ErrorCode.DUP_CUST_FILEID.toString(), DUP_CUST_FILEID_TEXT);
        hmErrorMessages.put(IErrorMessage.ErrorCode.DUP_CUST_REQID.toString(), DUP_CUST_REQID_TEXT);
        hmErrorMessages.put(IErrorMessage.ErrorCode.DUPLD_CUST_REQID.toString(), DUPLD_CUST_REQID_TEXT);
        hmErrorMessages.put(IErrorMessage.ErrorCode.REQ_COUNT_MISMATCH.toString(), REQ_COUNT_MISMATCH_TEXT);
        hmErrorMessages.put(IErrorMessage.ErrorCode.SDT_INT_ERR.toString(), SDT_INT_ERR_TEXT);
    }

    @Override
    public <D extends IDomainObject> D getValue(final Class<D> domainType, final String key) {
        final IErrorMessage errorMessage = new ErrorMessage();

        if (hmErrorMessages.containsKey(key)) {
            errorMessage.setErrorCode(key);
            errorMessage.setErrorText(hmErrorMessages.get(key));
        }
        return (D) errorMessage;
    }

}
