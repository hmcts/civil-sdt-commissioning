package uk.gov.moj.sdt.producers.comx.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.jmx.support.RegistrationPolicy;
import uk.gov.moj.sdt.utils.mbeans.SdtMetricsMBean;

@Configuration
@EnableAutoConfiguration
public class ProducersComxConfig {

    @Bean
    @Qualifier("sdtProducersComxMetrics")
    public MBeanExporter mBeanExporterProducerMetrics() {
        MBeanExporter mBeanExporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put("bean:name=sdtProducersComxMetrics", SdtMetricsMBean.getMetrics());
        mBeanExporter.setBeans(beans);
        MethodNameBasedMBeanInfoAssembler methodNameBasedMBeanInfoAssembler = new MethodNameBasedMBeanInfoAssembler();
        methodNameBasedMBeanInfoAssembler.setManagedMethods(
            "getTime",
            "getOsStats",
            "getBulkSubmitStats",
            "getBulkFeedbackStats",
            "getSubmitQueryStats",
            "getStatusUpdateStats",
            "getDomainObjectsStats",
            "getDatabaseCallsStats",
            "getDatabaseReadsStats",
            "getDatabaseWritesStats",
            "getActiveCustomersStats",
            "getRequestQueueStats",
            "getTargetAppStats",
            "getErrorStats",
            "getLastRefStats",
            "getPerformanceLoggingString",
            "uncache",
            "setPerformanceLoggingFlags",
            "reset",
            "dumpMetrics"
        );
        mBeanExporter.setAssembler(methodNameBasedMBeanInfoAssembler);
        mBeanExporter.setRegistrationPolicy(RegistrationPolicy.REPLACE_EXISTING);
        return mBeanExporter;
    }
}
