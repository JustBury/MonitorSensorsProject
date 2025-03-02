package by.bury.sensorstatisticsservice.controller;

import by.bury.sensorstatisticsservice.model.CommonStatistic;
import by.bury.sensorstatisticsservice.model.SensorTypeStatistic;
import by.bury.sensorstatisticsservice.service.SensorStatisticService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic")
public class SensorStatisticController {

    private final SensorStatisticService sensorStatisticService;

    public SensorStatisticController(SensorStatisticService sensorStatisticService) {
        this.sensorStatisticService = sensorStatisticService;
    }


    @GetMapping("/sensor-data")
    public List<CommonStatistic> getSensorData(
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return sensorStatisticService.getCommonSensorDataByDateRange(startDate, endDate);
    }

    @GetMapping("/sensor-data/types")
    public List<SensorTypeStatistic> getSensorDataByTypes(
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return sensorStatisticService.getSensorDataByTypesByDateRange(startDate, endDate);
    }
}
