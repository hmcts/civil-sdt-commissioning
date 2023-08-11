package uk.gov.moj.sdt.producers.comx.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.jmx.support.MBeanRegistrationSupport;
import org.springframework.jmx.support.RegistrationPolicy;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;
import uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class ProducersComxConfigTest extends AbstractSdtUnitTestBase {

    private static final String BEAN_NAME_PROD_COMX_METRICS = "bean:name=sdtProducersComxMetrics";

    @Mock
    SdtMetricsMBean mockSdtMetricsMBean;

    private ProducersComxConfig producersComxConfig;

    @Override
    protected void setUpLocalTests() {
        producersComxConfig = new ProducersComxConfig();
    }

    @Test
    void testmBeanExporterProducerMetrics() {

        try (MockedStatic<SdtMetricsMBean> mockStaticSdtMetricsMBean = mockStatic(SdtMetricsMBean.class)) {
            mockStaticSdtMetricsMBean.when(SdtMetricsMBean::getMetrics).thenReturn(mockSdtMetricsMBean);

            MBeanExporter producerMetrics = producersComxConfig.mBeanExporterProducerMetrics();

            Map<String, Object> actualBeans = (Map<String, Object>) getAccessibleField(MBeanExporter.class,
                                                                                       "beans",
                                                                                       Map.class,
                                                                                       producerMetrics);
            assertNotNull(actualBeans, "Beans map should not be null");
            assertEquals(1, actualBeans.size(), "Beans map contains unexpected number of items");
            assertNotNull(actualBeans.get(BEAN_NAME_PROD_COMX_METRICS), "Beans map does not contain expected bean");

            MethodNameBasedMBeanInfoAssembler actualAssembler =
                (MethodNameBasedMBeanInfoAssembler) getAccessibleField(MBeanExporter.class,
                                                                       "assembler",
                                                                       MethodNameBasedMBeanInfoAssembler.class,
                                                                       producerMetrics);
            assertNotNull(actualAssembler, "Assembler should not be null");
            Set<String> actualManagedMethods =
                (Set<String>) getAccessibleField(MethodNameBasedMBeanInfoAssembler.class,
                                                 "managedMethods",
                                                 Set.class,
                                                 actualAssembler);
            assertManagedMethods(actualManagedMethods);

            RegistrationPolicy actualRegistrationPolicy =
                (RegistrationPolicy) getAccessibleField(MBeanRegistrationSupport.class,
                                                        "registrationPolicy",
                                                        RegistrationPolicy.class,
                                                        producerMetrics);
            assertEquals(RegistrationPolicy.REPLACE_EXISTING.name(),
                         actualRegistrationPolicy.name(),
                         "Unexpected registration policy");

            mockStaticSdtMetricsMBean.verify(SdtMetricsMBean::getMetrics);
        }
    }

    private void assertManagedMethods(Set<String> managedMethods) {
        assertNotNull(managedMethods, "Managed methods should not be null");

        List<String> expectedManagedMethods = new ArrayList<>();
        expectedManagedMethods.add("getTime");
        expectedManagedMethods.add("getOsStats");
        expectedManagedMethods.add("getBulkSubmitStats");
        expectedManagedMethods.add("getBulkFeedbackStats");
        expectedManagedMethods.add("getSubmitQueryStats");
        expectedManagedMethods.add("getStatusUpdateStats");
        expectedManagedMethods.add("getDomainObjectsStats");
        expectedManagedMethods.add("getDatabaseCallsStats");
        expectedManagedMethods.add("getDatabaseReadsStats");
        expectedManagedMethods.add("getDatabaseWritesStats");
        expectedManagedMethods.add("getActiveCustomersStats");
        expectedManagedMethods.add("getRequestQueueStats");
        expectedManagedMethods.add("getTargetAppStats");
        expectedManagedMethods.add("getErrorStats");
        expectedManagedMethods.add("getLastRefStats");
        expectedManagedMethods.add("getPerformanceLoggingString");
        expectedManagedMethods.add("uncache");
        expectedManagedMethods.add("setPerformanceLoggingFlags");
        expectedManagedMethods.add("reset");
        expectedManagedMethods.add("dumpMetrics");

        assertEquals(expectedManagedMethods.size(),
                     managedMethods.size(),
                     "Unexpected number of managed methods");

        for (String expectedManagedMethod : expectedManagedMethods) {
            assertTrue(managedMethods.contains(expectedManagedMethod),
                       "Managed methods does not contain " + expectedManagedMethod + " method");
        }
    }
}
