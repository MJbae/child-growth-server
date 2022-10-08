package mj.childGrowth.batch;

import mj.childGrowth.application.HeightAggregationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class LogTasklet implements Tasklet {
    public final HeightAggregationService service;

    public LogTasklet(HeightAggregationService service) {
        this.service = service;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        logger.info("-- Execute Log Aggregation --");
        service.saveAggregation();
        return RepeatStatus.FINISHED;
    }
}
