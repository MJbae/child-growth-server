package mj.log.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "log_height_request", catalog = "log")
public class HeightRequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer month;

    private Float height;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public HeightRequestLog(Float height, Integer month, Sex sex) {
        this.height = height;
        this.month = month;
        this.sex = sex;
    }
}

