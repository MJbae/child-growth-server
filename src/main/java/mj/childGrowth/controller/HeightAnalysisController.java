package mj.childGrowth.controller;

import mj.childGrowth.application.HeightAnalysisService;
import mj.childGrowth.controller.dto.AnalysisResponseData;
import mj.childGrowth.controller.dto.HeightResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HeightAnalysisController {
    public final HeightAnalysisService service;

    public HeightAnalysisController(HeightAnalysisService service) {
        this.service = service;
    }

    @RequestMapping("/api/height/range")
    public AnalysisResponseData range(@RequestParam Integer monthAfterBirth, @RequestParam Float height,
                                      @RequestParam String sex) {

        List<HeightResponseData> range = service.showAllBy(monthAfterBirth, height, sex);
        int rangeIndex = service.getRangeIndex(height, range);


        return new AnalysisResponseData(rangeIndex, range);
    }


}
