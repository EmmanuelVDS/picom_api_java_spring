package fr.manu.picom_api.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Stop extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ipRaspberry;

    @ManyToOne
    private Zone zone;

    public Stop() {
    }

    public Stop(String name, Zone zone) {
        this.name = name;
        this.zone = zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Stop stop = (Stop) o;
        return id != null && Objects.equals(id, stop.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
