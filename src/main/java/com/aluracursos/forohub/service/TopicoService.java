package com.aluracursos.forohub.service;

import com.aluracursos.forohub.domain.DTO.request.DataTopicoRegistration;
import com.aluracursos.forohub.domain.DTO.request.DataTopicoUpdate;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.user.UserEntity;
import com.aluracursos.forohub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository repository;

    public Topico crear(DataTopicoRegistration data, UserEntity autor) {
        return repository.save(new Topico(data.titulo(), data.mensaje(), autor));
    }

    @Transactional
    public Topico actualizar(Long id, DataTopicoUpdate data) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró el tópico con el ID suministrado: " + id);
        }

        var topico = repository.getReferenceById(id);
        topico.actualizarMensaje(data.mensaje());
        return topico;
    }

}
