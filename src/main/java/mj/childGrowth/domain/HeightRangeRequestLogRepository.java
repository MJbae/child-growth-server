package mj.childGrowth.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeightRangeRequestLogRepository extends CrudRepository<HeightRequestLog, Long> {
    HeightRequestLog save(HeightRequestLog log);
}
