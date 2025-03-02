package by.bury.monitorsensors.util;

import by.bury.monitorsensors.model.Sensor;
import org.springframework.data.jpa.domain.Specification;

public class SensorSpecification {

    public static Specification<Sensor> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Sensor> hasModel(String model) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("model")), "%" + model.toLowerCase() + "%");
    }
}
