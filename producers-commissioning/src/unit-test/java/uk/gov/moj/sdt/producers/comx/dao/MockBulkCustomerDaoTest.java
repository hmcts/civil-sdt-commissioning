package uk.gov.moj.sdt.producers.comx.dao;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.domain.api.IBulkCustomer;
import uk.gov.moj.sdt.domain.api.IBulkCustomerApplication;
import uk.gov.moj.sdt.domain.api.ITargetApplication;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class MockBulkCustomerDaoTest extends AbstractSdtUnitTestBase {

    private static final long SDT_CUSTOMER_ID = 2L;

    private static final String TARGET_APP_CODE_MCOL = "MCOL";
    private static final String TARGET_APP_CODE_APP1 = "APP1";
    private static final String TARGET_APP_CODE_APP2 = "APP2";

    private MockBulkCustomerDao mockBulkCustomerDao;

    @Override
    protected void setUpLocalTests() {
        mockBulkCustomerDao = new MockBulkCustomerDao();
    }

    @Test
    void testGetBulkCustomerBySdtId() {
        IBulkCustomer bulkCustomer = mockBulkCustomerDao.getBulkCustomerBySdtId(SDT_CUSTOMER_ID);

        assertNotNull(bulkCustomer, "BulkCustomer should not be null");
        assertEquals(SDT_CUSTOMER_ID, bulkCustomer.getId(), "BulkCustomer has unexpected id");
        assertEquals(SDT_CUSTOMER_ID, bulkCustomer.getSdtCustomerId(), "BulkCustomer has unexpected SDT customer id");

        Set<IBulkCustomerApplication> bulkCustomerApplications = bulkCustomer.getBulkCustomerApplications();
        assertNotNull(bulkCustomerApplications, "BulkCustomerApplications should not be null");
        assertEquals(1, bulkCustomerApplications.size(), "Unexpected number of BulkCustomerApplications");

        IBulkCustomerApplication bulkCustomerApplication = bulkCustomerApplications.iterator().next();
        assertEquals(
            TARGET_APP_CODE_MCOL + "-id",
            bulkCustomerApplication.getCustomerApplicationId(),
            "BulkCustomerApplication has unexpected customer application id");

        IBulkCustomer actualBulkCustomer = bulkCustomerApplication.getBulkCustomer();
        assertNotNull(actualBulkCustomer, "BulkCustomerApplication BulkCustomer should not be null");
        assertSame(bulkCustomer, actualBulkCustomer, "BulkCustomerApplication has unexpected BulkCustomer");

        ITargetApplication targetApplication = bulkCustomerApplication.getTargetApplication();
        assertNotNull(targetApplication, "BulkCustomerApplication TargetApplication should not be null");
        assertEquals(
            TARGET_APP_CODE_MCOL,
            targetApplication.getTargetApplicationCode(),
            "BulkCustomerApplication TargetApplication has unexpected target application code");
        assertEquals(
            TARGET_APP_CODE_MCOL,
            targetApplication.getTargetApplicationName(),
            "BulkCustomerApplication TargetApplication has unexpected target application name");
    }

    @Test
    void testSetTargetAppCodes() {
        List<String> targetAppCodes = new ArrayList<>();
        targetAppCodes.add(TARGET_APP_CODE_APP1);
        targetAppCodes.add(TARGET_APP_CODE_APP2);

        mockBulkCustomerDao.setTargetAppCodes(targetAppCodes);

        List<String> actualTargetAppCodes = (List<String>) getAccessibleField(MockBulkCustomerDao.class,
                                                                              "targetAppCodes",
                                                                              List.class,
                                                                              mockBulkCustomerDao);

        assertNotNull(actualTargetAppCodes, "TargetAppCodes should not be null");
        assertEquals(2, actualTargetAppCodes.size(), "Unexpected numnber of TargetAppCodes");
        assertEquals(TARGET_APP_CODE_APP1, actualTargetAppCodes.get(0), "First TargetAppCode has unexpected name");
        assertEquals(TARGET_APP_CODE_APP2, actualTargetAppCodes.get(1), "Second TargetAppCode has unexpected name");
    }
}
