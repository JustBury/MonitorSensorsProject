package by.bury.sensorstatisticsservice.repository;

import by.bury.sensorstatisticsservice.model.CommonStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CommonStatisticRepository extends JpaRepository<CommonStatistic, Long> {

    List<CommonStatistic> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<CommonStatistic> findByDateAfter(LocalDateTime startDate);

    List<CommonStatistic> findByDateBefore(LocalDateTime endDate);
}
