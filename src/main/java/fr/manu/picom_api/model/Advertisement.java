package fr.manu.picom_api.model;

import fr.manu.picom_api.AdvertisementType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Advertisement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private String content;

    private String url;

    @Enumerated(EnumType.STRING)
    private AdvertisementType type;

    @ManyToOne
    @ToString.Exclude
    private User user;

    @ManyToMany
    @ToString.Exclude
    private Collection<Zone> zones = new ArrayList<>();

    public Advertisement(LocalDate dateStart, LocalDate dateEnd, AdvertisementType type, String content) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        if (type == AdvertisementType.IMAGE) {
            this.type = type;
            this.url = content;
        } else if (type == AdvertisementType.HTML) {
            this.type = type;
            this.content = content;
        }

    }

    public Advertisement() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Advertisement that = (Advertisement) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
