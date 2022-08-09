package mj.childGrowth.application;

import mj.childGrowth.controller.dto.HeightResponseData;
import mj.childGrowth.domain.HeightAnalysis;
import mj.childGrowth.domain.HeightAnalysisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("HeightAnalysisService")
class HeightAnalysisServiceTest {
    private HeightAnalysisService service;
    private final HeightAnalysisRepository repository = mock(HeightAnalysisRepository.class);
    private final float FIRST_HEIGHT = (float) 167.5;
    private final float SECOND_HEIGHT = (float) 170.8;
    private final float THIRD_HEIGHT = (float) 174.5;
    private final float FORTH_HEIGHT = (float) 180.4;
    private final float FIFTH_HEIGHT = (float) 183.9;

    @BeforeEach
    void setUp() {
        service = new HeightAnalysisService(repository);
    }

    @Nested
    @DisplayName("showAllBy 메소드는")
    class Describe_showAllBy {

        @Nested
        @DisplayName("만약 관련 데이터가 존재하지 않는다면")
        class Context_without_heightAnalysis_from_db {
            @BeforeEach
            void setUp() {
                given(repository.findAllByMonthAndSexAndHeight(227, (float) 179.5, "male"))
                        .willReturn(List.of());
            }

            private List<HeightResponseData> subject() {
                return service.showAllBy(227, (float) 179.5, "male");
            }

            @Test
            @DisplayName("빈 리스트를 반환한다")
            void it_returns_empty_list() {
                assertThat(subject()).isEmpty();
            }
        }

        @Nested
        @DisplayName("만약 heightAnalysis 데이터가 존재한다면")
        class Context_with_existing_heightAnalysis {
            @BeforeEach
            void setUp() {
                HeightAnalysis firstAnalysis = new HeightAnalysis((long) 1, "male", 227, 10, FIRST_HEIGHT);
                HeightAnalysis secondAnalysis = new HeightAnalysis((long) 2, "male", 227, 25, SECOND_HEIGHT);
                HeightAnalysis thirdAnalysis = new HeightAnalysis((long) 3, "male", 227, 50, THIRD_HEIGHT);
                HeightAnalysis forthAnalysis = new HeightAnalysis((long) 4, "male", 227, 75, FORTH_HEIGHT);
                HeightAnalysis fifthAnalysis = new HeightAnalysis((long) 5, "male", 227, 90, FIFTH_HEIGHT);

                given(repository.findAllByMonthAndSexAndHeight(227, (float) 179.5, "male"))
                        .willReturn(List.of(firstAnalysis, secondAnalysis, thirdAnalysis, forthAnalysis, fifthAnalysis));
            }

            private List<HeightResponseData> subject() {
                return service.showAllBy(227, (float) 179.5, "male");
            }

            @Test
            @DisplayName("비어 있지 않은 리스트를 반환한다")
            void it_returns_not_empty_list() {
                assertThat(subject()).isNotEmpty();
            }

            @Test
            @DisplayName("DB에서 가져온 Height 값과 동일한 값을 반환한다")
            void it_returns_exact_height() {
                assertThat(subject().get(0).getHeight()).isEqualTo(FIRST_HEIGHT);
                assertThat(subject().get(1).getHeight()).isEqualTo(SECOND_HEIGHT);
                assertThat(subject().get(2).getHeight()).isEqualTo(THIRD_HEIGHT);
                assertThat(subject().get(3).getHeight()).isEqualTo(FORTH_HEIGHT);
                assertThat(subject().get(4).getHeight()).isEqualTo(FIFTH_HEIGHT);
            }
        }
    }
}
