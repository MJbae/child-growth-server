package mj.childGrowth.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AnalysisResponseData {
    private final Integer rangeIndex;

    private final List<HeightResponseData> range;

    public AnalysisResponseData(Integer rangeIndex, List<HeightResponseData> range) {
        this.rangeIndex = rangeIndex;
        this.range = range;
    }
}
