package com.pumajavier.portfolio.experiencia;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ExperienciaDTO {

    private Long id;
    private Long persona_id;
    private String puesto;
    private String empleador;
    private String fecha;
    private String descripcion;

    public ExperienciaDTO() {
    }

    public ExperienciaDTO(Long id, Long persona_id, String puesto, String empleador, String fecha, String descripcion) {
        this.id = id;
        this.persona_id = persona_id;
        this.puesto = puesto;
        this.empleador = empleador;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
    
}
