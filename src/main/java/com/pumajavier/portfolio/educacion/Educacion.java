package com.pumajavier.portfolio.educacion;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pumajavier.portfolio.security.entity.User;
import com.pumajavier.portfolio.security.repository.UserRepository;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private User user;
    @Size(max = 60)
    private String titulo;
    @Size(max = 60)
    private String institucion;
    @Size(max = 30)
    private String fecha;
    @Size(max = 500)
    private String descripcion;

    public Educacion() {
    }

    public Educacion(User user, String titulo, String institucion, String fecha, String descripcion) {
        this.user = user;
        this.titulo = titulo;
        this.institucion = institucion;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

}
