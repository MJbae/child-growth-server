package mj.childGrowth.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeightAnalysisRepository extends CrudRepository<HeightAnalysis, Long> {
    List<HeightAnalysis> findAllByYearAndMonthAndDayAndSexAndHeight(Integer year, Integer month,
                                                                    Integer day, Float height,
                                                                    String sex);
}
