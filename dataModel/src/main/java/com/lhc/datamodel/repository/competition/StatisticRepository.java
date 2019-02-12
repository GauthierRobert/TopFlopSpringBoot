package com.lhc.datamodel.repository.competition;

import com.lhc.datamodel.entities.statistic.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
}
