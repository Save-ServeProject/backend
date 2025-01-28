package com.example.saveandserve.demo.service;

import com.example.saveandserve.demo.entity.Donacion;
import com.example.saveandserve.demo.repository.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonacionService {

    @Autowired
    private DonacionRepository donacionRepository;

    public List<Donacion> obtenerTodas() {
        return donacionRepository.findAll();
    }

    public Optional<Donacion> obtenerPorId(Long id) {
        return donacionRepository.findById(id);
    }

    public Donacion registrar(Donacion donacion) {
        return donacionRepository.save(donacion);
    }

    public Optional<Donacion> actualizar(Long id, Donacion donacionActualizada) {
        return donacionRepository.findById(id).map(donacionExistente -> {
            donacionExistente.setTotalDonacion(donacionActualizada.getTotalDonacion());
            donacionExistente.setFechaEntrega(donacionActualizada.getFechaEntrega());
            donacionExistente.setEstadoEnvio(donacionActualizada.getEstadoEnvio());
            donacionExistente.setEmpresa(donacionActualizada.getEmpresa());
            donacionExistente.setBancoDeAlimentos(donacionActualizada.getBancoDeAlimentos());
            donacionExistente.setTransporte(donacionActualizada.getTransporte());
            return donacionRepository.save(donacionExistente);
        });
    }

    public void eliminar(Long id) {
        donacionRepository.deleteById(id);
    }
}
