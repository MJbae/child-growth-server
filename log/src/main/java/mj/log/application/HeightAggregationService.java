package mj.log.application;

import mj.core.domain.Sex;
import mj.log.domain.HeightAggregationRepository;
import mj.log.domain.HeightRangeRequestLogRepository;
import mj.log.domain.HeightRequestAggregation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class HeightAggregationService {

    private final HeightAggregationRepository repository;
    private final HeightRangeRequestLogRepository logRepository;

    public HeightAggregationService(HeightAggregationRepository repository,
                                    HeightRangeRequestLogRepository logRepository) {
        this.repository = repository;
        this.logRepository = logRepository;
    }

    public void saveAggregation() {
        int maleCount = getCountBy(Sex.MALE);
        int femaleCount = getCountBy(Sex.FEMALE);
        int totalRequestCount = maleCount + femaleCount;
        float monthAverage = calculateMonthAverage(totalRequestCount);
        float heightAverage = calculateHeightAverage(totalRequestCount);

        Optional<HeightRequestAggregation> aggregation = repository.findFirstByCreatedAtBefore(LocalDateTime.now());

        if (aggregation.isEmpty()) {
            repository.save(new HeightRequestAggregation(totalRequestCount, maleCount, femaleCount,
                    monthAverage, heightAverage));
            return;
        }

        HeightRequestAggregation aggregationPresent = aggregation.get();
        aggregationPresent.updateAll(totalRequestCount, maleCount, femaleCount,
                monthAverage, heightAverage);

        repository.save(aggregationPresent);
    }

    private int getCountBy(Sex sex) {
        return logRepository.countBySex(sex);
    }

    private float calculateMonthAverage(int countAll) {
        Optional<Float> sumOfMonth = logRepository.addAllMonth();

        if (sumOfMonth.isEmpty() || countAll == 0) {
            return 0;
        }

        return sumOfMonth.get() / countAll;
    }

    private float calculateHeightAverage(int countAll) {
        Optional<Float> sumOfHeight = logRepository.addAllHeight();

        if (sumOfHeight.isEmpty() || countAll == 0) {
            return 0;
        }

        return sumOfHeight.get() / countAll;
    }
}
