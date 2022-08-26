package com.pumajavier.portfolio.experiencia;

import com.pumajavier.portfolio.security.entity.User;
import com.pumajavier.portfolio.security.repository.UserRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienciaService implements IExperienciaService {

    @Autowired
    ExperienciaRepository repo;
    @Autowired
    UserRepository userRepo;

    @Override
    public void agregarExperiencia(ExperienciaDTO exp) {
        User user = userRepo.findById(exp.getPersona_id()).orElse(null);
        Experiencia nuevaEdu = new Experiencia(user, exp.getPuesto(), exp.getEmpleador(), exp.getFecha(), exp.getDescripcion());
        repo.save(nuevaEdu);
    }

    @Override
    public void editarExperiencia(Long id, ExperienciaDTO datosExperiencia) {
        Experiencia expEditable = repo.findById(id).orElse(null);
        expEditable.setUser(userRepo.findById(datosExperiencia.getPersona_id()).orElse(null));
        expEditable.setPuesto(datosExperiencia.getPuesto());
        expEditable.setEmpleador(datosExperiencia.getEmpleador());
        expEditable.setFecha(datosExperiencia.getFecha());
        expEditable.setDescripcion(datosExperiencia.getDescripcion());
        repo.save(expEditable);
    }

    @Override
    public List<ExperienciaDTO> listarExperiencia() {
        List<Experiencia> listaExp = repo.findAll();
        List<ExperienciaDTO> listaDTO = new ArrayList<ExperienciaDTO>();
        for (int i = 0; i < listaExp.size(); i++) {
            Experiencia exp = listaExp.get(i);
            ExperienciaDTO expDTO = new ExperienciaDTO(exp.getId(), exp.getUser().getId(), exp.getPuesto(), exp.getEmpleador(), exp.getFecha(), exp.getDescripcion());
            listaDTO.add(expDTO);
        }
        return listaDTO;
    }

    @Override
    public ExperienciaDTO buscarExperiencia(Long id) {
        Experiencia exp = repo.findById(id).orElse(null);
        ExperienciaDTO expDTO = new ExperienciaDTO(exp.getId(), exp.getUser().getId(), exp.getPuesto(), exp.getEmpleador(), exp.getFecha(), exp.getDescripcion());
        return expDTO;
    }

    @Override
    public void borrarExperiencia(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void crearExperienciaDefault(Long persona_id) {
        User userDefault = userRepo.findById(persona_id).orElse(null);
        Experiencia expDefault = new Experiencia(userDefault, "Puesto", "Empresa", "Inicio - Fin", "Descripción...");
        repo.save(expDefault);
    }

}