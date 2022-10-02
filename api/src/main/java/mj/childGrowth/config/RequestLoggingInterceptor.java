package mj.childGrowth.config;

import mj.childGrowth.MqProducer;
import mj.childGrowth.domain.HeightRequestLog;
import mj.childGrowth.domain.HeightRangeRequestLogRepository;
import mj.childGrowth.domain.Sex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.InvalidPropertiesFormatException;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
    public final HeightRangeRequestLogRepository repository;
    public final MqProducer producer;

    public RequestLoggingInterceptor(HeightRangeRequestLogRepository repository, MqProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        producer.run(req.getParameter("height"), req.getParameter("monthAfterBirth"), req.getParameter("sex"));
        return true;
    }
}
