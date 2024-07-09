package uk.gov.hmcts.reform.sdt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.TimeZone;
import javax.annotation.PostConstruct;

// Disable auto configuration of data source as civil-sdt-commissioning doesn't use a database
@SpringBootApplication(scanBasePackages = {"uk.gov.moj.sdt", "uk.gov.hmcts.reform.sdt"}, exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
@SuppressWarnings("HideUtilityClassConstructor") // Spring needs a constructor, it's not a utility class
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
    }
}
