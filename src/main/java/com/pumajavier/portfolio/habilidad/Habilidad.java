package com.pumajavier.portfolio.habilidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pumajavier.portfolio.security.entity.User;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private User user;
    @Size(max = 45)
    private String tipo;
    @Size(max = 45)
    private String nivel;
    @Size(max = 45)
    private String nombre;
    @Max(100)
    private int progreso;
    @Size(max = 50)
    private String icono;

    public Habilidad() {
    }

    public Habilidad(User user, String tipo, String nivel, String nombre, int progreso, String icono) {
        this.user = user;
        this.tipo = tipo;
        this.nivel = nivel;
        this.nombre = nombre;
        this.progreso = progreso;
        this.icono = icono;
    }
}