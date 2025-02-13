package com.example.saveandserve.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.saveandserve.demo.entity.Donacion;
import com.example.saveandserve.demo.entity.LineaProducto;
import com.example.saveandserve.demo.entity.Producto;
import com.example.saveandserve.demo.repository.DonacionRepository;
import com.example.saveandserve.demo.repository.LineaProductoRepository;
import com.example.saveandserve.demo.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class DonacionService {

    private final DonacionRepository donacionRepository;
    private final ProductoRepository productoRepository;
    private final LineaProductoRepository lineaProductoRepository;

    public DonacionService(DonacionRepository donacionRepository, ProductoRepository productoRepository, LineaProductoRepository lineaProductoRepository) {
        this.donacionRepository = donacionRepository;
        this.productoRepository = productoRepository;
        this.lineaProductoRepository = lineaProductoRepository;
    }

    @Transactional
    public Donacion registrar(Donacion donacion) {
        BigDecimal totalDonacion = BigDecimal.ZERO;
    
        for (LineaProducto lp : donacion.getLineasProducto()) {
            Producto producto = lp.getProducto();
    
            // Verificar si el producto ya existe
            Optional<Producto> productoExistente = productoRepository.findByIdProducto(producto.getIdProducto());
    
            if (productoExistente.isPresent()) {
                lp.setProducto(productoExistente.get()); // Usar el existente
            } else {
                producto = productoRepository.save(producto); // Guardar el nuevo producto
                lp.setProducto(producto);
            }
    
            // Calcular el subtotal
            lp.setSubtotal(lp.getPrecioUnitario().multiply(BigDecimal.valueOf(lp.getCantidad())));
            totalDonacion = totalDonacion.add(lp.getSubtotal());
    
            // **Asignar la donación a la línea de producto**
            lp.setDonacion(donacion);
        }
    
        // Asignar el total de la donación
        donacion.setTotalDonacion(totalDonacion);
    
        // **Guardar la donación primero**
        Donacion nuevaDonacion = donacionRepository.save(donacion);
    
        // **Guardar las líneas de producto asociadas a la donación**
        lineaProductoRepository.saveAll(donacion.getLineasProducto());
    
        return nuevaDonacion;
    }
    

    public List<Donacion> obtenerTodas() {
        return donacionRepository.findAll();
    }

    public Optional<Donacion> obtenerPorId(Long id) {
        return donacionRepository.findById(id);
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
    public List<Donacion> obtenerPorEmpresaId(Long empresaId) {
        return donacionRepository.findByEmpresaId(empresaId);
    }
}
