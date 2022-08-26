package com.pumajavier.portfolio.persona;

/**
 *
 * @author JAVIER
 */
public interface IPersonaService {
     public Persona buscarPersona(Long id);
    public void crearPersona(Persona pers);
    public void editarPersona(Long id, Persona datosPersona);
}
