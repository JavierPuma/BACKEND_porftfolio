package com.pumajavier.portfolio.seccion;

import com.pumajavier.portfolio.security.entity.User;
import java.util.List;


public interface ISeccionService {
    public void crearSeccion(SeccionDTO sec);
    
    public List<SeccionDTO> listarSecciones();
    
    public void eliminarSeccion(Long id);
    
    public void editarSeccion(Long id, SeccionDTO datosSeccion);
    
    public SeccionDTO buscarSeccion(Long id);
    
    public void crearSeccionesDefault(Long persona_id);
}