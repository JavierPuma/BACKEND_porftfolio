package com.pumajavier.portfolio.experiencia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pumajavier.portfolio.security.entity.User;
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
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private User user;
    @Size(max = 60)
    private String puesto;
    @Size(max = 60)
    private String empleador;
    @Size(max = 30)
    private String fecha;
    @Size(max = 500)
    private String descripcion;

    public Experiencia() {
    }

    public Experiencia(User user,String puesto, String empleador, String fecha, String descripcion) {
        this.user = user;
        this.puesto = puesto;
        this.empleador = empleador;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

}