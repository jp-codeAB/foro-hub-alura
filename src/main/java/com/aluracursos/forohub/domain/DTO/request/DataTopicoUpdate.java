package com.aluracursos.forohub.domain.DTO.request;

import jakarta.validation.constraints.NotBlank;

public record DataTopicoUpdate(@NotBlank String mensaje) {}