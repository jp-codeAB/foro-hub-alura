package com.aluracursos.forohub.domain.DTO.response;

import com.aluracursos.forohub.domain.topico.Topico;

public record DataTopicoResponse(Long id, String titulo, String mensaje, String status, String autor) {
    public DataTopicoResponse(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getStatus(), topico.getAutor().getNombre());
    }
}
