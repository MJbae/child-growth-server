package mj.api.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AnalysisResponseData {
    private final Integer position;

    private final List<HeightData> range;

    public AnalysisResponseData(Integer position, List<HeightData> range) {
        this.position = position;
        this.range = range;
    }
}
