package fr.manu.picom_api.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Hour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime hourStart;

    private LocalTime hourEnd;

    @OneToMany(mappedBy = "hour")
    @ToString.Exclude
    private List<ZoneHasHour> zoneHasHours;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Hour hour = (Hour) o;
        return id != null && Objects.equals(id, hour.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
