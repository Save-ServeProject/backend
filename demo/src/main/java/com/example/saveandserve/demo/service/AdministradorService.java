package com.example.saveandserve.demo.service;

import com.example.saveandserve.demo.dto.CreateAdministradorDTO;
import com.example.saveandserve.demo.entity.Administrador;
import com.example.saveandserve.demo.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Para codificar la contraseña

    public Administrador nuevoAdministrador(CreateAdministradorDTO dto) {
        Administrador admin = Administrador.builder()
                .nombreUsuario(dto.getNombreUsuario())
                .contrasenia(passwordEncoder.encode(dto.getContrasenia()))
                .nombreCompleto(dto.getNombreCompleto())
                .email(dto.getEmail())
                .creadoEn(LocalDateTime.now())
                .lastPasswordChangeAt(LocalDateTime.now())
                .build();

        return administradorRepository.save(admin);
    }

    public Optional<Administrador> obtenerAdministradorPorId(Long id) {
        return administradorRepository.findById(id);
    }


    public Administrador actualizarAdministrador(Long id, CreateAdministradorDTO dto) {
        Administrador admin = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));

        admin.setNombreUsuario(dto.getNombreUsuario());
        admin.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
        admin.setNombreCompleto(dto.getNombreCompleto());
        admin.setEmail(dto.getEmail());

        return administradorRepository.save(admin);
    }


    public void eliminarAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }
}
