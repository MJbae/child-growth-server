package mj.childGrowth.controller;

import mj.childGrowth.domain.HeightAnalysis;
import mj.childGrowth.domain.HeightAnalysisRepository;
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
    private HeightAnalysisRepository repository;

    @Nested
    @DisplayName("range 메소드는")
    class Describe_range {
        @BeforeEach
        void setUp() {
            HeightAnalysis firstAnalysis = new HeightAnalysis((long) 1, "male", 227, 10, (float) 167.5);
            HeightAnalysis secondAnalysis = new HeightAnalysis((long) 2, "male", 227, 25, (float) 170.8);
            HeightAnalysis thirdAnalysis = new HeightAnalysis((long) 3, "male", 227, 50, (float) 174.5);
            HeightAnalysis forthAnalysis = new HeightAnalysis((long) 4, "male", 227, 75, (float) 180.4);
            HeightAnalysis fifthAnalysis = new HeightAnalysis((long) 5, "male", 227, 90, (float) 183.9);

            given(repository.findAllByMonthAndSexAndHeight(227, (float) 179.5, "male"))
                    .willReturn(List.of(firstAnalysis, secondAnalysis, thirdAnalysis, forthAnalysis, fifthAnalysis));
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
