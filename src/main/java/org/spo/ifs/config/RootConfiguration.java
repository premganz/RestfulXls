package org.spo.ifs.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

/**
 * The root application context.
 * <p/>
 * Beans can also be configured by XML in root-context.xml which is imported by
 * this context class.
 * <p/>
 * Component scanning is also done to pickup any components other than
 *
 * @Controllers. @Controllers will be picked up by the SpringMVC context.
 */
@Configuration
@ImportResource({"classpath:META-INF/spring/root-context.xml"})
@Import({org.spo.ifs.config.JettyConfiguration.class, org.spo.ifs.config.SpringSecurityConfig.class})
@ComponentScan(basePackages = {"org.spo.ifs.template", 
		"org.spo.svc.xlaccess",
		"org.spo.svc.httpd",		
		"org.spo.svc.pages", 
		"org.spo.svc.pages.gateway",
		"org.spo.svc.pages.thread.exch",
		"org.spo.svc.pages.gateway",
		"org.spo.svc.xlaccess.test"},
        excludeFilters = {@ComponentScan.Filter(Controller.class),
                @ComponentScan.Filter(Configuration.class)})
public class RootConfiguration {

    /**
     * Allows access to environment properties.  eg @Value("${jetty.port}).
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * The metrics registry.
     */
    @Bean
    public MetricRegistry metricsRegistry() {
        return new MetricRegistry();
    }

    /**
     * The metrics health check registry.
     */
    @Bean
    public HealthCheckRegistry metricsHealthCheckRegistry() {
        return new HealthCheckRegistry();
    }
}
