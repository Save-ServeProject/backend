package com.example.saveandserve.demo.controller;

import com.example.saveandserve.demo.dto.GetAdministradorDto;
import com.example.saveandserve.demo.dto.converter.AdministradorDtoConverter;
import com.example.saveandserve.demo.entity.Administrador;
import com.example.saveandserve.demo.security.jwt.JwtProvider;
import com.example.saveandserve.demo.security.jwt.model.JwtAdministradorResponse;
import com.example.saveandserve.demo.security.jwt.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;
    private final AdministradorDtoConverter administradorDtoConverter;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtAdministradorResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Administrador admin = (Administrador) authentication.getPrincipal();
        String jwtToken = tokenProvider.generateToken(authentication);
        JwtAdministradorResponse respuesta = JwtAdministradorResponse.builder()
                .nombreUsuario(admin.getNombreUsuario())
                .roles(Collections.singleton("ADMINISTRADOR"))
                .token(jwtToken)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping("/administradores/me")
    public ResponseEntity<GetAdministradorDto> me(@AuthenticationPrincipal Administrador admin) {
        GetAdministradorDto respuesta = administradorDtoConverter.convertAdministradorEntityToGetAdministradorDto(admin);
        return ResponseEntity.ok(respuesta);
    }
}
