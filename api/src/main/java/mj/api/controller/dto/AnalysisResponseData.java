package mj.api.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AnalysisResponseData {
    private final Integer rangeIndex;

    private final List<HeightData> range;

    public AnalysisResponseData(Integer rangeIndex, List<HeightData> range) {
        this.rangeIndex = rangeIndex;
        this.range = range;
    }
}
