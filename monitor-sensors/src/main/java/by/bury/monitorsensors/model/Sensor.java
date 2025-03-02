package by.bury.monitorsensors.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity(name = "sensor")
public class Sensor {

    @Id
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "range_id")
    private Range range;

    @ManyToOne
    @JoinColumn(name = "type")
    private SensorType type;

    @ManyToOne
    @JoinColumn(name = "unit")
    private SensorUnit unit;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    public Sensor() {
    }

    public Sensor(String uuid, String name, String model, Range range, SensorType type, SensorUnit unit, String location, String description) {
        this.uuid = uuid;
        this.name = name;
        this.model = model;
        this.range = range;
        this.type = type;
        this.unit = unit;
        this.location = location;
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public SensorUnit getUnit() {
        return unit;
    }

    public void setUnit(SensorUnit unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(uuid, sensor.uuid) && Objects.equals(name, sensor.name) && Objects.equals(model, sensor.model) && Objects.equals(range, sensor.range) && Objects.equals(type, sensor.type) && unit == sensor.unit && Objects.equals(location, sensor.location) && Objects.equals(description, sensor.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, model, range, type, unit, location, description);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", range=" + range +
                ", type=" + type +
                ", unit=" + unit +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
