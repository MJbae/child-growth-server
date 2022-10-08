package mj.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "height_analysis")
public class HeightAnalysis {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Integer month;

    private Integer percentile;

    private Float height;

    public HeightAnalysis(Long id, Sex sex, Integer month, Integer percentile, Float height) {
        this.id = id;
        this.sex = sex;
        this.month = month;
        this.percentile = percentile;
        this.height = height;
    }

    public HeightAnalysis(Sex sex, Integer month, Integer percentile, Float height) {
        this.sex = sex;
        this.month = month;
        this.percentile = percentile;
        this.height = height;
    }
}
