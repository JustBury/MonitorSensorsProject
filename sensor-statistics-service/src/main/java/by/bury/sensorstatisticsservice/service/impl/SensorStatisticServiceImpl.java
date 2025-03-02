package by.bury.sensorstatisticsservice.service.impl;

import by.bury.sensorstatisticsservice.model.CommonStatistic;
import by.bury.sensorstatisticsservice.model.SensorTypeStatistic;
import by.bury.sensorstatisticsservice.repository.CommonStatisticRepository;
import by.bury.sensorstatisticsservice.repository.SensorTypeStatisticRepository;
import by.bury.sensorstatisticsservice.service.SensorStatisticService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class SensorStatisticServiceImpl implements SensorStatisticService {

    Logger logger = Logger.getLogger(SensorStatisticServiceImpl.class.getName());


    private final CommonStatisticRepository commonStatisticRepository;
    private final SensorTypeStatisticRepository sensorTypeStatisticRepository;

    public SensorStatisticServiceImpl(CommonStatisticRepository commonStatisticRepository,
                                      SensorTypeStatisticRepository sensorTypeStatisticRepository) {
        this.commonStatisticRepository = commonStatisticRepository;
        this.sensorTypeStatisticRepository = sensorTypeStatisticRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommonStatistic> getCommonSensorDataByDateRange(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;
        logger.info(String.format("Getting data by date range from %s to %s", startDate, endDate));
        if (startDateTime == null && endDateTime == null) {
            return commonStatisticRepository.findAll();
        }
        if (startDateTime == null) {
            return commonStatisticRepository.findByDateBefore(endDateTime);
        }
        if (endDateTime == null) {
            return commonStatisticRepository.findByDateAfter(startDateTime);
        }
        return commonStatisticRepository.findByDateBetween(startDateTime, endDateTime);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SensorTypeStatistic> getSensorDataByTypesByDateRange(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;
        logger.info(String.format("Getting data by date range from %s to %s", startDate, endDate));
        if (startDateTime == null && endDateTime == null) {
            return sensorTypeStatisticRepository.findAll();
        }
        if (startDateTime == null) {
            return sensorTypeStatisticRepository.findByDateBefore(endDateTime);
        }
        if (endDateTime == null) {
            return sensorTypeStatisticRepository.findByDateAfter(startDateTime);
        }
        return sensorTypeStatisticRepository.findByDateBetween(startDateTime, endDateTime);
    }
}
