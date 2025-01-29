package com.example.saveandserve.demo.service;

import com.example.saveandserve.demo.entity.Transporte;
import com.example.saveandserve.demo.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;

    public List<Transporte> obtenerTodos() {
        return transporteRepository.findAll();
    }

    public Optional<Transporte> obtenerPorId(Long id) {
        return transporteRepository.findById(id);
    }

    public Transporte guardar(Transporte transporte) {
        return transporteRepository.save(transporte);
    }

    public Transporte actualizar(Long id, Transporte transporteActualizado) {
        return transporteRepository.findById(id)
            .map(transporte -> {
                transporte.setNombreTransporte(transporteActualizado.getNombreTransporte());
                return transporteRepository.save(transporte);
            })
            .orElseThrow(() -> new RuntimeException("Transporte no encontrado"));
    }

    public void eliminar(Long id) {
        transporteRepository.deleteById(id);
    }
}
