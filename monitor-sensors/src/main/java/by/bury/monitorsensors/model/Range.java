package by.bury.monitorsensors.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity(name = "range")
public class Range {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "from_field")
    @Min(value = 1, message = "From must be a positive integer")
    private Integer from;

    @Column(name = "to_field")
    @NotNull(message = "To cannot be null")
    @Min(value = 1, message = "To must be a positive integer")
    private Integer to;

    public Range() {
    }

    public Range(Long id, Integer from, Integer to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return Objects.equals(id, range.id) && Objects.equals(from, range.from) && Objects.equals(to, range.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to);
    }

    @Override
    public String toString() {
        return "Range{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
