package by.bury.monitorsensors.mapper;

import by.bury.monitorsensors.dto.SensorRequestDto;
import by.bury.monitorsensors.dto.SensorResponseDto;
import by.bury.monitorsensors.dto.SensorUpdateDto;
import by.bury.monitorsensors.model.Range;
import by.bury.monitorsensors.model.Sensor;
import by.bury.monitorsensors.model.SensorType;
import by.bury.monitorsensors.model.SensorUnit;
import by.bury.monitorsensors.repository.SensorTypeRepository;
import by.bury.monitorsensors.repository.SensorUnitRepository;
import org.springframework.stereotype.Component;

@Component
public class SensorMapper {

    private final SensorTypeRepository sensorTypeRepository;
    private final SensorUnitRepository sensorUnitRepository;

    public SensorMapper(SensorTypeRepository sensorTypeRepository, SensorUnitRepository sensorUnitRepository) {
        this.sensorTypeRepository = sensorTypeRepository;
        this.sensorUnitRepository = sensorUnitRepository;
    }

    public SensorResponseDto fromEntityToDto(Sensor sensor) {
        String uuid = sensor.getUuid();
        String name = sensor.getName();
        String model = sensor.getModel();
        Range range = sensor.getRange();
        String type = sensor.getType().getName();
        String unit = sensor.getUnit().getName();
        String location = sensor.getLocation();
        String description = sensor.getDescription();

        return new SensorResponseDto(uuid, name, model, range, type, unit, location, description);
    }

    public Sensor fromDtoToEntity(SensorRequestDto dto) {
        Sensor sensor = new Sensor();
        sensor.setName(dto.name());
        sensor.setModel(dto.model());
        sensor.setRange(dto.range());
        sensor.setType(getSensorType(dto.type()));
        sensor.setLocation(dto.location());
        sensor.setUnit(getSensorUnit(dto.unit()));
        sensor.setDescription(dto.description());
        return sensor;
    }

    public Sensor update(SensorUpdateDto sensorUpdateDto, Sensor sensor) {

        if(sensorUpdateDto.name() != null){
            sensor.setName(sensorUpdateDto.name());
        }
        if(sensorUpdateDto.model() != null){
            sensor.setModel(sensorUpdateDto.model());
        }
        if(sensorUpdateDto.range() != null){
            sensor.setRange(sensorUpdateDto.range());
        }
        if(sensorUpdateDto.type() != null ){
            sensor.setType(getSensorType(sensorUpdateDto.type()));
        }
        if(sensorUpdateDto.location() != null){
            sensor.setLocation(sensorUpdateDto.location());
        }
        if(sensorUpdateDto.unit() != null){
            sensor.setUnit(getSensorUnit(sensorUpdateDto.unit()));
        }
        if(sensorUpdateDto.description() != null){
            sensor.setDescription(sensorUpdateDto.description());
        }
        return sensor;
    }

    private SensorType getSensorType(SensorType type){
        return sensorTypeRepository.findByName(type.getName());
    }

    private SensorUnit getSensorUnit(SensorUnit unit){
        return sensorUnitRepository.findByName(unit.getName());
    }
}
