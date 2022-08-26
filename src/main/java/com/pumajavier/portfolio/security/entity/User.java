package com.pumajavier.portfolio.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pumajavier.portfolio.educacion.Educacion;
import com.pumajavier.portfolio.experiencia.Experiencia;
import com.pumajavier.portfolio.habilidad.Habilidad;
import com.pumajavier.portfolio.persona.Persona;
import com.pumajavier.portfolio.proyecto.Proyecto;
import com.pumajavier.portfolio.seccion.Seccion;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Persona persona;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private List<Proyecto> proyectos;
    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private List<Educacion> educacion;
    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private List<Experiencia> experiencia;
    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private List<Habilidad> habilidades;
    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private List<Seccion> secciones;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
