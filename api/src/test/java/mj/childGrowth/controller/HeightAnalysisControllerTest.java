package mj.childGrowth.controller;

import mj.childGrowth.application.HeightAnalysisService;
import mj.childGrowth.controller.dto.HeightResponseData;
import mj.childGrowth.domain.Sex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(HeightAnalysisController.class)
@DisplayName("HeightAnalysisController")
class HeightAnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeightAnalysisService service;

    @MockBean
    private HeightRangeRequestLogRepository repository;

    private final float FIRST_HEIGHT = (float) 167.5;

    private final float SECOND_HEIGHT = (float) 170.8;

    private final float THIRD_HEIGHT = (float) 174.5;

    private final float FORTH_HEIGHT = (float) 180.4;

    private final float FIFTH_HEIGHT = (float) 183.9;

    private final int MONTH_AFTER_BIRTH = 227;

    private final float HEIGHT_IN_REQUEST = (float) 179.5;

    private final String REQUEST_URL = "/api/height/range";

    @Nested
    @DisplayName("range 메소드는")
    class Describe_range {
        @BeforeEach
        void setUp() {
            HeightResponseData firstAnalysis = new HeightResponseData(10, FIRST_HEIGHT);
            HeightResponseData secondAnalysis = new HeightResponseData(25, SECOND_HEIGHT);
            HeightResponseData thirdAnalysis = new HeightResponseData(50, THIRD_HEIGHT);
            HeightResponseData forthAnalysis = new HeightResponseData(75, FORTH_HEIGHT);
            HeightResponseData fifthAnalysis = new HeightResponseData(90, FIFTH_HEIGHT);

            given(service.showAllBy(MONTH_AFTER_BIRTH, HEIGHT_IN_REQUEST, Sex.MALE))
                    .willReturn(List.of(firstAnalysis, secondAnalysis, thirdAnalysis, forthAnalysis, fifthAnalysis));


            given(service.getRangeIndex(HEIGHT_IN_REQUEST, List.of(firstAnalysis, secondAnalysis, thirdAnalysis, forthAnalysis, fifthAnalysis)))
                    .willReturn(3);
        }

        @Test
        @DisplayName("HTTP Status Code 200 OK 응답한다")
        void it_responds_with_200_ok() throws Exception {
            mockMvc.perform(get(REQUEST_URL)
                            .param("monthAfterBirth", "227")
                            .param("sex", "male")
                            .param("height", "179.5")
                    )
                    .andExpect(status().isOk());
        }
    }
}
