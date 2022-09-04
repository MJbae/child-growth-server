package mj.childGrowth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        logger.info("Logging Request Parameters in Interceptor: height={}, monthAfterBirth={}, sex={}",
                req.getParameter("height"), req.getParameter("monthAfterBirth"),
                req.getParameter("sex"));

        return true;
    }
}
