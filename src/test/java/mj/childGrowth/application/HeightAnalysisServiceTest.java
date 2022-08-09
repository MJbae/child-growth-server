package mj.childGrowth.application;

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

    @Nested
    @DisplayName("showAllBy 메소드는")
    class Describe_showAllBy {
        @BeforeEach
        void setUp() {
            service = new HeightAnalysisService(repository);

            HeightAnalysis firstAnalysis = new HeightAnalysis((long) 1, "male", 227, 10, (float) 167.5);
            HeightAnalysis secondAnalysis = new HeightAnalysis((long) 2, "male", 227, 25, (float) 170.8);
            HeightAnalysis thirdAnalysis = new HeightAnalysis((long) 3, "male", 227, 50, (float) 174.5);
            HeightAnalysis forthAnalysis = new HeightAnalysis((long) 4, "male", 227, 75, (float) 180.4);
            HeightAnalysis fifthAnalysis = new HeightAnalysis((long) 5, "male", 227, 90, (float) 183.9);

            given(repository.findAllByMonthAndSexAndHeight(227, (float) 179.5, "male"))
                    .willReturn(List.of(firstAnalysis, secondAnalysis, thirdAnalysis, forthAnalysis, fifthAnalysis));
        }

        @Test
        @DisplayName("비어 있지 않은 리스트를 반환한다")
        void it_returns_not_empty_list() {
            assertThat(service.showAllBy(227, (float) 179.5, "male")).isNotEmpty();
        }
    }
}
