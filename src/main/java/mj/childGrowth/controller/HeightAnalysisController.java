package mj.childGrowth.controller;

import mj.childGrowth.application.HeightAnalysisService;
import mj.childGrowth.controller.dto.AnalysisResponseData;
import mj.childGrowth.controller.dto.HeightResponseData;
import mj.childGrowth.controller.validator.HeightValidation;
import mj.childGrowth.controller.validator.MonthValidation;
import mj.childGrowth.controller.validator.SexValidation;
import mj.childGrowth.domain.Sex;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HeightAnalysisController {
    public final HeightAnalysisService service;

    public HeightAnalysisController(HeightAnalysisService service) {
        this.service = service;
    }

    @RequestMapping("/api/height/range")
    public AnalysisResponseData range(@RequestParam @MonthValidation Integer monthAfterBirth,
                                      @RequestParam @HeightValidation Float height,
                                      @RequestParam @SexValidation String sex) {

        List<HeightResponseData> range = service.showAllBy(monthAfterBirth, height, Sex.valueOf(sex.toUpperCase()));
        int rangeIndex = service.getRangeIndex(height, range);


        return new AnalysisResponseData(rangeIndex, range);
    }


}
