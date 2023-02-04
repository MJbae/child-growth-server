package mj.api.controller.dto;

import lombok.Getter;

@Getter
public class HeightData {
    private final Integer percentile;

    private final Float height;

    public HeightData(Integer percentile, Float height){
        this.percentile = percentile;
        this.height = height;
    }
}
