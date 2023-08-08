package uk.gov.moj.sdt.producers.comx.dao;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import uk.gov.moj.sdt.domain.BulkCustomer;
import uk.gov.moj.sdt.domain.ServiceRequest;
import uk.gov.moj.sdt.domain.api.IBulkCustomer;
import uk.gov.moj.sdt.domain.api.IDomainObject;
import uk.gov.moj.sdt.domain.api.IServiceRequest;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static ch.qos.logback.classic.Level.ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class MockGenericDaoTest extends AbstractSdtUnitTestBase {

    private static final long SERVICE_REQUEST_ID = 4L;
    private static final long BULK_CUSTOMER_ID = 8L;

    private static final String SEQUENCE_NAME_TEST = "testSequenceName";

    @Mock
    Supplier<CriteriaQuery<BulkCustomer>> bulkCustomerCriteriaQuerySupplier;

    private MockGenericDao mockGenericDao;

    @Override
    protected void setUpLocalTests() {
        mockGenericDao = new MockGenericDao();
    }

    @Test
    void testFetchServiceRequest() {
        IServiceRequest serviceRequest = mockGenericDao.fetch(IServiceRequest.class, SERVICE_REQUEST_ID);
        assertNotNull(serviceRequest, "fetch should return non-null ServiceRequest");
        assertEquals(SERVICE_REQUEST_ID, serviceRequest.getId());
    }

    @Test
    void testFetchNonServiceRequest() {
        IBulkCustomer bulkCustomer = mockGenericDao.fetch(IBulkCustomer.class, BULK_CUSTOMER_ID);
        assertNull(bulkCustomer, "fetch should return null BulkCustomer");
    }

    @Test
    void testQueryCriteriaQuerySupplier() {
        IDomainObject[] bulkCustomers = mockGenericDao.query(BulkCustomer.class, bulkCustomerCriteriaQuerySupplier);
        assertNotNull(bulkCustomers, "query should return non-null BulkCustomer array");
        assertEquals(0, bulkCustomers.length, "BulkCustomer array has unexpected number of items");
    }

    @Test
    void testQuery() {
        IDomainObject[] bulkCustomers = mockGenericDao.query(BulkCustomer.class);
        assertNotNull(bulkCustomers, "query should return non-null BulkCustomer array");
        assertEquals(0, bulkCustomers.length, "BulkCustomer array has unexpected number of items");
    }

    @Test
    void testPersistServiceRequest() {
        Logger logger = (Logger) LoggerFactory.getLogger(MockGenericDao.class);

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        IServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(SERVICE_REQUEST_ID);

        mockGenericDao.persist(serviceRequest);

        List<ILoggingEvent> logList = listAppender.list;
        String actualLogMessage = "";
        if (!logList.isEmpty()) {
            actualLogMessage = logList.get(0).getFormattedMessage();
        }
        logger.detachAndStopAllAppenders();

        String logMessage =
            "SDT Commissioning: service request id [" + SERVICE_REQUEST_ID + "], bulk customer [null], " +
                "bulk reference [null], request type [null], hostname [null], request payload [null], " +
                "response payload [null]";

        assertEquals(SERVICE_REQUEST_ID, serviceRequest.getId(), "ServiceRequest has unexpected id");
        assertEquals(logMessage, actualLogMessage, "Expected message not found in log");
    }

    @Test
    void testPersistServiceRequestNoLogging() {
        Logger logger = (Logger) LoggerFactory.getLogger(MockGenericDao.class);
        Level originalLogLevel = logger.getLevel();
        logger.setLevel(ERROR);

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        IServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(SERVICE_REQUEST_ID);

        mockGenericDao.persist(serviceRequest);

        int logSize = listAppender.list.size();

        // Reset logger level to original value to ensure that it is in expected state for other tests
        logger.setLevel(originalLogLevel);

        logger.detachAndStopAllAppenders();

        assertEquals(SERVICE_REQUEST_ID, serviceRequest.getId(), "ServiceRequest has unexpected id");
        assertEquals(0, logSize, "Log contains an unexpected number of messages");
    }

    @Test
    void testPersistNonServiceRequest() {
        IBulkCustomer bulkCustomer = new BulkCustomer();
        bulkCustomer.setId(BULK_CUSTOMER_ID);

        mockGenericDao.persist(bulkCustomer);

        assertEquals(BULK_CUSTOMER_ID, bulkCustomer.getId(), "BulkCustomer has unexpected id");
    }

    @Test
    void testGetNextSequenceValue() {
        long nextSeqValue = mockGenericDao.getNextSequenceValue(SEQUENCE_NAME_TEST);
        assertEquals(0, nextSeqValue, "Next sequence value should be 0");
    }

    @Test
    void testQueryAsList() {
        List<BulkCustomer> bulkCustomerList =
            mockGenericDao.queryAsList(BulkCustomer.class, bulkCustomerCriteriaQuerySupplier);
        assertNotNull(bulkCustomerList, "queryAsList should return non-null list of BulkCustomers");
        assertEquals(0, bulkCustomerList.size(), "BulkCustomer list has unexpected number of items");
    }

    @Test
    void testUniqueResult() {
        BulkCustomer bulkCustomer = mockGenericDao.uniqueResult(BulkCustomer.class, bulkCustomerCriteriaQuerySupplier);
        assertNull(bulkCustomer, "uniqueResult should return null BulkCustomer");
    }

    @Test
    void testQueryAsCount() {
        long count = mockGenericDao.queryAsCount(BulkCustomer.class, bulkCustomerCriteriaQuerySupplier);
        assertEquals(0, count, "queryAsCount should return 0");
    }

    @Test
    void testGetEntityManager() {
        EntityManager entityManager = mockGenericDao.getEntityManager();
        assertNull(entityManager, "EntityManager should be null");
    }

    @Test
    void testPersistBulk() {
        IBulkCustomer bulkCustomer = new BulkCustomer();
        bulkCustomer.setId(BULK_CUSTOMER_ID);

        List<IBulkCustomer> bulkCustomers = new ArrayList<>();
        bulkCustomers.add(bulkCustomer);

        mockGenericDao.persistBulk(bulkCustomers);

        assertEquals(BULK_CUSTOMER_ID, bulkCustomer.getId(), "BulkCustomer has unexpected id");
    }
}
