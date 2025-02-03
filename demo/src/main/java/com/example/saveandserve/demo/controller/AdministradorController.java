package com.example.saveandserve.demo.controller;

import com.example.saveandserve.demo.dto.CreateAdministradorDTO;
import com.example.saveandserve.demo.dto.GetAdministradorDto;
import com.example.saveandserve.demo.dto.converter.AdministradorDtoConverter;
import com.example.saveandserve.demo.entity.Administrador;
import com.example.saveandserve.demo.service.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administradores")
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorService administradorService;
    private final AdministradorDtoConverter administradorDtoConverter;

    @PostMapping("/")
    public ResponseEntity<GetAdministradorDto> nuevoAdministrador(@RequestBody CreateAdministradorDTO nuevoAdministrador) {
        Administrador adminCreado = administradorService.nuevoAdministrador(nuevoAdministrador);
        GetAdministradorDto respuesta = administradorDtoConverter.convertAdministradorEntityToGetAdministradorDto(adminCreado);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping("/me")
    public ResponseEntity<GetAdministradorDto> yo(@AuthenticationPrincipal Administrador admin) {
        GetAdministradorDto respuesta = administradorDtoConverter.convertAdministradorEntityToGetAdministradorDto(admin);
        return ResponseEntity.ok(respuesta);
    }

}
