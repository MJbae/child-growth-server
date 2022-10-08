package mj.childGrowth.config;

import mj.childGrowth.MqProducer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
    public final MqProducer producer;

    public RequestLoggingInterceptor(MqProducer producer) {
        this.producer = producer;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        producer.run(req.getParameter("height"), req.getParameter("monthAfterBirth"), req.getParameter("sex"));
        return true;
    }
}
