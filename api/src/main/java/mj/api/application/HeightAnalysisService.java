package mj.api.application;

import mj.api.controller.dto.HeightResponseData;
import mj.api.domain.HeightAnalysisRepository;
import mj.core.domain.Sex;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HeightAnalysisService {
    public final HeightAnalysisRepository repository;

    public HeightAnalysisService(HeightAnalysisRepository repository) {
        this.repository = repository;
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


}
