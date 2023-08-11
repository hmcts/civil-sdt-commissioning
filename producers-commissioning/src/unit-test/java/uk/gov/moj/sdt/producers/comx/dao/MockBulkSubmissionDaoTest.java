package uk.gov.moj.sdt.producers.comx.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.moj.sdt.domain.BulkCustomer;
import uk.gov.moj.sdt.domain.api.IBulkCustomer;
import uk.gov.moj.sdt.domain.api.IBulkSubmission;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MockBulkSubmissionDaoTest extends AbstractSdtUnitTestBase {

    private static final String CUST_REF = "CustRef";
    private static final String CUST_REF_DUPLICATE = "duplicate";

    private static final int DATA_RETENTION_PERIOD = 90;

    private static final String SDT_BULK_REF_A1 = "MCOL_20130722000000_A00000001";
    private static final String SDT_BULK_REF_B1 = "MCOL_20130722000000_B00000001";
    private static final String SDT_BULK_REF_B2 = "MCOL_20130722000000_B00000002";
    private static final String SDT_BULK_REF_C1 = "MCOL_20130722000000_C00000001";
    private static final String SDT_BULK_REF_NOT_FOUND = "SdtBulkRefNotFound";
    private static final String SDT_BULK_REF_TEST = "Test";

    private MockBulkSubmissionDao mockBulkSubmissionDao;

    @Override
    protected void setUpLocalTests() {
        mockBulkSubmissionDao = new MockBulkSubmissionDao();
    }

    @Test
    void testGetBulkSubmission() {
        IBulkCustomer bulkCustomer = new BulkCustomer();

        IBulkSubmission bulkSubmission =
            mockBulkSubmissionDao.getBulkSubmission(bulkCustomer, CUST_REF, DATA_RETENTION_PERIOD);

        assertNull(bulkSubmission, "BulkSubmission should be null");
    }

    @Test
    void testGetBulkSubmissionDuplicate() {
        IBulkCustomer bulkCustomer = new BulkCustomer();

        IBulkSubmission bulkSubmission =
            mockBulkSubmissionDao.getBulkSubmission(bulkCustomer, CUST_REF_DUPLICATE, DATA_RETENTION_PERIOD);

        assertNotNull(bulkSubmission, "BulkSubmission should not be null");
        assertEquals(CUST_REF_DUPLICATE.toLowerCase(),
                     bulkSubmission.getCustomerReference(),
                     "BulkSubmission has unexpected customer reference");
        assertEquals(SDT_BULK_REF_B1,
                     bulkSubmission.getSdtBulkReference(),
                     "BulkSubmission has unexpected SDT bulk reference");

        LocalDateTime createdDate = LocalDateTime.of(2013, 7, 22, 13, 0);
        assertEquals(createdDate, bulkSubmission.getCreatedDate(), "BulkSubmission has unexpected created date");
    }

    @ParameterizedTest
    @ValueSource(strings = {SDT_BULK_REF_A1, SDT_BULK_REF_B1, SDT_BULK_REF_B2, SDT_BULK_REF_C1})
    void testGetBulkSubmissionBySdtRef(String sdtRef) {
        IBulkCustomer bulkCustomer = new BulkCustomer();

        IBulkSubmission bulkSubmission =
            mockBulkSubmissionDao.getBulkSubmissionBySdtRef(bulkCustomer, sdtRef, DATA_RETENTION_PERIOD);

        assertNotNull(bulkSubmission, "BulkSubmission should not be null");
        assertNotNull(bulkSubmission.getCreatedDate(), "BulkSubmission created date should not be null");
        assertEquals(sdtRef,
                     bulkSubmission.getCustomerReference(),
                     "BulkSubmission has unexpected customer reference");
    }

    @Test
    void testGetBulkSubmissionBySdtRefNotFound() {
        IBulkCustomer bulkCustomer = new BulkCustomer();

        IBulkSubmission bulkSubmission =
            mockBulkSubmissionDao.getBulkSubmissionBySdtRef(bulkCustomer,
                                                            SDT_BULK_REF_NOT_FOUND,
                                                            DATA_RETENTION_PERIOD);

        assertNull(bulkSubmission, "BulkSubmission should be null");
    }

    @Test
    void testSetBulkReferenceList() {
        List<String> bulkRefList = new ArrayList<>();
        bulkRefList.add(SDT_BULK_REF_TEST);

        mockBulkSubmissionDao.setBulkReferenceList(bulkRefList);

        List<String> actualBulkRefList = (List<String>) getAccessibleField(MockBulkSubmissionDao.class,
                                                                           "bulkReferenceList",
                                                                           List.class,
                                                                           mockBulkSubmissionDao);

        assertNotNull(actualBulkRefList, "BulkReference list should not be null");
        assertEquals(1, actualBulkRefList.size(), "BulkReference list has unexpected number of items");
        assertEquals(SDT_BULK_REF_TEST, actualBulkRefList.get(0), "BulkReference list contains unexpected item");
    }
}
