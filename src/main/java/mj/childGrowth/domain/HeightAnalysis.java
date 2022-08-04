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

    private String month;

    private Integer percentile;

    private Float height;
}
