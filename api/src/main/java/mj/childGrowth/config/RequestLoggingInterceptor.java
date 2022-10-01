package mj.childGrowth.config;

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
    public final TypeConverter converter = new TypeConverter();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public RequestLoggingInterceptor(HeightRangeRequestLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        Float height = Float.parseFloat(req.getParameter("height"));
        Integer monthAfterBirth = Integer.parseInt(req.getParameter("monthAfterBirth"));
        try {
            Sex sex = converter.convert(req.getParameter("sex"));
            logger.info("Logging Request Parameters in Interceptor: height={}, monthAfterBirth={}, sex={}",
                    height, monthAfterBirth, sex);
            repository.save(new HeightRequestLog(height, monthAfterBirth, sex));
        } catch (IllegalArgumentException e) {
            logger.info("Invalid Request Param in Sex");
        }
        return true;
    }
}
