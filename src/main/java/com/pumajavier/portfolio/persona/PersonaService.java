package com.pumajavier.portfolio.persona;

import com.pumajavier.portfolio.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    PersonaRepository repo;
    @Autowired
    UserRepository userRepo;

    @Override
    public Persona buscarPersona(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void crearPersona(Persona pers) {
        repo.save(pers);
    }

    @Override
    public void editarPersona(Long id, Persona datosPersona) {
        Persona pers = buscarPersona(id);
        pers = datosPersona;
        pers.setUser(userRepo.findById(id).orElse(null));
        repo.save(pers);
    }
}