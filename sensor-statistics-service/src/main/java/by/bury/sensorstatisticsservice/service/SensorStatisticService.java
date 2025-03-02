package by.bury.sensorstatisticsservice.service;

import by.bury.sensorstatisticsservice.model.CommonStatistic;
import by.bury.sensorstatisticsservice.model.SensorTypeStatistic;

import java.time.LocalDate;
import java.util.List;

public interface SensorStatisticService {

    List<CommonStatistic> getCommonSensorDataByDateRange(LocalDate startDate, LocalDate endDate);

    List<SensorTypeStatistic> getSensorDataByTypesByDateRange(LocalDate startDate, LocalDate endDate);
}
