package mj.childGrowth.controller;

import mj.childGrowth.domain.HeightRangeRequestLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(HomeController.class)
@DisplayName("HomeController")
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeightRangeRequestLogRepository repository;

    private final String REQUEST_URL = "/api";

    private final String WELCOME_MESSAGE = "Welcome to Child Growth API Server";

    @Nested
    @DisplayName("home 메소드는")
    class Describe_home {
        @Test
        @DisplayName("HTTP Status Code 200 OK로 응답한다")
        void it_responds_with_200_ok() throws Exception {
            mockMvc.perform(get(REQUEST_URL))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("환영메시지를 반환한다")
        void it_returns_welcome_message() throws Exception {
            mockMvc.perform(get(REQUEST_URL))
                    .andExpect(content().string(containsString(WELCOME_MESSAGE)));
        }
    }
}
