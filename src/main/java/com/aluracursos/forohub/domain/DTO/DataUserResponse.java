package com.aluracursos.forohub.domain.DTO;

import com.aluracursos.forohub.domain.user.UserEntity;

public record DataUserResponse(Long id, String nombre, String email) {
    public DataUserResponse(UserEntity user) {
        this(user.getId(), user.getNombre(), user.getEmail());
    }
}
