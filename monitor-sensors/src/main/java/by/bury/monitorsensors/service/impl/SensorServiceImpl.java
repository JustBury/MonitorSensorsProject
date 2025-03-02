package by.bury.monitorsensors.service.impl;

import by.bury.monitorsensors.dto.SensorRequestDto;
import by.bury.monitorsensors.dto.SensorResponseDto;
import by.bury.monitorsensors.dto.SensorUpdateDto;
import by.bury.monitorsensors.exception.SensorNotFoundException;
import by.bury.monitorsensors.mapper.SensorMapper;
import by.bury.monitorsensors.model.Sensor;
import by.bury.monitorsensors.repository.SensorRepository;
import by.bury.monitorsensors.service.SensorService;
import by.bury.monitorsensors.util.SensorSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    public SensorServiceImpl(SensorRepository sensorRepository, SensorMapper sensorMapper) {
        this.sensorRepository = sensorRepository;
        this.sensorMapper = sensorMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SensorResponseDto> getAllSensors() {
        return sensorRepository.findAll().stream().map(sensorMapper::fromEntityToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SensorResponseDto> getListSensorsByFilter(String name, String model) {
        Specification<Sensor> spec = Specification.where(null);
        if (name != null) spec = spec.and(SensorSpecification.hasName(name));
        if (model != null) spec = spec.and(SensorSpecification.hasModel(model));
        List<Sensor> sensors = sensorRepository.findAll(spec);
        return sensors.stream().map(sensorMapper::fromEntityToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SensorResponseDto getSensor(String sensorId) {
        Sensor sensor = sensorRepository.findById(sensorId).orElseGet(null);
        if (sensor == null) {
            throw new SensorNotFoundException(String.format("Sensor with id %s not found", sensorId));
        }
        return sensorMapper.fromEntityToDto(sensor);
    }

    @Override
    @Transactional
    public SensorResponseDto saveSensor(SensorRequestDto sensorRequestDto) {
        Sensor sensor = sensorMapper.fromDtoToEntity(sensorRequestDto);
        String uuid = UUID.randomUUID().toString();
        sensor.setUuid(uuid);
        Sensor savedSensor = sensorRepository.save(sensor);
        return sensorMapper.fromEntityToDto(savedSensor);
    }

    @Override
    @Transactional
    public SensorResponseDto updateSensor(String uuid, SensorUpdateDto sensorUpdateDto) {
        Sensor sensor = sensorRepository.findById(uuid).orElseGet(null);
        if (sensor == null) {
            throw new SensorNotFoundException(String.format("Sensor with id %s not found", uuid));
        }
        Sensor savingSensor = sensorMapper.update(sensorUpdateDto, sensor);
        Sensor updatedSensor = sensorRepository.save(savingSensor);
        return sensorMapper.fromEntityToDto(updatedSensor);
    }

    @Override
    @Transactional
    public void deleteSensor(String sensorId) {
        sensorRepository.deleteById(sensorId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isSensorExists(String uuid) {
        return sensorRepository.existsByUuid(uuid);
    }
}
