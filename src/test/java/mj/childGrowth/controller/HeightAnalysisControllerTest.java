package mj.childGrowth.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(HeightAnalysisController.class)
@DisplayName("HeightAnalysisController")
class HeightAnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("range 메소드는")
    class Describe_range {

        @Test
        @DisplayName("HTTP Status Code 200 OK 응답한다")
        void it_responds_with_200_ok() throws Exception {
            mockMvc.perform(get("/height/range")
                            .param("year", "1991")
                            .param("month", "3")
                            .param("day", "26")
                            .param("sex", "male")
                            .param("height", "179.5")
                    )
                    .andExpect(status().isOk());
        }
    }
}
