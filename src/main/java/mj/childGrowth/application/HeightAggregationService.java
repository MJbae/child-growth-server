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
        int maleCount = analysisService.getCountBySex(Sex.MALE);
        int femaleCount = analysisService.getCountBySex(Sex.FEMALE);
        int totalRequest = maleCount + femaleCount;
//        float monthAverage;
//        float heightAverage;
        repository.save(new HeightRequestAggregation(totalRequest, maleCount, femaleCount));
    }
}
