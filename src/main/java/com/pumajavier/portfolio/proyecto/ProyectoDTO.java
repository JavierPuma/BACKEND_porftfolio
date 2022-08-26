package com.pumajavier.portfolio.proyecto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProyectoDTO {

    private Long id;
    private Long persona_id;
    private String titulo;
    private String descripcion;
    private String img_url;
    private String demo_url;
    private String repo_url;

    public ProyectoDTO() {
    }

    public ProyectoDTO(Long id, Long persona_id, String titulo, String descripcion, String img_url, String demo_url, String repo_url) {
        this.id = id;
        this.persona_id = persona_id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.img_url = img_url;
        this.demo_url = demo_url;
        this.repo_url = repo_url;
    }

}