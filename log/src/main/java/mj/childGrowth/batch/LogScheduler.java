package mj.childGrowth.batch;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LogScheduler {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Job job;
    private final JobLauncher jobLauncher;

    // 하루에 한 번 실행
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000L)
    public void executeJob() {
        try {
            jobLauncher.run(
                    job,
                    new JobParametersBuilder()
                            .addString("datetime", LocalDateTime.now().toString())
                            .toJobParameters()  // job parameter 설정
            );
        } catch (JobExecutionException ex) {
            logger.info("exception in executing job: {}", ex.getMessage());
            ex.printStackTrace();
        }
    }

}