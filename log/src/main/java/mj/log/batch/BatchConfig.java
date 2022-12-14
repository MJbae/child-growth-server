package mj.log.batch;


import lombok.RequiredArgsConstructor;
import mj.log.application.HeightAggregationService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    public final HeightAggregationService service;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job tutorialJob() {
        return jobBuilderFactory.get("LogJob")
                .start(tutorialStep())
                .build();
    }

    @Bean
    public Step tutorialStep() {
        return stepBuilderFactory.get("LogStep")
                .tasklet(new LogTasklet(service))
                .build();
    }
}
