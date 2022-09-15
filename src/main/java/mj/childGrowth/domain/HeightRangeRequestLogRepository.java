package mj.childGrowth.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeightRangeRequestLogRepository extends CrudRepository<HeightRequestLog, Long> {
    HeightRequestLog save(HeightRequestLog log);

    int countBySex(Sex sex);

    @Query("SELECT SUM(log.month) FROM HeightRequestLog log")
    Optional<Float> addAllMonth();

    @Query("SELECT SUM(log.height) FROM HeightRequestLog log")
    Optional<Float> addAllHeight();
}
