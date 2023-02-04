package mj.api.controller;

import mj.api.controller.validator.HeightValidation;
import mj.api.controller.validator.MonthValidation;
import mj.api.controller.validator.SexValidation;
import mj.api.application.HeightAnalysisService;
import mj.api.controller.dto.AnalysisResponseData;
import mj.api.controller.dto.HeightResponseData;
import mj.core.utils.TypeConverter;
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

        List<HeightResponseData> range = service.showAllBy(monthAfterBirth, sex);
        int rangeIndex = service.getRangeIndex(height, range);

        return new AnalysisResponseData(rangeIndex, range);
    }


}
