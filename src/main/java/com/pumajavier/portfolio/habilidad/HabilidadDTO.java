package com.pumajavier.portfolio.habilidad;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class HabilidadDTO {

    private Long id;
    private Long persona_id;
    private String tipo;
    private String nivel;
    private String nombre;
    private int progreso;
    private String icono;

    public HabilidadDTO() {
    }

    public HabilidadDTO(Long id, Long persona_id, String tipo, String nivel, String nombre, int progreso, String icono) {
        this.id = id;
        this.persona_id = persona_id;
        this.tipo = tipo;
        this.nivel = nivel;
        this.nombre = nombre;
        this.progreso = progreso;
        this.icono = icono;
    }

}