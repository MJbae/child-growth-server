package mj.childGrowth.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeightAnalysisRepository extends CrudRepository<HeightAnalysis, Long> {
    List<HeightAnalysis> findAllByMonthAndSexAndHeight(Integer month, Float height, String sex);
}
