package mj.childGrowth.controller;

import mj.childGrowth.controller.dto.AnalysisResponseData;
import mj.childGrowth.controller.dto.HeightResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeightAnalysisController {

    @RequestMapping("/height/range")
    public AnalysisResponseData range() {

        Integer RANGE_INDEX = 1;

        List<HeightResponseData> range = new ArrayList<>();

        return new AnalysisResponseData(RANGE_INDEX, range);
    }
}