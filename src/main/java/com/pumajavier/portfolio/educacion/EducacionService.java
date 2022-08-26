package com.pumajavier.portfolio.educacion;

import com.pumajavier.portfolio.security.entity.User;
import com.pumajavier.portfolio.security.repository.UserRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducacionService implements IEducacionService {

    @Autowired
    EducacionRepository repo;
    @Autowired
    UserRepository userRepo;

    @Override
    public void agregarEducacion(EducacionDTO edu) {
        User user = userRepo.findById(edu.getPersona_id()).orElse(null);
        Educacion nuevaEdu = new Educacion(user, edu.getTitulo(), edu.getInstitucion(), edu.getFecha(), edu.getDescripcion());
        repo.save(nuevaEdu);
    }

    @Override
    public void editarEducacion(Long id, EducacionDTO datosEducacion) {
        Educacion eduEditable = repo.findById(id).orElse(null);
        eduEditable.setUser(userRepo.findById(datosEducacion.getPersona_id()).orElse(null));
        eduEditable.setTitulo(datosEducacion.getTitulo());
        eduEditable.setInstitucion(datosEducacion.getInstitucion());
        eduEditable.setFecha(datosEducacion.getFecha());
        eduEditable.setDescripcion(datosEducacion.getDescripcion());
        repo.save(eduEditable);
    }

    @Override
    public List<EducacionDTO> listarEducacion() {
        List<Educacion> listaEdu = repo.findAll();
        List<EducacionDTO> listaDTO = new ArrayList<EducacionDTO>();
        for (int i = 0; i < listaEdu.size(); i++) {
            Educacion edu = listaEdu.get(i);
            EducacionDTO eduDTO = new EducacionDTO(edu.getId(), edu.getUser().getId(), edu.getTitulo(), edu.getInstitucion(), edu.getFecha(), edu.getDescripcion());
            listaDTO.add(eduDTO);
        }
        return listaDTO;
    }

    @Override
    public EducacionDTO buscarEducacion(Long id) {
        Educacion edu = repo.findById(id).orElse(null);
        EducacionDTO eduDTO = new EducacionDTO(edu.getId(), edu.getUser().getId(), edu.getTitulo(), edu.getInstitucion(), edu.getFecha(), edu.getDescripcion());
        return eduDTO;
    }

    @Override
    public void borrarEducacion(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void crearEducacionDefault(Long persona_id) {
        User userDefault = userRepo.findById(persona_id).orElse(null);
        Educacion eduDefault = new Educacion(userDefault, "Título", "Institución", "Inicio - Fin", "Descripción...");
        repo.save(eduDefault);
    }
}