package com.pumajavier.portfolio.habilidad;

import java.util.List;

public interface IHabilidadService {

    public void agregarHabilidad(HabilidadDTO hab);

    public void editarHabilidad(Long id, HabilidadDTO datsoHabilidad);

    public HabilidadDTO buscarHabilidad(Long id);

    public List<HabilidadDTO> listarHabilidades();

    public void borrarHabilidad(Long id);

    public void crearHabilidadDefault(Long persona_id);

}