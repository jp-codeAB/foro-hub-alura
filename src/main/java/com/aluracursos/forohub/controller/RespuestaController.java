package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.DTO.request.DataRespuestaRegistration;
import com.aluracursos.forohub.domain.DTO.request.DataRespuestaUpdate;
import com.aluracursos.forohub.domain.DTO.response.DataRespuestaResponse;
import com.aluracursos.forohub.domain.user.UserEntity;
import com.aluracursos.forohub.repository.RespuestaRepository;
import com.aluracursos.forohub.service.RespuestaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService service;
    @Autowired
    private RespuestaRepository repository;

    @PostMapping
    public ResponseEntity<DataRespuestaResponse> crear(@RequestBody @Valid DataRespuestaRegistration data,
                                                       @AuthenticationPrincipal UserEntity autor) {
        var respuesta = service.crear(data, autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataRespuestaResponse(respuesta));
    }

    @GetMapping
    public ResponseEntity<Page<DataRespuestaResponse>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(repository.findAll(paginacion).map(DataRespuestaResponse::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataRespuestaResponse> obtenerPorId(@PathVariable Long id) {
        var respuesta = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada."));
        return ResponseEntity.ok(new DataRespuestaResponse(respuesta));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DataRespuestaResponse> actualizar(@PathVariable Long id, @RequestBody @Valid DataRespuestaUpdate data) {
        var respuesta = service.actualizar(id, data);
        return ResponseEntity.ok(new DataRespuestaResponse(respuesta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
