package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.user.DataAuthentication;
import com.aluracursos.forohub.domain.user.DataTokenJWT;
import com.aluracursos.forohub.domain.user.UserEntity;
import com.aluracursos.forohub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DataAuthentication data){
        try{
            var token = new UsernamePasswordAuthenticationToken(data.email(), data.contrasena());
            var auth = manager.authenticate(token);
            var tokenJWT = tokenService.generarToken((UserEntity) auth.getPrincipal());
            return ResponseEntity.ok(new DataTokenJWT(tokenJWT));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
