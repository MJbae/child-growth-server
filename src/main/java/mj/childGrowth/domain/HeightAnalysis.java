package mj.childGrowth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "height_analysis")
@Entity
@Getter
@NoArgsConstructor
public class HeightAnalysis {
    @Id
    @GeneratedValue
    private Long id;

    private String sex;

    private Integer month;

    private Integer percentile;

    private Float height;

    public HeightAnalysis(Long id, String sex, Integer month, Integer percentile, Float height) {
        this.id = id;
        this.sex = sex;
        this.month = month;
        this.percentile = percentile;
        this.height = height;
    }

    public HeightAnalysis(String sex, Integer month, Integer percentile, Float height) {
        this.sex = sex;
        this.month = month;
        this.percentile = percentile;
        this.height = height;
    }
}
