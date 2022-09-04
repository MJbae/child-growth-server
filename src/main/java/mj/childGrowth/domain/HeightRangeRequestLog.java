package mj.childGrowth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class HeightRangeRequestLog {

    @Id
    @GeneratedValue
    private Long id;

    private Integer month;

    private Float height;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public HeightRangeRequestLog(Float height, Integer month, Sex sex) {
        this.height = height;
        this.month = month;
        this.sex = sex;
    }
}

