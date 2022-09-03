package mj.childGrowth;

import mj.childGrowth.config.RequestLoggingFilter;
import mj.childGrowth.config.SpeedMeasuringFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChildGrowthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildGrowthApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean setSpeedMeasurementFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SpeedMeasuringFilter());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean setRequestLoggingFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new RequestLoggingFilter());
        filterRegistrationBean.addUrlPatterns("/api/height/*");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
}
