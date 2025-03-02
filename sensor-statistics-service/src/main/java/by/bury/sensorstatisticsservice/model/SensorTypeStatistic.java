package by.bury.sensorstatisticsservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "statistic_type")
public class SensorTypeStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String typeSensor;

    @Column(name = "count_sensors", nullable = false)
    private Integer countSensors;

    private LocalDateTime date;

    public SensorTypeStatistic() {
    }

    public SensorTypeStatistic(String typeSensor, Integer countSensors, LocalDateTime date) {
        this.typeSensor = typeSensor;
        this.countSensors = countSensors;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeSensor() {
        return typeSensor;
    }

    public void setTypeSensor(String typeSensor) {
        this.typeSensor = typeSensor;
    }

    public Integer getCountSensors() {
        return countSensors;
    }

    public void setCountSensors(Integer countSensors) {
        this.countSensors = countSensors;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorTypeStatistic that = (SensorTypeStatistic) o;
        return Objects.equals(id, that.id) && Objects.equals(typeSensor, that.typeSensor) && Objects.equals(countSensors, that.countSensors) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeSensor, countSensors, date);
    }

    @Override
    public String toString() {
        return "SensorTypeStatistic{" +
                "id=" + id +
                ", typeSensor='" + typeSensor + '\'' +
                ", countSensors=" + countSensors +
                ", date=" + date +
                '}';
    }
}
