package mj.api.domain;

import mj.core.domain.HeightAnalysis;
import mj.core.domain.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeightAnalysisRepository extends JpaRepository<HeightAnalysis, Long> {
    List<HeightAnalysis> findAllByMonthAndSex(Integer month, Sex sex);
}
