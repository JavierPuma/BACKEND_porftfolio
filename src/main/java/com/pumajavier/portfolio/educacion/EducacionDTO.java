package com.pumajavier.portfolio.educacion;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EducacionDTO {

    private Long id;
    private Long persona_id;
    private String titulo;
    private String institucion;
    private String fecha;
    private String descripcion;

    public EducacionDTO() {
    }

    public EducacionDTO(Long id, Long persona_id, String titulo, String institucion, String fecha, String descripcion) {
        this.id = id;
        this.persona_id = persona_id;
        this.titulo = titulo;
        this.institucion = institucion;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

}