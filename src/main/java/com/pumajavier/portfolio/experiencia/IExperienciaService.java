package com.pumajavier.portfolio.experiencia;

import com.pumajavier.portfolio.security.entity.User;
import java.util.List;

public interface IExperienciaService {

    public void agregarExperiencia(ExperienciaDTO exp);

    public void editarExperiencia(Long id, ExperienciaDTO datosExperiencia);

    public List<ExperienciaDTO> listarExperiencia();

    public ExperienciaDTO buscarExperiencia(Long id);

    public void borrarExperiencia(Long id);

    public void crearExperienciaDefault(Long persona_id);

}