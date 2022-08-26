package com.pumajavier.portfolio.educacion;

import java.util.List;

public interface IEducacionService {
    public void agregarEducacion(EducacionDTO edu);
    
    public void editarEducacion(Long id, EducacionDTO datosEducacion);
    
    public List<EducacionDTO> listarEducacion();
    
    public EducacionDTO buscarEducacion(Long id);
    
    public void borrarEducacion(Long id);
    
    public void crearEducacionDefault(Long persona_id);
}