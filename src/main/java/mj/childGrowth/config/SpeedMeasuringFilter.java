package mj.childGrowth.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Order(1)
public class SpeedMeasuringFilter extends AbstractRequestLoggingFilter {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    final String REQUESTED_AT = "requestedAt";
    final String SLOW_TAG = "SLOW_API: ";

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        request.setAttribute(REQUESTED_AT, System.currentTimeMillis());
        logger.debug(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        Object requestedAt = request.getAttribute(REQUESTED_AT);

        if (requestedAt == null) {
            logger.debug(message);
            return;
        }

        double elapsed = (System.currentTimeMillis() - (long) requestedAt) / 1000.0;
        String prefixTag = (elapsed > 1) ? SLOW_TAG : "";

        logger.info(prefixTag + " " + message + " elapsed=" + elapsed);
    }
}
