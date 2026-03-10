package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByEmail(String username);
}
