package com.example.saveandserve.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.saveandserve.demo.entity.Empresa;
import com.example.saveandserve.demo.service.EmpresaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> obtenerEmpresas() {
        List<Empresa> empresas = empresaService.obtenerTodas();
        return empresas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtenerEmpresaPorId(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.obtenerPorId(id);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> registrar(@RequestBody Empresa empresa) {
        Empresa nuevaEmpresa = empresaService.guardar(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> actualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        Optional<Empresa> empresaActualizada = empresaService.actualizar(id, empresa);
        return empresaActualizada.map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        boolean eliminado = empresaService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<Empresa>> obtenerEmpresasPaginadas(
            @RequestParam(defaultValue = "0") int page,    
            @RequestParam(defaultValue = "10") int size,   
            @RequestParam(defaultValue = "nombre") String sortBy 
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Empresa> empresas = empresaService.obtenerEmpresasPaginadas(pageable);
        return ResponseEntity.ok(empresas);
    }

}
