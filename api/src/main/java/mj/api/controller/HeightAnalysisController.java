package mj.api.controller;

import mj.api.controller.validator.HeightValidation;
import mj.api.controller.validator.MonthValidation;
import mj.api.controller.validator.SexValidation;
import mj.api.application.HeightAnalysisService;
import mj.api.controller.dto.AnalysisResponseData;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HeightAnalysisController {
    public final HeightAnalysisService service;

    public HeightAnalysisController(HeightAnalysisService service) {
        this.service = service;
    }

    @RequestMapping("/api/height/range")
    public AnalysisResponseData checkHeight(@RequestParam @MonthValidation Integer monthAfterBirth,
                                            @RequestParam @HeightValidation Float height,
                                            @RequestParam @SexValidation String sex) {

        return service.analyzeHeightPosition(monthAfterBirth, sex, height);
    }
}
