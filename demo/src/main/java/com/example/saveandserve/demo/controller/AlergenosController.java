package com.example.saveandserve.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.saveandserve.demo.entity.Alergenos;
import com.example.saveandserve.demo.service.AlergenosService;

@RestController
@RequestMapping("/alergenos")
public class AlergenosController {

    private final AlergenosService alergenosService;

    public AlergenosController(AlergenosService alergenosService) {
        this.alergenosService = alergenosService;
    }

    @GetMapping
    public List<Alergenos> getAllAlergenos() {
        return alergenosService.getAllAlergenos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alergenos> getAlergenoById(@PathVariable Long id) {
        Optional<Alergenos> alergeno = alergenosService.getAlergenoById(id);
        return alergeno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Alergenos saveAlergeno(@RequestBody Alergenos alergeno) {
        return alergenosService.saveAlergeno(alergeno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlergeno(@PathVariable Long id) {
        alergenosService.deleteAlergeno(id);
        return ResponseEntity.noContent().build();
    }
}
