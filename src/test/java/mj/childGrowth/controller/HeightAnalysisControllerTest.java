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

    private HeightAnalysis analysis;

    @Nested
    @DisplayName("range 메소드는")
    class Describe_range {
        @BeforeEach
        void setUp() {
//            analysis = new HeightAnalysis(1, "male", 1, 10, 173);
            given(repository.findAllByMonthAndSexAndHeight(227, (float) 179.5, "male"))
                    .willReturn(List.of());
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
