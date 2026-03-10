package com.aluracursos.forohub.service;

import com.aluracursos.forohub.domain.DTO.request.DataRespuestaRegistration;
import com.aluracursos.forohub.domain.DTO.request.DataRespuestaUpdate;
import com.aluracursos.forohub.domain.respuesta.Respuesta;
import com.aluracursos.forohub.domain.user.UserEntity;
import com.aluracursos.forohub.repository.RespuestaRepository;
import com.aluracursos.forohub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Respuesta crear(DataRespuestaRegistration data, UserEntity autor) {
        var topico = topicoRepository.findById(data.topicoId())
                .orElseThrow(() -> new EntityNotFoundException("El tópico con ID " + data.topicoId() + " no existe."));

        return respuestaRepository.save(new Respuesta(data.mensaje(), topico, autor));
    }

    @Transactional
    public Respuesta actualizar(Long id, DataRespuestaUpdate data) {
        if (!respuestaRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró la respuesta con ID: " + id);
        }
        var respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizarMensaje(data.mensaje());
        return respuesta;
    }

    public void eliminar(Long id) {
        if (!respuestaRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar: ID " + id + " no encontrado.");
        }
        respuestaRepository.deleteById(id);
    }
}