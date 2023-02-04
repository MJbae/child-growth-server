package mj.api.application;

import mj.api.controller.dto.AnalysisResponseData;
import mj.api.domain.HeightAnalysisRepository;
import mj.core.domain.HeightAnalysis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static mj.core.domain.Sex.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("HeightAnalysisService")
class HeightAnalysisIntegrationTest {
    private final HeightAnalysisRepository repository = mock(HeightAnalysisRepository.class);
    private final HeightAnalysisService service = new HeightAnalysisService(repository);
    private final float FIRST_HEIGHT = (float) 167.5;
    private final float SECOND_HEIGHT = (float) 170.8;
    private final float THIRD_HEIGHT = (float) 174.5;
    private final float FORTH_HEIGHT = (float) 180.4;
    private final float FIFTH_HEIGHT = (float) 183.9;
    private final int MONTH_AFTER_BIRTH = 227;

    @Nested
    @DisplayName("아이의 키를 분석할 때 ")
    class Describe_showAllBy {
        @Nested
        class Context_with_male_after_227_month_after {
            @BeforeEach
            void setUp() {
                HeightAnalysis firstAnalysis = new HeightAnalysis((long) 1, MALE, MONTH_AFTER_BIRTH, 10, FIRST_HEIGHT);
                HeightAnalysis secondAnalysis = new HeightAnalysis((long) 2, MALE, MONTH_AFTER_BIRTH, 25, SECOND_HEIGHT);
                HeightAnalysis thirdAnalysis = new HeightAnalysis((long) 3, MALE, MONTH_AFTER_BIRTH, 50, THIRD_HEIGHT);
                HeightAnalysis forthAnalysis = new HeightAnalysis((long) 4, MALE, MONTH_AFTER_BIRTH, 75, FORTH_HEIGHT);
                HeightAnalysis fifthAnalysis = new HeightAnalysis((long) 5, MALE, MONTH_AFTER_BIRTH, 90, FIFTH_HEIGHT);

                given(repository.findAllByMonthAndSex(MONTH_AFTER_BIRTH, MALE))
                        .willReturn(List.of(firstAnalysis, secondAnalysis, thirdAnalysis, forthAnalysis, fifthAnalysis));
            }
            @Test
            @DisplayName("10% 미만의 키라면 0번째 위치를 전달한다")
            void it_is_0th_position() {
                AnalysisResponseData result = service.analyzeHeightPosition(MONTH_AFTER_BIRTH, "MALE", FIRST_HEIGHT - 1);

                assertThat(result.getPosition()).isEqualTo(0);
            }

            @Test
            @DisplayName("10% 이상 25% 미만의 키라면 1번째 위치를 전달한다")
            void it_is_1st_position() {
                AnalysisResponseData result = service.analyzeHeightPosition(MONTH_AFTER_BIRTH, "MALE", FIRST_HEIGHT + 1);

                assertThat(result.getPosition()).isEqualTo(1);
            }

            @Test
            @DisplayName("25% 이상 50% 미만의 키라면 2번째 위치를 전달한다")
            void it_is_2nd_position() {
                AnalysisResponseData result = service.analyzeHeightPosition(MONTH_AFTER_BIRTH, "MALE", SECOND_HEIGHT + 1);

                assertThat(result.getPosition()).isEqualTo(2);
            }

            @Test
            @DisplayName("50% 이상 75% 미만의 키라면 3번째 위치를 전달한다")
            void it_is_3rd_position() {
                AnalysisResponseData result = service.analyzeHeightPosition(MONTH_AFTER_BIRTH, "MALE", THIRD_HEIGHT + 1);

                assertThat(result.getPosition()).isEqualTo(3);
            }

            @Test
            @DisplayName("75% 이상 100% 미만의 키라면 4번째 위치를 전달한다")
            void it_is_4th_position() {
                AnalysisResponseData result = service.analyzeHeightPosition(MONTH_AFTER_BIRTH, "MALE", FORTH_HEIGHT + 1);

                assertThat(result.getPosition()).isEqualTo(4);
            }

            @Test
            @DisplayName("100% 이상의 키라면 5번째 위치를 전달한다")
            void it_is_5th_position() {
                AnalysisResponseData result = service.analyzeHeightPosition(MONTH_AFTER_BIRTH, "MALE", FIFTH_HEIGHT + 1);

                assertThat(result.getPosition()).isEqualTo(5);
            }
        }
    }
}