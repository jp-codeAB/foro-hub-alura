package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.DTO.DataUserRegistration;
import com.aluracursos.forohub.domain.DTO.DataUserResponse;
import com.aluracursos.forohub.domain.user.DataAuthentication;
import com.aluracursos.forohub.domain.user.DataTokenJWT;
import com.aluracursos.forohub.domain.user.UserEntity;
import com.aluracursos.forohub.repository.UserRepository;
import com.aluracursos.forohub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DataAuthentication data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.email(), data.contrasena());
        var usuarioAutenticado = manager.authenticate(authToken);
        var tokenJWT = tokenService.generarToken((UserEntity) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity<DataUserResponse> registrarUsuario(
            @RequestBody @Valid DataUserRegistration data) {

        String hashPassword = passwordEncoder.encode(data.contrasena());
        UserEntity usuario = new UserEntity(null, data.nombre(), hashPassword, data.email());
        userRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataUserResponse(usuario));
    }
}
