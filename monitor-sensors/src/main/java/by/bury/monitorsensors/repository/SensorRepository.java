package by.bury.monitorsensors.repository;

import by.bury.monitorsensors.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, String>, JpaSpecificationExecutor<Sensor> {

    @Query("select (count(s) > 0) from sensor s where s.uuid = :uuid")
    boolean existsByUuid(@Param("uuid") String uuid);

}
