package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.DTO.request.DataTopicoRegistration;
import com.aluracursos.forohub.domain.DTO.request.DataTopicoUpdate;
import com.aluracursos.forohub.domain.DTO.response.DataTopicoResponse;
import com.aluracursos.forohub.domain.user.UserEntity;
import com.aluracursos.forohub.repository.TopicoRepository;
import com.aluracursos.forohub.service.TopicoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;
    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity<DataTopicoResponse> crear(@RequestBody @Valid DataTopicoRegistration data,
                                                    @AuthenticationPrincipal UserEntity autor) {
        var topico = service.crear(data, autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataTopicoResponse(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DataTopicoResponse>> listar(Pageable paginacion) {
        return ResponseEntity.ok(repository.findAll(paginacion).map(DataTopicoResponse::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataTopicoResponse> detalle(@PathVariable Long id) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        return ResponseEntity.ok(new DataTopicoResponse(topico));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DataTopicoResponse> actualizar(@PathVariable Long id, @RequestBody @Valid DataTopicoUpdate data) {
        var topico = service.actualizar(id, data);
        return ResponseEntity.ok(new DataTopicoResponse(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
