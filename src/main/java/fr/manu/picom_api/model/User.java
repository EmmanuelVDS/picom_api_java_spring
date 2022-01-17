package fr.manu.picom_api.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Merci d'indiquer un prénom")
    @NotNull(message = "Merci d'indiquer un prénom")
    private String name;

    @NotBlank(message = "Merci d'indiquer un nom")
    @NotNull(message = "Merci d'indiquer un nom")
    private String lastname;

    @NotBlank(message = "Merci d'indiquer un email")
    @NotNull(message = "Merci d'indiquer un email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Merci d'indiquer un mot de passe")
    @NotNull(message = "Merci d'indiquer un mot de passe")
    @Length(min = 8)
    private String password;

    @NotBlank(message = "Merci d'indiquer un numéro de téléphone")
    @NotNull(message = "Merci d'indiquer un numéro de téléphone")
    private String phone;

    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Collection<Advertisement> advertisements = new ArrayList<>();

    public User(String name, String lastname, String email, String password, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
