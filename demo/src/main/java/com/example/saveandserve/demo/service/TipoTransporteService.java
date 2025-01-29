package com.example.saveandserve.demo.service;

import com.example.saveandserve.demo.entity.TipoTransporte;
import com.example.saveandserve.demo.repository.TipoTransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoTransporteService {

    @Autowired
    private TipoTransporteRepository tipoTransporteRepository;

    public List<TipoTransporte> obtenerTodos() {
        return tipoTransporteRepository.findAll();
    }

    public Optional<TipoTransporte> obtenerPorId(Long id) {
        return tipoTransporteRepository.findById(id);
    }

    public TipoTransporte guardar(TipoTransporte tipoTransporte) {
        return tipoTransporteRepository.save(tipoTransporte);
    }

    public TipoTransporte actualizar(Long id, TipoTransporte tipoTransporteActualizado) {
        return tipoTransporteRepository.findById(id)
            .map(tipoTransporte -> {
                tipoTransporte.setTipo(tipoTransporteActualizado.getTipo());
                return tipoTransporteRepository.save(tipoTransporte);
            })
            .orElseThrow(() -> new RuntimeException("Tipo de transporte no encontrado"));
    }

    public void eliminar(Long id) {
        tipoTransporteRepository.deleteById(id);
    }
}
