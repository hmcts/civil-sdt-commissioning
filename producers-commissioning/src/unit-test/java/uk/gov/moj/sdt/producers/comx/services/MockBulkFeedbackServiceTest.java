package uk.gov.moj.sdt.producers.comx.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.moj.sdt.domain.BulkCustomer;
import uk.gov.moj.sdt.domain.BulkFeedbackRequest;
import uk.gov.moj.sdt.domain.BulkSubmission;
import uk.gov.moj.sdt.domain.api.IBulkCustomer;
import uk.gov.moj.sdt.domain.api.IBulkFeedbackRequest;
import uk.gov.moj.sdt.domain.api.IBulkSubmission;
import uk.gov.moj.sdt.producers.comx.utils.BulkFeedbackFactory;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;
import uk.gov.moj.sdt.utils.SdtContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MockBulkFeedbackServiceTest extends AbstractSdtUnitTestBase {

    private static final String BULK_FEEDBACK_REF_A1 = "MCOL_20130722000000_A00000001";
    private static final String BULK_FEEDBACK_REF_B1 = "MCOL_20130722000000_B00000001";
    private static final String BULK_FEEDBACK_REF_B2 = "MCOL_20130722000000_B00000002";
    private static final String BULK_FEEDBACK_REF_C1 = "MCOL_20130722000000_C00000001";

    private static final long SDT_CUST_ID = 1L;

    private static final String BULK_FEEDBACK_REF_TEST = "BulkFeedbackRefTest";

    @Mock
    private SdtContext mockSdtContext;

    private MockBulkFeedbackService mockBulkFeedbackService;

    @Override
    protected void setUpLocalTests() {
        mockBulkFeedbackService = new MockBulkFeedbackService(createBulkFeedbackFactory(),
                                                              createBulkFeedbackFactory(),
                                                              createBulkFeedbackFactory(),
                                                              createBulkFeedbackFactory());
    }

    @ParameterizedTest
    @ValueSource(strings = {BULK_FEEDBACK_REF_A1, BULK_FEEDBACK_REF_B1, BULK_FEEDBACK_REF_B2, BULK_FEEDBACK_REF_C1})
    void testGetBulkFeedback(String sdtBulkReference) {
        IBulkCustomer bulkCustomer = new BulkCustomer();
        bulkCustomer.setSdtCustomerId(SDT_CUST_ID);

        IBulkFeedbackRequest bulkFeedbackRequest = new BulkFeedbackRequest();
        bulkFeedbackRequest.setBulkCustomer(bulkCustomer);
        bulkFeedbackRequest.setSdtBulkReference(sdtBulkReference);

        try (MockedStatic<SdtContext> mockStaticSdtContext = mockStatic(SdtContext.class)) {
            mockStaticSdtContext.when(SdtContext::getContext).thenReturn(mockSdtContext);

            IBulkSubmission bulkSubmission = mockBulkFeedbackService.getBulkFeedback(bulkFeedbackRequest);

            assertNotNull(bulkSubmission, "BulkSubmission should not be null");
            assertEquals(sdtBulkReference,
                         bulkSubmission.getSdtBulkReference(),
                         "BulkSubmission has unexpected SDT bulk reference");

            mockStaticSdtContext.verify(SdtContext::getContext);
            verify(mockSdtContext).setTargetApplicationRespMap(any());
        }
    }

    @Test
    void testSetBulkFeedbackFactoryMap() {
        Map<String, BulkFeedbackFactory> bulkFeedbackFactoryMap = new HashMap<>();
        BulkFeedbackFactory bulkFeedbackFactory = createBulkFeedbackFactory();
        bulkFeedbackFactoryMap.put(BULK_FEEDBACK_REF_TEST, bulkFeedbackFactory);

        mockBulkFeedbackService.setBulkFeedbackFactoryMap(bulkFeedbackFactoryMap);

        Map<String, BulkFeedbackFactory> actualBulkFeedbackFactoryMap =
            (Map<String, BulkFeedbackFactory>) getAccessibleField(MockBulkFeedbackService.class,
                                                                  "bulkFeedbackFactoryMap",
                                                                  Map.class,
                                                                  mockBulkFeedbackService);
        assertNotNull(actualBulkFeedbackFactoryMap, "BulkFeedbackFactory map should not be null");
        assertEquals(1,
                     actualBulkFeedbackFactoryMap.size(),
                     "BulkFeedbackFactory map has unexpected number of items");

        BulkFeedbackFactory actualBulkFeedbackFactory = actualBulkFeedbackFactoryMap.get(BULK_FEEDBACK_REF_TEST);
        assertNotNull(actualBulkFeedbackFactory,
                      "BulkFeedbackFactory map does not contain expected BulkFeedbackFactory");
    }

    private BulkFeedbackFactory createBulkFeedbackFactory() {
        IBulkSubmission bulkSubmission = new BulkSubmission();

        return new BulkFeedbackFactory(bulkSubmission);
    }

}
