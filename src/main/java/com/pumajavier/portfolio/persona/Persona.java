package com.pumajavier.portfolio.persona;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pumajavier.portfolio.security.entity.User;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
public class Persona {

    @Id
    private Long id;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private User user;
    @Size(min = 4, max = 60)
    private String nombre;
    @Size(max = 80)
    @NotNull
    private String titulo;
    @Size(max = 500)
    private String linkedin_url;
    @Size(max = 500)
    private String github_url;
    @Size(max = 500)
    private String img_url;
    @Size(max = 500)
    private String banner_url;
    @Size(max = 500)
    private String about_url;

    public Persona() {
    }

    public Persona(User user, String nombre, String titulo, String linkedin_url, String github_url, String img_url, String banner_url, String about_url) {
        this.user = user;
        this.nombre = nombre;
        this.titulo = titulo;
        this.linkedin_url = linkedin_url;
        this.github_url = github_url;
        this.img_url = img_url;
        this.banner_url = banner_url;
        this.about_url = about_url;
    }
}