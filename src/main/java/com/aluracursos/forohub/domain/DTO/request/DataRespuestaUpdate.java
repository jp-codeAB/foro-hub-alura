package com.aluracursos.forohub.domain.DTO.request;

import jakarta.validation.constraints.NotBlank;

public record DataRespuestaUpdate(
        @NotBlank(message = "El nuevo mensaje no puede estar vacío")
        String mensaje
) {}
