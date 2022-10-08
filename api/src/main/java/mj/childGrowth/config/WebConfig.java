package mj.childGrowth.config;

import mj.childGrowth.MqProducer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public final MqProducer producer;

    public WebConfig(MqProducer producer) {
        this.producer = producer;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLoggingInterceptor(producer))
                .addPathPatterns("/api/height/**");
    }

    @Bean
    public FilterRegistrationBean setSpeedMeasurementFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SpeedMeasuringFilter());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
