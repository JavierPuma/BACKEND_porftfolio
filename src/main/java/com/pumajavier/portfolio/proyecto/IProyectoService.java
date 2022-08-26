package com.pumajavier.portfolio.proyecto;

import java.util.List;

public interface IProyectoService {
    public void agregarProyecto(ProyectoDTO proj);
    
    public void editarProyecto(Long id, ProyectoDTO datosProyecto);
    
    public ProyectoDTO buscarProyecto(Long id);
    
    public List<ProyectoDTO> listarProyectos();
    
    public void borrarProyecto(Long id);  
    
    public void crearProyectoDefault(Long persona_id);
}