package by.bury.monitorsensors.service;

import by.bury.monitorsensors.dto.SensorRequestDto;
import by.bury.monitorsensors.dto.SensorResponseDto;
import by.bury.monitorsensors.dto.SensorUpdateDto;

import java.util.List;

public interface SensorService {

    List<SensorResponseDto> getAllSensors();

    List<SensorResponseDto> getListSensorsByFilter(String name, String model);

    SensorResponseDto getSensor(String sensorId);

    SensorResponseDto saveSensor(SensorRequestDto sensorRequestDto);

    SensorResponseDto updateSensor(String uuid, SensorUpdateDto sensorUpdateDto);

    void deleteSensor(String sensorId);

    boolean isSensorExists(String uuid);
}
