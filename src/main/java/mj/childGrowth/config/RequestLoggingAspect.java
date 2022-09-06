package mj.childGrowth.config;


import mj.childGrowth.domain.HeightRangeRequestLog;
import mj.childGrowth.domain.HeightRangeRequestLogRepository;
import mj.childGrowth.domain.Sex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
public class RequestLoggingAspect {
    private final HeightRangeRequestLogRepository repository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    RequestLoggingAspect(HeightRangeRequestLogRepository repository) {
        this.repository = repository;
    }

    @Pointcut("within(mj.childGrowth.controller.HeightAnalysisController)")
    public void onRequest() {
    }

    @Around("mj.childGrowth.config.RequestLoggingAspect.onRequest()")
    public Object requestLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Float height = Float.parseFloat(req.getParameter("height"));
        Integer monthAfterBirth = Integer.parseInt(req.getParameter("monthAfterBirth"));
        Sex sex = Sex.valueOf(req.getParameter("sex").toUpperCase());

        logger.info("Logging Request Parameters in Interceptor: height={}, monthAfterBirth={}, sex={}",
                height, monthAfterBirth, sex);

        repository.save(new HeightRangeRequestLog(height, monthAfterBirth, sex));

        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

}
