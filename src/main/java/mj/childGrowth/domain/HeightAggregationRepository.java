package mj.childGrowth.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeightAggregationRepository extends CrudRepository<HeightRequestAggregation, Long> {
    HeightRequestAggregation save(HeightRequestAggregation aggregation);
}
