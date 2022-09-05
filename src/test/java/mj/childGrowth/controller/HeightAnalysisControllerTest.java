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

    @Nested
    @DisplayName("range 메소드는")
    class Describe_range {
        @BeforeEach
        void setUp() {
            HeightResponseData firstAnalysis = new HeightResponseData(10, (float) 167.5);
            HeightResponseData secondAnalysis = new HeightResponseData(25, (float) 170.8);
            HeightResponseData thirdAnalysis = new HeightResponseData(50, (float) 174.5);
            HeightResponseData forthAnalysis = new HeightResponseData(75, (float) 180.4);
            HeightResponseData fifthAnalysis = new HeightResponseData(90, (float) 183.9);

            given(service.showAllBy(227, (float) 179.5, Sex.MALE))
                    .willReturn(List.of(firstAnalysis, secondAnalysis, thirdAnalysis, forthAnalysis, fifthAnalysis));


            given(service.getRangeIndex((float) 179.5, List.of(firstAnalysis, secondAnalysis, thirdAnalysis, forthAnalysis, fifthAnalysis)))
                    .willReturn(3);
        }

        @Test
        @DisplayName("HTTP Status Code 200 OK 응답한다")
        void it_responds_with_200_ok() throws Exception {
            mockMvc.perform(get("/height/range")
                            .param("monthAfterBirth", "227")
                            .param("sex", "male")
                            .param("height", "179.5")
                    )
                    .andExpect(status().isOk());
        }
    }
}
