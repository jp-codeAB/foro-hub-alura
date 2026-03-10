package com.aluracursos.forohub.domain.DTO;

import jakarta.validation.constraints.NotBlank;

public record DataTopicoUpdate(@NotBlank String mensaje) {}