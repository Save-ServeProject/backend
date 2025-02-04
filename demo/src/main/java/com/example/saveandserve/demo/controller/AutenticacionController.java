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

import jakarta.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;
    private final UsuarioDtoConverter usuarioDtoConverter;

    // @PostMapping("/auth/login")
    // public ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
    //     Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
    //     );

    //     SecurityContextHolder.getContext().setAuthentication(authentication);
    //     Usuario usuario = (Usuario) authentication.getPrincipal();
    //     String jwtToken = tokenProvider.generateToken(authentication);

    //     Set<String> roles = usuario.getRoles().stream()
    //             .map(Enum::name) // Convertir los roles de Enum a String
    //             .collect(Collectors.toSet());

    //     JwtUserResponse respuesta = JwtUserResponse.builder()
    //             .username(usuario.getUsername()) 
    //             .roles(roles)  
    //             .token(jwtToken)
    //             .build();

    //     return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    // }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        String jwtToken = tokenProvider.generateToken(authentication);

        Set<String> roles = usuario.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());

        JwtUserResponse respuesta = JwtUserResponse.jwtUserResponseBuilder()
                .username(usuario.getUsername())
                .fullName(usuario.getFullName())
                .email(usuario.getEmail())
                .roles(roles)
                .token(jwtToken)
                .build();

        
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }


    @GetMapping("/usuarios/me")
    public ResponseEntity<GetUserDto> me(@AuthenticationPrincipal Usuario usuario) {
        GetUserDto respuesta = usuarioDtoConverter.convertUsuarioEntityToGetUsuarioDto(usuario);
        return ResponseEntity.ok(respuesta);
    }
}
