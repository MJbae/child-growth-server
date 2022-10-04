package mj.childGrowth.application;

import mj.childGrowth.ViewAggMqProducer;
import mj.childGrowth.domain.Sex;
import org.springframework.stereotype.Service;

@Service
public class HeightAggregationService {

    private final HeightAnalysisService analysisService;
    private final ViewAggMqProducer producer;

    public HeightAggregationService(HeightAnalysisService analysisService,
                                    ViewAggMqProducer producer) {
        this.analysisService = analysisService;
        this.producer = producer;
    }

    public void saveAggregation() {
        int maleCount = analysisService.getCountBy(Sex.MALE);
        int femaleCount = analysisService.getCountBy(Sex.FEMALE);
        int totalRequestCount = maleCount + femaleCount;
        float monthAverage = analysisService.calculateMonthAverage(totalRequestCount);
        float heightAverage = analysisService.calculateHeightAverage(totalRequestCount);

        producer.run(String.valueOf(totalRequestCount),
                String.valueOf(maleCount),
                String.valueOf(femaleCount),
                String.valueOf(monthAverage),
                String.valueOf(heightAverage));
    }
}
