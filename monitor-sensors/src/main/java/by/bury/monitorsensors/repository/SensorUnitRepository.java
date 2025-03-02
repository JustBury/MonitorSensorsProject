package by.bury.monitorsensors.repository;

import by.bury.monitorsensors.model.SensorUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SensorUnitRepository extends JpaRepository<SensorUnit, Long> {
    SensorUnit findByName(String name);
}

