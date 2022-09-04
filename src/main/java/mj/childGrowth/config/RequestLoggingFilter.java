
package mj.childGrowth.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestLoggingFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        logger.info("Logging Request Parameters: height={}, monthAfterBirth={}, sex={}",
                req.getParameter("height"), req.getParameter("monthAfterBirth"),
                req.getParameter("sex"));

        chain.doFilter(request, response);
    }
}
