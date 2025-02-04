package com.example.saveandserve.demo.dto.converter;

import com.example.saveandserve.demo.dto.GetUserDto;
import com.example.saveandserve.demo.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsuarioDtoConverter {

    public GetUserDto convertUsuarioEntityToGetUsuarioDto(Usuario usuario) {
        Set<String> roles = usuario.getRoles().stream()
                .map(Enum::name) // Convertir los roles de Enum a String
                .collect(Collectors.toSet());

        return new GetUserDto(
                usuario.getUsername(),
                roles
        );
    }
}

