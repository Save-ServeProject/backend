package com.example.saveandserve.demo.service;

import com.example.saveandserve.demo.entity.BancoDeAlimentos;
import com.example.saveandserve.demo.repository.BancoDeAlimentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoDeAlimentosService {

    @Autowired
    private BancoDeAlimentosRepository bancoDeAlimentosRepository;

    public List<BancoDeAlimentos> obtenerTodos() {
        return bancoDeAlimentosRepository.findAll();
    }

    public Optional<BancoDeAlimentos> obtenerPorId(Long id) {
        return bancoDeAlimentosRepository.findById(id);
    }

    public BancoDeAlimentos registrar(BancoDeAlimentos banco) {
        return bancoDeAlimentosRepository.save(banco);
    }

    public Optional<BancoDeAlimentos> actualizar(Long id, BancoDeAlimentos bancoActualizado) {
        return bancoDeAlimentosRepository.findById(id).map(bancoExistente -> {
            bancoExistente.setNombre(bancoActualizado.getNombre());
            bancoExistente.setDireccion(bancoActualizado.getDireccion());
            bancoExistente.setTelefono(bancoActualizado.getTelefono());
            bancoExistente.setEmail(bancoActualizado.getEmail());
            bancoExistente.setCiudad(bancoActualizado.getCiudad());
            bancoExistente.setContrasenia(bancoActualizado.getContrasenia());
            return bancoDeAlimentosRepository.save(bancoExistente);
        });
    }

    public void eliminar(Long id) {
        bancoDeAlimentosRepository.deleteById(id);
    }
}
