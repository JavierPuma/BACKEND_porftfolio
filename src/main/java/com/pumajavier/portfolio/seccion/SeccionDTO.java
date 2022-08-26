package com.pumajavier.portfolio.seccion;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SeccionDTO {

    private Long id;
    private Long persona_id;
    private String titulo;
    private String descripcion;

    public SeccionDTO() {
    }

    public SeccionDTO(Long id, Long persona_id, String titulo, String descripcion) {
        this.id = id;
        this.persona_id = persona_id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

}
