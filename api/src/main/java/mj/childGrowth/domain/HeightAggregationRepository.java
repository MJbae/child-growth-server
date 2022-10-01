package mj.childGrowth.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface HeightAggregationRepository extends CrudRepository<HeightRequestAggregation, Long> {
    HeightRequestAggregation save(HeightRequestAggregation aggregation);

    Optional<HeightRequestAggregation> findFirstByCreatedAtBefore(LocalDateTime createdAt);
}
