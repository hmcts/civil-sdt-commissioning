package uk.gov.moj.sdt.producers.comx.dao;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.domain.BulkCustomer;
import uk.gov.moj.sdt.domain.api.IBulkCustomer;
import uk.gov.moj.sdt.domain.api.IIndividualRequest;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MockIndividualRequestDaoTest extends AbstractSdtUnitTestBase {

    private static final String CUST_REF = "CustRef";
    private static final String CUST_REF_DUPLICATE = "duplicate";

    private static final int DATA_RETENTION_PERIOD = 90;

    private static final String SDT_REF_ID = "SdtRefId";

    private static final int MAX_ATTEMPTS = 3;

    private MockIndividualRequestDao mockIndividualRequestDao;

    @Override
    protected void setUpLocalTests() {
        mockIndividualRequestDao = new MockIndividualRequestDao();
    }

    @Test
    void testGetIndividualRequest() {
        IBulkCustomer bulkCustomer = new BulkCustomer();

        IIndividualRequest individualRequest =
            mockIndividualRequestDao.getIndividualRequest(bulkCustomer, CUST_REF, DATA_RETENTION_PERIOD);
        assertNull(individualRequest, "IndividualRequest should be null");
    }

    @Test
    void testGetIndividualRequestDuplicate() {
        IBulkCustomer bulkCustomer = new BulkCustomer();

        IIndividualRequest individualRequest =
            mockIndividualRequestDao.getIndividualRequest(bulkCustomer, CUST_REF_DUPLICATE, DATA_RETENTION_PERIOD);
        assertNotNull(individualRequest, "IndividualRequest should not be null");
    }

    @Test
    void testGetRequestBySdtReference() {
        IIndividualRequest individualRequest = mockIndividualRequestDao.getRequestBySdtReference(SDT_REF_ID);
        assertNull(individualRequest, "IndividualRequest should be null");
    }

    @Test
    void testGetPendingIndividualRequests() {
        List<IIndividualRequest> pendingIndividualRequests =
            mockIndividualRequestDao.getPendingIndividualRequests(MAX_ATTEMPTS);
        assertNull(pendingIndividualRequests, "List of pending IndividualRequests should be null");
    }

    @Test
    void testGetStaleIndividualRequests() {
        List<IIndividualRequest> staleIndividualRequests =
            mockIndividualRequestDao.getStaleIndividualRequests(MAX_ATTEMPTS);
        assertNull(staleIndividualRequests, "List of stale IndividualRequests should be null");
    }
}
