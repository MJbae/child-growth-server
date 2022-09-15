package mj.childGrowth.application;

import mj.childGrowth.domain.HeightAggregationRepository;
import mj.childGrowth.domain.HeightRequestAggregation;
import mj.childGrowth.domain.Sex;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HeightAggregationService {
    public final HeightAggregationRepository repository;

    public final HeightAnalysisService analysisService;


    public HeightAggregationService(HeightAggregationRepository repository, HeightAnalysisService analysisService) {
        this.repository = repository;
        this.analysisService = analysisService;
    }

    public void saveAggregation() {
        int maleCount = analysisService.getCountBy(Sex.MALE);
        int femaleCount = analysisService.getCountBy(Sex.FEMALE);
        int totalRequestCount = maleCount + femaleCount;
        float monthAverage = analysisService.calculateMonthAverage(totalRequestCount);
        float heightAverage = analysisService.calculateHeightAverage(totalRequestCount);

        repository.save(new HeightRequestAggregation(totalRequestCount, maleCount, femaleCount,
                monthAverage, heightAverage));
    }
}
