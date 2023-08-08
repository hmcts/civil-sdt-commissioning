package uk.gov.moj.sdt.producers.comx.config;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import uk.gov.moj.sdt.interceptors.service.RequestDaoService;
import uk.gov.moj.sdt.producers.comx.sdtws.SdtEndpointPortType;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;
import uk.gov.moj.sdt.utils.concurrent.api.IInFlightMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CxfConfigTest extends AbstractSdtUnitTestBase {

    private static final String URL_MAPPING_COMMISSIONING = "/producers-comx/service/*";

    private static final String INTERCEPTOR_TYPE_IN = "In";
    private static final String INTERCEPTOR_TYPE_OUT = "Out";
    private static final String INTERCEPTOR_TYPE_OUT_FAULT = "Out Fault";

    private static final String ENDPOINT_ADDRESS = "/sdtapi";

    @Mock
    LoggingFeature mockLoggingFeature;

    @Mock
    SdtEndpointPortType mockSdtEndpointPortType;

    @Mock
    RequestDaoService mockRequestDaoService;

    private CxfConfig cxfConfig;

    @Override
    protected void setUpLocalTests() {
        cxfConfig = new CxfConfig();
    }

    @Test
    void testCxfServlet() {
        ServletRegistrationBean<CXFServlet> cxfServlet = cxfConfig.cxfServlet();

        assertNotNull(cxfServlet, "CxfServlet should not be null");

        Collection<String> actualUrlMappings = cxfServlet.getUrlMappings();
        assertNotNull(actualUrlMappings, "UrlMappings collection should not be null");
        assertEquals(1,
                     actualUrlMappings.size(),
                     "UrlMappings collection contains unexpected number of items");

        String actualUrlMapping = actualUrlMappings.iterator().next();
        assertEquals(URL_MAPPING_COMMISSIONING, actualUrlMapping, "UrlMapping has unexpected value");
    }

    @Test
    void testSpringBus() {
        SpringBus springBus = cxfConfig.springBus(mockLoggingFeature);

        assertNotNull(springBus, "SpringBus should not be null");

        Collection<Feature> actualFeatures = springBus.getFeatures();
        assertNotNull(actualFeatures, "Features collection should not be null");
        assertEquals(1, actualFeatures.size(), "Features collection contains unexpected number of items");

        Feature actualFeature = actualFeatures.iterator().next();
        assertSame(mockLoggingFeature, actualFeature, "Features collection contains unexpected feature");
    }

    @Test
    void testLoggingFeature() {
        LoggingFeature loggingFeature = cxfConfig.loggingFeature();

        assertNotNull(loggingFeature, "LoggingFeature should not be null");
    }

    @Test
    void testConcurrencyMap() {
        Map<String, IInFlightMessage> concurrencyMap = cxfConfig.concurrencyMap();

        assertNotNull(concurrencyMap, "ConcurrencyMap should not be null");
        assertEquals(0, concurrencyMap.size(), "ConcurrencyMap contains unexpected number of items");
    }

    @Test
    void testSdtEndpoint() {
        EndpointImpl sdtEndpoint = (EndpointImpl) cxfConfig.sdtEndpoint(mockSdtEndpointPortType, mockRequestDaoService);

        assertNotNull(sdtEndpoint, "Endpoint should not be null");

        List<String> expectedInInterceptors = new ArrayList<>();
        expectedInInterceptors.add("XmlInboundInterceptor");
        expectedInInterceptors.add("SdtUnmarshallInterceptor");
        expectedInInterceptors.add("ServiceRequestInboundInterceptor");
        assertInterceptors(INTERCEPTOR_TYPE_IN,
                           expectedInInterceptors, sdtEndpoint.getInInterceptors());

        List<String> expectedOutInterceptors = new ArrayList<>();
        expectedOutInterceptors.add("CacheSetupOutboundInterceptor");
        expectedOutInterceptors.add("XmlOutboundInterceptor");
        expectedOutInterceptors.add("ServiceRequestOutboundInterceptor");
        expectedOutInterceptors.add("CacheEndOutboundInterceptor");
        assertInterceptors(INTERCEPTOR_TYPE_OUT,
                           expectedOutInterceptors, sdtEndpoint.getOutInterceptors());

        List<String> expectedOutFaultInterceptors = new ArrayList<>();
        expectedOutFaultInterceptors.add("FaultOutboundInterceptor");
        assertInterceptors(INTERCEPTOR_TYPE_OUT_FAULT,
                           expectedOutFaultInterceptors, sdtEndpoint.getOutFaultInterceptors());

        List<String> expectedProperties = new ArrayList<>();
        expectedProperties.add("schema-validation-enabled");
        expectedProperties.add("disable.outputstream.optimization");
        expectedProperties.add("soap.env.ns.map");
        assertProperties(expectedProperties, sdtEndpoint.getProperties());

        assertEquals(ENDPOINT_ADDRESS, sdtEndpoint.getAddress(), "Enpoint has unexpected address");
    }

    private void assertInterceptors(String interceptorType,
                                    List<String> expectedInterceptors,
                                    List<Interceptor<? extends Message>> interceptors) {
        assertNotNull(interceptors, interceptorType + " interceptors should not be null");

        int numExpectedInterceptors = expectedInterceptors.size();

        assertEquals(numExpectedInterceptors,
                     interceptors.size(),
                     "Unexpected number of " + interceptorType + " interceptors");

        for (int i = 0; i < numExpectedInterceptors; i++) {
            assertEquals(expectedInterceptors.get(i),
                         interceptors.get(i).getClass().getSimpleName(),
                         "Unexpected interceptor found in " + interceptorType + " interceptors");
        }
    }

    private void assertProperties(List<String> expectedProperties, Map<String, Object> properties) {
        assertNotNull(properties, "Properties should not be null");

        assertEquals(expectedProperties.size(),
                     properties.size(),
                     "Unexpected number of properties");

        for (String expectedProperty : expectedProperties) {
            assertTrue(properties.containsKey(expectedProperty),
                       "Properties does not contain " + expectedProperty + " property");
        }
    }
}
