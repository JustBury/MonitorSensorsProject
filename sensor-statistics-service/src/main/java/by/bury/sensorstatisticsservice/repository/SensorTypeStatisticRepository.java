package by.bury.sensorstatisticsservice.repository;

import by.bury.sensorstatisticsservice.model.CommonStatistic;
import by.bury.sensorstatisticsservice.model.SensorTypeStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorTypeStatisticRepository extends JpaRepository<SensorTypeStatistic, Long> {

    List<SensorTypeStatistic> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<SensorTypeStatistic> findByDateAfter(LocalDateTime startDate);

    List<SensorTypeStatistic> findByDateBefore(LocalDateTime endDate);
}
