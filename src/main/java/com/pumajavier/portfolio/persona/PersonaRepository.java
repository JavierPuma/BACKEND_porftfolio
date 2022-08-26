package com.pumajavier.portfolio.persona;

/**
 *
 * @author JAVIER
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    
}