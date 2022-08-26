package com.pumajavier.portfolio.security.repository;

import com.pumajavier.portfolio.security.entity.ERole;
import com.pumajavier.portfolio.security.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(ERole name);
}
