package mj.api.application;

import mj.api.controller.dto.AnalysisResponseData;
import mj.api.controller.dto.HeightData;
import mj.api.domain.HeightAnalysisRepository;
import mj.core.utils.TypeConverter;
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


    public AnalysisResponseData showAllBy(Integer monthAfterBirth, String sex, Float height) {
        TypeConverter converter = new TypeConverter();

        List<HeightData> searchResult = repository.findAllByMonthAndSex(monthAfterBirth, converter.toSex(sex))
                .stream().map(heightAnalysis -> new HeightData(heightAnalysis.getPercentile(), heightAnalysis.getHeight()))
                .sorted(Comparator.comparingInt(HeightData::getPercentile))
                .collect(Collectors.toList());

        int range = getRangeIndex(height, searchResult);

        return new AnalysisResponseData(range, searchResult);
    }

    private int getRangeIndex(Float height, List<HeightData> range) {
        int rangeIndex = 0;
        for (HeightData data : range) {
            if (data.getHeight() > height) {
                break;
            }
            rangeIndex++;
        }
        return rangeIndex;
    }
}
