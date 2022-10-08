package mj.childGrowth.application;

import mj.childGrowth.controller.dto.HeightResponseData;
import mj.childGrowth.domain.HeightAnalysis;
import mj.childGrowth.domain.HeightAnalysisRepository;
import mj.childGrowth.domain.HeightRangeRequestLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static mj.childGrowth.domain.Sex.MALE;
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
    private final int MONTH_AFTER_BIRTH = 227;
    private final float HEIGHT_IN_REQUEST = (float) 179.5;

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
                given(repository.findAllByMonthAndSex(MONTH_AFTER_BIRTH, MALE))
                        .willReturn(List.of());
            }

            private List<HeightResponseData> subject() {
                return service.showAllBy(MONTH_AFTER_BIRTH, HEIGHT_IN_REQUEST, MALE);
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
                HeightAnalysis firstAnalysis = new HeightAnalysis((long) 1, MALE, MONTH_AFTER_BIRTH, 10, FIRST_HEIGHT);
                HeightAnalysis secondAnalysis = new HeightAnalysis((long) 2, MALE, MONTH_AFTER_BIRTH, 25, SECOND_HEIGHT);
                HeightAnalysis thirdAnalysis = new HeightAnalysis((long) 3, MALE, MONTH_AFTER_BIRTH, 50, THIRD_HEIGHT);
                HeightAnalysis forthAnalysis = new HeightAnalysis((long) 4, MALE, MONTH_AFTER_BIRTH, 75, FORTH_HEIGHT);
                HeightAnalysis fifthAnalysis = new HeightAnalysis((long) 5, MALE, MONTH_AFTER_BIRTH, 90, FIFTH_HEIGHT);

                given(repository.findAllByMonthAndSex(MONTH_AFTER_BIRTH, MALE))
                        .willReturn(List.of(firstAnalysis, secondAnalysis, thirdAnalysis, forthAnalysis, fifthAnalysis));
            }

            private List<HeightResponseData> subject() {
                return service.showAllBy(MONTH_AFTER_BIRTH, HEIGHT_IN_REQUEST, MALE);
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

    @Nested
    @DisplayName("getRangeIndex 메소드는")
    class Describe_getRangeIndex {
        private int subject() {
            HeightResponseData firstData = new HeightResponseData(10, FIRST_HEIGHT);
            HeightResponseData secondData = new HeightResponseData(25, SECOND_HEIGHT);
            HeightResponseData thirdData = new HeightResponseData(50, THIRD_HEIGHT);
            HeightResponseData forthData = new HeightResponseData(75, FORTH_HEIGHT);
            HeightResponseData fifthData = new HeightResponseData(90, FIFTH_HEIGHT);

            return service.getRangeIndex(HEIGHT_IN_REQUEST, List.of(firstData, secondData, thirdData, forthData, fifthData));
        }

        @Test
        @DisplayName("정확한 rangeIndex를 반환한다")
        void it_returns_exact_rangeIndex() {
            assertThat(subject()).isEqualTo(3);
        }
    }
}
