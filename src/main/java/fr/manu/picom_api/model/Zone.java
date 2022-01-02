package fr.manu.picom_api.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int stopNumber;

    @OneToMany(mappedBy = "zone")
    @ToString.Exclude
    private List<Stop> stops;

    @OneToMany(mappedBy = "zone")
    @ToString.Exclude
    private List<ZoneHasHour> zoneHasHours;

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
