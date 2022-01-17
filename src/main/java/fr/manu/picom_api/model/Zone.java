package fr.manu.picom_api.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Zone extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int stopNumber;

    @ManyToMany(mappedBy = "zones")
    @ToString.Exclude
    private Collection<Advertisement> advertisements = new ArrayList<>();

    @OneToMany(mappedBy = "zone")
    @ToString.Exclude
    private Collection<Stop> stops = new ArrayList<>();

    @OneToMany(mappedBy = "zone")
    @ToString.Exclude
    private Collection<ZoneHasHour> zoneHasHours = new ArrayList<>();

    public Zone() {
    }

    public Zone(String name, int stopNumber) {
        this.name = name;
        this.stopNumber = stopNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Zone zone = (Zone) o;
        return id != null && Objects.equals(id, zone.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
