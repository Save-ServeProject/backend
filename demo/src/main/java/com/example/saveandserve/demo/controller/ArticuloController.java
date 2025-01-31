package com.example.saveandserve.demo.controller;

import com.example.saveandserve.demo.entity.Articulo;
import com.example.saveandserve.demo.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping
    public ResponseEntity<List<Articulo>> getAllArticulos() {
        return ResponseEntity.ok(articuloService.getAllArticulos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> getArticuloById(@PathVariable Long id) {
        Optional<Articulo> articulo = articuloService.getArticuloById(id);
        return articulo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Articulo> createArticulo(@RequestBody Articulo articulo) {
        return ResponseEntity.ok(articuloService.saveArticulo(articulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticulo(@PathVariable Long id) {
        articuloService.deleteArticulo(id);
        return ResponseEntity.noContent().build();
    }
}
