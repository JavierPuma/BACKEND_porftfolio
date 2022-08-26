package com.pumajavier.portfolio.proyecto;

import com.pumajavier.portfolio.security.entity.User;
import com.pumajavier.portfolio.security.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoService implements IProyectoService {

    @Autowired
    ProyectoRepository repo;
    @Autowired
    UserRepository userRepo;

    @Override
    public void agregarProyecto(ProyectoDTO proj) {
        User user = userRepo.findById(proj.getPersona_id()).orElse(null);
        Proyecto nuevoProj = new Proyecto(user, proj.getTitulo(), proj.getDescripcion(), proj.getImg_url(), proj.getDemo_url(), proj.getRepo_url());
        repo.save(nuevoProj);
    }

    @Override
    public void editarProyecto(Long id, ProyectoDTO datosProyecto) {
        Proyecto projEditable = repo.findById(id).orElse(null);
        projEditable.setUser(userRepo.findById(datosProyecto.getPersona_id()).orElse(null));
        projEditable.setTitulo(datosProyecto.getTitulo());
        projEditable.setDescripcion(datosProyecto.getDescripcion());
        projEditable.setImg_url(datosProyecto.getImg_url());
        projEditable.setDemo_url(datosProyecto.getDemo_url());
        projEditable.setRepo_url(datosProyecto.getRepo_url());
        repo.save(projEditable);
    }

    @Override
    public ProyectoDTO buscarProyecto(Long id) {
        Proyecto proj =  repo.findById(id).orElse(null);
        ProyectoDTO projDTO = new ProyectoDTO(proj.getId(), proj.getUser().getId(), proj.getTitulo(), proj.getDescripcion(), proj.getImg_url(), proj.getDemo_url(), proj.getRepo_url());
        return projDTO;
    }

    @Override
    public List<ProyectoDTO> listarProyectos() {
        List<Proyecto> listaProyectos = repo.findAll();
        List<ProyectoDTO> listaDTO = new ArrayList<ProyectoDTO>();
        for(int i = 0; i < listaProyectos.size();i++){
            Proyecto proj = listaProyectos.get(i);
            ProyectoDTO projDTO = new ProyectoDTO(proj.getId(), proj.getUser().getId(), proj.getTitulo(), proj.getDescripcion(), proj.getImg_url(), proj.getDemo_url(), proj.getRepo_url()); 
            listaDTO.add(projDTO);
        }
        return listaDTO;
    }

    @Override
    public void borrarProyecto(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void crearProyectoDefault(Long persona_id) {
        User userDefault = userRepo.findById(persona_id).orElse(null);
        Proyecto proyectoDefault = new Proyecto(userDefault, "Titulo", "Descripcion de tu proyecto", "https://cabaretfestival.es/wp-content/uploads/2020/07/Hero-Banner-Placeholder-Light-1024x480-1.png", "https://google.com", "https://google.com");
        repo.save(proyectoDefault);
    }

}