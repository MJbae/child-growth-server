//package mj.childGrowth.config;
//
//import mj.childGrowth.domain.HeightRangeRequestLogRepository;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    public final HeightRangeRequestLogRepository repository;
//
//    public WebConfig(HeightRangeRequestLogRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RequestLoggingInterceptor(repository))
//                .addPathPatterns("/api/height/**");
//    }
//
//    @Bean
//    public FilterRegistrationBean setSpeedMeasurementFilterRegistration() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SpeedMeasuringFilter());
//        filterRegistrationBean.setOrder(1);
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean setRequestLoggingFilterRegistration() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new RequestLoggingFilter());
//        filterRegistrationBean.addUrlPatterns("/api/height/*");
//        filterRegistrationBean.setOrder(2);
//        return filterRegistrationBean;
//    }
//}
