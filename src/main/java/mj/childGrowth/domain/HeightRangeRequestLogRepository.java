package mj.childGrowth.domain;

import org.springframework.data.repository.CrudRepository;

public interface HeightRangeRequestLogRepository extends CrudRepository<HeightRangeRequestLog, Long> {
    HeightRangeRequestLog save(HeightRangeRequestLog log);
}
