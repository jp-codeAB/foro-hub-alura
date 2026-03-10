package com.aluracursos.forohub.domain.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRespuestaRegistration(
        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,
        @NotNull(message = "El ID del tópico es obligatorio")
        Long topicoId
) {}