package com.aluracursos.forohub.domain.DTO.response;

import com.aluracursos.forohub.domain.respuesta.Respuesta;

import java.time.LocalDateTime;

public record DataRespuestaResponse(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        Long topicoId,
        Boolean solucion
) {
    public DataRespuestaResponse(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(),
                respuesta.getAutor().getNombre(), respuesta.getTopico().getId(), respuesta.getSolucion());
    }
}
