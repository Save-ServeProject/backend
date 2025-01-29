package com.example.saveandserve.demo.controller;

import com.example.saveandserve.demo.entity.TipoTransporte;
import com.example.saveandserve.demo.service.TipoTransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipo-transporte")
public class TipoTransporteController {

    @Autowired
    private TipoTransporteService tipoTransporteService;

    @GetMapping
    public List<TipoTransporte> obtenerTodos() {
        return tipoTransporteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTransporte> obtenerPorId(@PathVariable Long id) {
        Optional<TipoTransporte> tipoTransporte = tipoTransporteService.obtenerPorId(id);
        return tipoTransporte.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoTransporte crear(@RequestBody TipoTransporte tipoTransporte) {
        return tipoTransporteService.guardar(tipoTransporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoTransporte> actualizar(@PathVariable Long id, @RequestBody TipoTransporte tipoTransporte) {
        try {
            TipoTransporte actualizado = tipoTransporteService.actualizar(id, tipoTransporte);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tipoTransporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
