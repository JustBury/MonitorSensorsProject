package by.bury.monitorsensors.repository;

import by.bury.monitorsensors.model.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorTypeRepository  extends JpaRepository<SensorType, Long> {
    SensorType findByName(String name);
}
