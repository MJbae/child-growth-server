package mj.childGrowth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "height_request_aggregation")
public class HeightRequestAggregation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_total_count")
    private Integer requestTotalCount;

    @Column(name = "month_average")
    private Float monthAverage;

    @Column(name = "height_average")
    private Float heightAverage;

    @Column(name = "male_count")
    private Integer maleCount;

    @Column(name = "female_count")
    private Integer femaleCount;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public HeightRequestAggregation(Integer requestTotalCount, Integer femaleCount, Integer maleCount) {
        this.requestTotalCount = requestTotalCount;
        this.femaleCount = femaleCount;
        this.maleCount = maleCount;
    }
}
