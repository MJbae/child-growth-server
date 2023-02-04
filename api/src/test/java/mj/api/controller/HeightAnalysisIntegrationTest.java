package mj.api.controller;

import mj.api.MqProducer;
import mj.api.application.HeightAnalysisService;
import mj.api.domain.HeightAnalysisRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(HeightAnalysisController.class)
@DisplayName("HeightAnalysisController")
class HeightAnalysisIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HeightAnalysisService service;

    @MockBean
    private HeightAnalysisRepository repository;

    @MockBean
    private MqProducer producer;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @MockBean
    private PlatformTransactionManager transactionManager;

    private final String REQUEST_URL = "/api/height/range";

    @Test
    @DisplayName("아이 키 성장 확인 요청 시 유효한 응답을 전달한다")
    void it_responds_with_200_ok() throws Exception {
        mockMvc.perform(get(REQUEST_URL)
                        .param("monthAfterBirth", "227")
                        .param("sex", "male")
                        .param("height", "179.5")
                )
                .andExpect(status().isOk());
    }

}
