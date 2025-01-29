package com.example.saveandserve.demo.service;

import com.example.saveandserve.demo.entity.LineaProducto;
import com.example.saveandserve.demo.repository.LineaProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineaProductoService {

    private final LineaProductoRepository lineaProductoRepository;

    public LineaProductoService(LineaProductoRepository lineaProductoRepository) {
        this.lineaProductoRepository = lineaProductoRepository;
    }

    public List<LineaProducto> getAllLineasProducto() {
        return lineaProductoRepository.findAll();
    }

    public Optional<LineaProducto> getLineaProductoById(Long id) {
        return lineaProductoRepository.findById(id);
    }

    public List<LineaProducto> getLineasProductoByDonacion(Long donacionId) {
        return lineaProductoRepository.findByDonacionId(donacionId);
    }

    public LineaProducto saveLineaProducto(LineaProducto lineaProducto) {
        return lineaProductoRepository.save(lineaProducto);
    }

    public void deleteLineaProducto(Long id) {
        lineaProductoRepository.deleteById(id);
    }
}
