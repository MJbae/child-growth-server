package mj.childGrowth.controller;

import mj.childGrowth.controller.dto.AnalysisResponseData;
import mj.childGrowth.controller.dto.HeightResponseData;
import mj.childGrowth.domain.HeightAnalysisRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HeightAnalysisController {
    public final HeightAnalysisRepository repository;

    public HeightAnalysisController(HeightAnalysisRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/height/range")
    public AnalysisResponseData range(@RequestParam Integer monthAfterBirth, @RequestParam Float height,
                                      @RequestParam String sex) {

        Integer RANGE_INDEX = 1;
        List<HeightResponseData> range = repository.findAllByMonthAndSexAndHeight(monthAfterBirth, height, sex)
                .stream().map(heightAnalysis -> new HeightResponseData(heightAnalysis.getPercentile(), heightAnalysis.getHeight()))
                .collect(Collectors.toList());

        return new AnalysisResponseData(RANGE_INDEX, range);
    }
}
