package com.aluracursos.forohub.domain.DTO;

import jakarta.validation.constraints.NotBlank;

public record DataTopicoRegistration(
        @NotBlank(message = "El título es obligatorio") String titulo,
        @NotBlank(message = "El mensaje no puede estar vacío") String mensaje
) {}