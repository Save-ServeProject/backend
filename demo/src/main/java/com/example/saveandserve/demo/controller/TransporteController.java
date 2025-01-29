package com.example.saveandserve.demo.controller;

import com.example.saveandserve.demo.entity.Transporte;
import com.example.saveandserve.demo.service.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transportes")
public class TransporteController {

    @Autowired
    private TransporteService transporteService;

    @GetMapping
    public List<Transporte> obtenerTodos() {
        return transporteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transporte> obtenerPorId(@PathVariable Long id) {
        Optional<Transporte> transporte = transporteService.obtenerPorId(id);
        return transporte.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transporte crear(@RequestBody Transporte transporte) {
        return transporteService.guardar(transporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transporte> actualizar(@PathVariable Long id, @RequestBody Transporte transporte) {
        try {
            Transporte actualizado = transporteService.actualizar(id, transporte);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        transporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
