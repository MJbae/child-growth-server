package mj.childGrowth.application;

import mj.childGrowth.domain.HeightAggregationRepository;
import mj.childGrowth.domain.HeightRequestAggregation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HeightAggregationService {
    public final HeightAggregationRepository repository;

    public HeightAggregationService(HeightAggregationRepository repository) {
        this.repository = repository;
    }

    public void saveAggregation() {
        repository.save(new HeightRequestAggregation(10));
    }

}
