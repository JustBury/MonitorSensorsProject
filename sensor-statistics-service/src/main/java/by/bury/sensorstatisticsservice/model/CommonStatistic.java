package by.bury.sensorstatisticsservice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "common_statistic")
public class CommonStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count_sensors", nullable = false)
    private Integer countSensors;

    private LocalDateTime date;

    public CommonStatistic() {
    }

    public CommonStatistic(Long id, Integer countSensors, LocalDateTime date) {
        this.id = id;
        this.countSensors = countSensors;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        CommonStatistic that = (CommonStatistic) o;
        return Objects.equals(id, that.id) && Objects.equals(countSensors, that.countSensors) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countSensors, date);
    }

    @Override
    public String toString() {
        return "Common_Statistic{" +
                "id=" + id +
                ", countSensors=" + countSensors +
                ", date=" + date +
                '}';
    }
}
