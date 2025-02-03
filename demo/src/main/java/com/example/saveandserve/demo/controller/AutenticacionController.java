package com.example.saveandserve.demo.controller;

import com.example.saveandserve.demo.dto.GetUserDto;
import com.example.saveandserve.demo.dto.converter.UsuarioDtoConverter;
import com.example.saveandserve.demo.entity.Usuario;
import com.example.saveandserve.demo.security.jwt.JwtProvider;
import com.example.saveandserve.demo.security.jwt.entidades.JwtUserResponse;
import com.example.saveandserve.demo.security.jwt.entidades.LoginRequest;
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
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;
    private final UsuarioDtoConverter usuarioDtoConverter;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        // Autenticación con email en lugar de nombre de usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        String jwtToken = tokenProvider.generateToken(authentication);

        // Convertimos los roles de la entidad Usuario a un Set de Strings
        Set<String> roles = usuario.getRoles().stream()
                .map(rol -> rol.name())  // Convertir cada rol a su nombre (e.g., "BANCO_DE_ALIMENTOS")
                .collect(Collectors.toSet());

        JwtUserResponse respuesta = JwtUserResponse.builder()
                .username(usuario.getUsername())  // Nombre de usuario de la entidad Usuario
                .roles(roles)  // Roles obtenidos de la entidad Usuario
                .token(jwtToken)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping("/usuarios/me")
    public ResponseEntity<GetUsuarioDto> me(@AuthenticationPrincipal Usuario usuario) {
        // Convertir la entidad Usuario a DTO usando el conversor
        GetUsuarioDto respuesta = usuarioDtoConverter.convertUsuarioEntityToGetUsuarioDto(usuario);
        return ResponseEntity.ok(respuesta);
    }
}
