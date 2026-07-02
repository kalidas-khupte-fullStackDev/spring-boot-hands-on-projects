package com.ecommerce.project.repository;

import com.ecommerce.project.model.Role;
import com.ecommerce.project.model.User;
import com.ecommerce.project.model.enums.AppRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
//    @Query("SELECT u FROM User u JOIN FETCH u.roles")
//    @EntityGraph(attributePaths = {"roles"})
    Optional<Role> findByRoleName(AppRole appRole);

    boolean existsByRoleName(AppRole appRole);
}
