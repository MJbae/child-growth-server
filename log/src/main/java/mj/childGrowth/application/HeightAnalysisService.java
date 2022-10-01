package mj.childGrowth.application;

import mj.childGrowth.controller.dto.HeightResponseData;
import mj.childGrowth.domain.HeightAnalysisRepository;
import mj.childGrowth.domain.HeightRangeRequestLogRepository;
import mj.childGrowth.domain.Sex;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class HeightAnalysisService {
    public final HeightAnalysisRepository repository;
    public final HeightRangeRequestLogRepository logRepository;

    public HeightAnalysisService(HeightAnalysisRepository repository, HeightRangeRequestLogRepository logRepository) {
        this.repository = repository;
        this.logRepository = logRepository;
    }

    public int getRangeIndex(Float height, List<HeightResponseData> range) {
        int rangeIndex = 0;
        for (HeightResponseData analysis : range) {
            if (analysis.getHeight() > height) {
                break;
            }
            rangeIndex++;
        }
        return rangeIndex;
    }

    public List<HeightResponseData> showAllBy(Integer monthAfterBirth, Float height, Sex sex) {
        return repository.findAllByMonthAndSex(monthAfterBirth, sex)
                .stream().map(heightAnalysis -> new HeightResponseData(heightAnalysis.getPercentile(), heightAnalysis.getHeight()))
                .sorted(Comparator.comparingInt(HeightResponseData::getPercentile))
                .collect(Collectors.toList());
    }

    public int getCountBy(Sex sex) {
        return logRepository.countBySex(sex);
    }

    public float calculateMonthAverage(int countAll) {
        Optional<Float> sumOfMonth = logRepository.addAllMonth();

        if (sumOfMonth.isEmpty() || countAll == 0) {
            return 0;
        }

        return sumOfMonth.get() / countAll;
    }

    public float calculateHeightAverage(int countAll) {
        Optional<Float> sumOfHeight = logRepository.addAllHeight();

        if (sumOfHeight.isEmpty() || countAll == 0) {
            return 0;
        }

        return sumOfHeight.get() / countAll;
    }
}
