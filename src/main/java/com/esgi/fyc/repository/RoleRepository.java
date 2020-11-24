package com.esgi.fyc.repository;

import com.esgi.fyc.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByRoleName(String name);
}
