package com.pumajavier.portfolio.habilidad;

import com.pumajavier.portfolio.security.entity.User;
import com.pumajavier.portfolio.security.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabilidadService implements IHabilidadService {

    @Autowired
    HabilidadRepository repo;
    @Autowired
    UserRepository userRepo;

    @Override
    public void agregarHabilidad(HabilidadDTO hab) {
        User user = userRepo.findById(hab.getPersona_id()).orElse(null);
        Habilidad nuevaEdu = new Habilidad(user, hab.getTipo(), hab.getNivel(), hab.getNombre(), hab.getProgreso(), hab.getIcono());
        repo.save(nuevaEdu);
    }

    @Override
    public void editarHabilidad(Long id, HabilidadDTO datosHabilidad) {
        Habilidad habilidadEditable = repo.findById(id).orElse(null);
        habilidadEditable.setUser(userRepo.findById(datosHabilidad.getPersona_id()).orElse(null));
        habilidadEditable.setTipo(datosHabilidad.getTipo());
        habilidadEditable.setNivel(datosHabilidad.getNivel());
        habilidadEditable.setNombre(datosHabilidad.getNombre());
        habilidadEditable.setProgreso(datosHabilidad.getProgreso());
        habilidadEditable.setIcono(datosHabilidad.getIcono());
        repo.save(habilidadEditable);
    }

    @Override
    public HabilidadDTO buscarHabilidad(Long id) {
        Habilidad hab = repo.findById(id).orElse(null);
        HabilidadDTO habDTO = new HabilidadDTO(hab.getId(), hab.getUser().getId(), hab.getTipo(), hab.getNivel(), hab.getNombre(), hab.getProgreso(), hab.getIcono());
        return habDTO;
    }

    @Override
    public List<HabilidadDTO> listarHabilidades() {
        List<Habilidad> listaHab = repo.findAll();
        List<HabilidadDTO> listaDTO = new ArrayList<HabilidadDTO>();
        for (int i = 0; i < listaHab.size(); i++) {
            Habilidad hab = listaHab.get(i);
            HabilidadDTO habDTO = new HabilidadDTO(hab.getId(), hab.getUser().getId(), hab.getTipo(), hab.getNivel(), hab.getNombre(), hab.getProgreso(), hab.getIcono());
            listaDTO.add(habDTO);
        }
        return listaDTO;
    }

    @Override
    public void borrarHabilidad(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void crearHabilidadDefault(Long persona_id) {
        User userDefualt = userRepo.findById(persona_id).orElse(null);
        Habilidad defaultFront = new Habilidad(userDefualt, "Frontend", "Intermedio", "Habilidad Front #1", 80, "fa-brands fa-angular");
        Habilidad defaultBack = new Habilidad(userDefualt, "Backend", "Intermedio", "Habilidad Back #1", 80, "fa-brands fa-java");
        Habilidad defaultSoft = new Habilidad(userDefualt, "Soft", "", "Habilidad Blanda #1", 100, "fa-solid fa-comment-dots");
        repo.save(defaultFront);
        repo.save(defaultBack);
        repo.save(defaultSoft);
    }
}