package uk.gov.moj.sdt.producers.comx.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.moj.sdt.domain.BulkCustomer;
import uk.gov.moj.sdt.domain.BulkSubmission;
import uk.gov.moj.sdt.domain.TargetApplication;
import uk.gov.moj.sdt.domain.api.IBulkCustomer;
import uk.gov.moj.sdt.domain.api.IBulkSubmission;
import uk.gov.moj.sdt.domain.api.ITargetApplication;
import uk.gov.moj.sdt.producers.comx.utils.MockSdtBulkReferenceGenerator;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;
import uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
class MockBulkSubmissionServiceTest extends AbstractSdtUnitTestBase {

    private static final String TARGET_APP_CODE = "MCOL";

    private static final String SDT_BULK_REF = "MCOL-20230804131630-000000001";

    @Mock
    private MockSdtBulkReferenceGenerator mockMockSdtBulkReferenceGenerator;

    @Mock
    private SdtMetricsMBean mockSdtMetricsMBean;

    private MockBulkSubmissionService mockBulkSubmissionService;

    @Override
    protected void setUpLocalTests() {
        mockBulkSubmissionService = new MockBulkSubmissionService(mockMockSdtBulkReferenceGenerator);
    }

    @Test
    void testSaveBulkSubmission() {

        IBulkCustomer bulkCustomer = new BulkCustomer();
        bulkCustomer.setSdtCustomerId(1L);

        ITargetApplication targetApplication = new TargetApplication();
        targetApplication.setTargetApplicationCode(TARGET_APP_CODE);

        IBulkSubmission bulkSubmission = new BulkSubmission();
        bulkSubmission.setBulkCustomer(bulkCustomer);
        bulkSubmission.setTargetApplication(targetApplication);

        try (MockedStatic<SdtMetricsMBean> mockStaticSdtMetricsMBean = mockStatic(SdtMetricsMBean.class)) {
            mockStaticSdtMetricsMBean.when(SdtMetricsMBean::getMetrics).thenReturn(mockSdtMetricsMBean);

            when(mockMockSdtBulkReferenceGenerator.getSdtBulkReference(TARGET_APP_CODE)).thenReturn(SDT_BULK_REF);

            mockBulkSubmissionService.saveBulkSubmission(bulkSubmission);

            assertEquals(SDT_BULK_REF,
                         bulkSubmission.getSdtBulkReference(),
                         "BulkSubmission has unexpected SDT bulk reference");

            mockStaticSdtMetricsMBean.verify(SdtMetricsMBean::getMetrics);
            verify(mockSdtMetricsMBean).setLastBulkSubmitRef(SDT_BULK_REF);
        }
    }

    @Test
    void testSetBulkReferenceGenerator() {

        MockSdtBulkReferenceGenerator mockSdtBulkReferenceGenerator = new MockSdtBulkReferenceGenerator();

        mockBulkSubmissionService.setSdtBulkReferenceGenerator(mockSdtBulkReferenceGenerator);

        MockSdtBulkReferenceGenerator actualMockSdtBulkReferenceGenerator =
            (MockSdtBulkReferenceGenerator) getAccessibleField(MockBulkSubmissionService.class,
                                                               "sdtBulkReferenceGenerator",
                                                               MockSdtBulkReferenceGenerator.class,
                                                               mockBulkSubmissionService);
        assertSame(mockSdtBulkReferenceGenerator,
                   actualMockSdtBulkReferenceGenerator,
                   "MockBulkSubmissionService has unexpected MockSdtBulkReferenceGenerator");
    }
}
