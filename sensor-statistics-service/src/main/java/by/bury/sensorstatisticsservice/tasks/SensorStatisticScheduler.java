package by.bury.sensorstatisticsservice.tasks;

import by.bury.sensorstatisticsservice.client.MonitorSensorClient;
import by.bury.sensorstatisticsservice.dto.SensorResponseDto;
import by.bury.sensorstatisticsservice.model.CommonStatistic;
import by.bury.sensorstatisticsservice.model.SensorTypeStatistic;
import by.bury.sensorstatisticsservice.repository.CommonStatisticRepository;
import by.bury.sensorstatisticsservice.repository.SensorTypeStatisticRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class SensorStatisticScheduler {

    Logger logger = Logger.getLogger(SensorStatisticScheduler.class.getName());

    private final MonitorSensorClient monitorSensorClient;

    private final CommonStatisticRepository commonStatisticRepository;

    private final SensorTypeStatisticRepository sensorTypeStatisticRepository;

    public SensorStatisticScheduler(MonitorSensorClient monitorSensorClient,
                                    CommonStatisticRepository commonStatisticRepository,
                                    SensorTypeStatisticRepository sensorTypeStatisticRepository) {
        this.monitorSensorClient = monitorSensorClient;
        this.commonStatisticRepository = commonStatisticRepository;
        this.sensorTypeStatisticRepository = sensorTypeStatisticRepository;
    }


    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional
    public void makeSensorStatistic() {

        List<SensorResponseDto> sensorResponseDtoList = monitorSensorClient.getSensorsInfo();
        saveCountOfSensors(sensorResponseDtoList);
        saveCountOfSensorsByType(sensorResponseDtoList);
    }

    @Transactional
    public void saveCountOfSensors(List<SensorResponseDto> sensorResponseDtoList) {
        CommonStatistic commonStatistic = new CommonStatistic();
        LocalDateTime localDateTime = LocalDateTime.now();

        commonStatistic.setCountSensors(sensorResponseDtoList.size());
        commonStatistic.setDate(localDateTime);
        commonStatisticRepository.save(commonStatistic);

        logger.info(String.format("Common sensor statistics saved successfully on %s", localDateTime));
    }

    @Transactional
    public void saveCountOfSensorsByType(List<SensorResponseDto> sensorResponseDtoList) {
        logger.info("Start put sensor data by type");

        Map<String, Long> countByType = sensorResponseDtoList.stream()
                .collect(Collectors.groupingBy(SensorResponseDto::type, Collectors.counting()));

        LocalDateTime localDateTime = LocalDateTime.now();

        countByType.forEach((type, count) -> {
            SensorTypeStatistic statistic = new SensorTypeStatistic();
            statistic.setTypeSensor(type);
            statistic.setCountSensors(count.intValue());
            statistic.setDate(localDateTime);
            sensorTypeStatisticRepository.save(statistic);
        });

        logger.info(String.format("Sensor statistics by type saved successfully on %s", localDateTime));
    }
}
