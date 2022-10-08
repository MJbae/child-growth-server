package mj.api.controller.dto;

import lombok.Getter;

@Getter
public class HeightResponseData {
    private final Integer percentile;

    private final Float height;

    public HeightResponseData(Integer percentile, Float height){
        this.percentile = percentile;
        this.height = height;
    }
}
