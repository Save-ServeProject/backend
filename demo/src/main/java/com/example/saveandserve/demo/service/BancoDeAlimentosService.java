package com.example.saveandserve.demo.service;

import com.example.saveandserve.demo.entity.BancoDeAlimentos;
import com.example.saveandserve.demo.repository.BancoDeAlimentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoDeAlimentosService {

    @Autowired
    private BancoDeAlimentosRepository bancoDeAlimentosRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    public List<BancoDeAlimentos> obtenerTodos() {
        return bancoDeAlimentosRepository.findAll();
    }

    public Optional<BancoDeAlimentos> obtenerPorId(Long id) {
        return bancoDeAlimentosRepository.findById(id);
    }

    public BancoDeAlimentos registrar(BancoDeAlimentos banco) {
        return saveBanco(banco);
    }

    public Optional<BancoDeAlimentos> actualizar(Long id, BancoDeAlimentos bancoActualizado) {
        return bancoDeAlimentosRepository.findById(id).map(bancoExistente -> {
            bancoExistente.setNombre(bancoActualizado.getNombre());
            bancoExistente.setDireccion(bancoActualizado.getDireccion());
            bancoExistente.setTelefono(bancoActualizado.getTelefono());
            bancoExistente.setEmail(bancoActualizado.getEmail());
            bancoExistente.setCiudad(bancoActualizado.getCiudad());

            if (bancoActualizado.getContrasenia() != null && !bancoActualizado.getContrasenia().isEmpty()) {
                bancoExistente.setContrasenia(passwordEncoder.encode(bancoActualizado.getContrasenia()));
            }

            return bancoDeAlimentosRepository.save(bancoExistente);
        });
    }

    public void eliminar(Long id) {
        bancoDeAlimentosRepository.deleteById(id);
    }

    public BancoDeAlimentos saveBanco(BancoDeAlimentos banco) {
        banco.setContrasenia(passwordEncoder.encode(banco.getContrasenia())); // 🔒 Encripta la contraseña
        return bancoDeAlimentosRepository.save(banco);
    }
    
    public BancoDeAlimentos loadBancoById(Long id) {
        return bancoDeAlimentosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banco de alimentos no encontrado"));
    }

    public Optional<BancoDeAlimentos> obtenerPorEmail(String email) { 
        return bancoDeAlimentosRepository.findByEmail(email);
    }
    
}
