package com.pumajavier.portfolio.seccion;

import com.pumajavier.portfolio.security.entity.User;
import com.pumajavier.portfolio.security.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeccionService implements ISeccionService {

    @Autowired
    SeccionRepository repo;
    @Autowired
    UserRepository userRepo;

    @Override
    public void crearSeccion(SeccionDTO sec) {
        User seccionUser = userRepo.findById(sec.getPersona_id()).orElse(null);
        Seccion nuevaSeccion = new Seccion(seccionUser, sec.getTitulo(), sec.getDescripcion());
        repo.save(nuevaSeccion);
    }

    @Override
    public List<SeccionDTO> listarSecciones() {
        List<Seccion> listaSecciones = repo.findAll();
        List<SeccionDTO> listaDTO = new ArrayList<SeccionDTO>();
        for (int i = 0; i < listaSecciones.size(); i++) {
            Seccion seccion = listaSecciones.get(i);
            SeccionDTO seccionDTO = new SeccionDTO(seccion.getId(), seccion.getUser().getId(), seccion.getTitulo(), seccion.getDescripcion());
            listaDTO.add(seccionDTO);
        }
        return listaDTO;
    }

    @Override
    public SeccionDTO buscarSeccion(Long id) {
        Seccion getSeccion = repo.findById(id).orElse(null);
        SeccionDTO seccionDTO = new SeccionDTO(getSeccion.getId(), getSeccion.getUser().getId(), getSeccion.getTitulo(), getSeccion.getDescripcion());
        return seccionDTO;
    }

    @Override
    public void editarSeccion(Long id, SeccionDTO datosSeccion) {
        Seccion editSeccion = repo.findById(id).orElse(null);
        editSeccion.setTitulo(datosSeccion.getTitulo());
        editSeccion.setDescripcion(datosSeccion.getDescripcion());
        System.out.println(datosSeccion.toString());
        System.out.println(editSeccion.toString());
        repo.save(editSeccion);
    }

    @Override
    public void eliminarSeccion(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void crearSeccionesDefault(Long persona_id) {
        User userDefault = userRepo.findById(persona_id).orElse(null);
        Seccion about = new Seccion(userDefault, "Sobre mí", "Ingresa aquí tu información personal...");
        Seccion skills = new Seccion(userDefault, "Habilidades", "Ingresa infromación de tus habilidades aquí...");
        Seccion projects = new Seccion(userDefault, "Proyectos", "Ingresa infromación de tus proyectos aquí...");
        Seccion contact = new Seccion(userDefault, "Contacto", "Ingresa información de contacto aquí..");

        List<Seccion> listaSecciones = new ArrayList<Seccion>();

        listaSecciones.add(about);
        listaSecciones.add(skills);
        listaSecciones.add(projects);
        listaSecciones.add(contact);

        for (Seccion seccion : listaSecciones) {
            repo.save(seccion);
        }

    }

}