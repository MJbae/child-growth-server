package mj.api.controller.dto;

import lombok.Getter;
import mj.core.domain.HeightAnalysis;

@Getter
public class HeightData {
    private final Integer percentile;

    private final Float height;

    public HeightData(HeightAnalysis source){
        this.percentile = source.getPercentile();
        this.height = source.getHeight();
    }
}
