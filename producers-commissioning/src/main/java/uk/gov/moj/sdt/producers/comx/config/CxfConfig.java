package uk.gov.moj.sdt.producers.comx.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import uk.gov.moj.sdt.interceptors.in.SdtUnmarshallInterceptor;
import uk.gov.moj.sdt.interceptors.in.ServiceRequestInboundInterceptor;
import uk.gov.moj.sdt.interceptors.in.XmlInboundInterceptor;
import uk.gov.moj.sdt.interceptors.out.CacheEndOutboundInterceptor;
import uk.gov.moj.sdt.interceptors.out.CacheSetupOutboundInterceptor;
import uk.gov.moj.sdt.interceptors.out.FaultOutboundInterceptor;
import uk.gov.moj.sdt.interceptors.out.ServiceRequestOutboundInterceptor;
import uk.gov.moj.sdt.interceptors.out.XmlOutboundInterceptor;
import uk.gov.moj.sdt.interceptors.service.RequestDaoService;
import uk.gov.moj.sdt.producers.comx.sdtws.SdtEndpointPortType;
import uk.gov.moj.sdt.utils.concurrent.api.IInFlightMessage;

@Configuration
@EnableAutoConfiguration
@ComponentScan("uk.gov.moj.sdt")
@EnableCaching(proxyTargetClass = true)
public class CxfConfig {

    @Bean
    public ServletRegistrationBean<CXFServlet> cxfServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/producers-comx/service/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus(LoggingFeature loggingFeature) {
        SpringBus cxfBus = new SpringBus();
        cxfBus.getFeatures().add(loggingFeature);
        return cxfBus;
    }

    @Bean
    public LoggingFeature loggingFeature() {
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        return loggingFeature;
    }

    @Bean("concurrencyMap")
    public Map<String, IInFlightMessage> concurrencyMap() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public Endpoint sdtEndpoint(@Qualifier("ISdtEndpointPortType") SdtEndpointPortType sdtEndpointPortType,
                                RequestDaoService requestDaoService) {
        ServiceRequestInboundInterceptor serviceRequestInboundInterceptor = new ServiceRequestInboundInterceptor(requestDaoService);
        XmlInboundInterceptor xmlInboundInterceptor = new XmlInboundInterceptor();
        SdtUnmarshallInterceptor sdtUnmarshallInterceptor = new SdtUnmarshallInterceptor();

        EndpointImpl endpoint = new EndpointImpl(springBus(loggingFeature()), sdtEndpointPortType);
        List interceptors = new ArrayList<>();
        interceptors.add(xmlInboundInterceptor);
        interceptors.add(sdtUnmarshallInterceptor);
        interceptors.add(serviceRequestInboundInterceptor);
        endpoint.setInInterceptors(interceptors);

        CacheSetupOutboundInterceptor cacheSetupOutboundInterceptor = new CacheSetupOutboundInterceptor();
        XmlOutboundInterceptor xmlOutboundInterceptor = new XmlOutboundInterceptor();
        ServiceRequestOutboundInterceptor serviceRequestOutboundInterceptor = new ServiceRequestOutboundInterceptor(requestDaoService);
        CacheEndOutboundInterceptor cacheEndOutboundInterceptor = new CacheEndOutboundInterceptor();

        List outInceptors = new ArrayList<>();
        outInceptors.add(cacheSetupOutboundInterceptor);
        outInceptors.add(xmlOutboundInterceptor);
        outInceptors.add(serviceRequestOutboundInterceptor);
        outInceptors.add(cacheEndOutboundInterceptor);
        endpoint.setOutInterceptors(outInceptors);

        FaultOutboundInterceptor faultOutboundInterceptor = new FaultOutboundInterceptor(requestDaoService);
        List outFaultInterceptors = new ArrayList();
        outFaultInterceptors.add(faultOutboundInterceptor);
        endpoint.setOutFaultInterceptors(outFaultInterceptors);

        Map<String, Object> properties = new HashMap<>();
        properties.put("schema-validation-enabled", true);
        properties.put("disable.outputstream.optimization", true);
        Map<String, Object> nsMap = new HashMap<>();
        properties.put("soap.env.ns.map", nsMap);
        nsMap.put("base", "http://ws.sdt.moj.gov.uk/2013/sdt/BaseSchema");
        nsMap.put("breq", "http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema");
        nsMap.put("bresp", "http://ws.sdt.moj.gov.uk/2013/sdt/BulkResponseSchema");
        nsMap.put("bfreq", "http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackRequestSchema");
        nsMap.put("bfresp", "http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema");
        nsMap.put("qreq", "http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryRequestSchema");
        nsMap.put("qresp", "http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema");
        endpoint.setProperties(properties);
        endpoint.publish("/sdtapi");
        return endpoint;
    }
}
