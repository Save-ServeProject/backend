package com.example.saveandserve.demo.controller;

import com.example.saveandserve.demo.entity.BancoDeAlimentos;
import com.example.saveandserve.demo.service.BancoDeAlimentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bancos")
public class BancoDeAlimentosController {

    @Autowired
    private BancoDeAlimentosService bancoDeAlimentosService;

    @GetMapping
    public ResponseEntity<List<BancoDeAlimentos>> obtenerTodos() {
        List<BancoDeAlimentos> bancos = bancoDeAlimentosService.obtenerTodos();
        return bancos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(bancos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoDeAlimentos> obtenerPorId(@PathVariable Long id) {
        Optional<BancoDeAlimentos> banco = bancoDeAlimentosService.obtenerPorId(id);
        return banco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BancoDeAlimentos> registrar(@RequestBody BancoDeAlimentos banco) {
        BancoDeAlimentos nuevoBanco = bancoDeAlimentosService.registrar(banco);
        return ResponseEntity.ok(nuevoBanco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BancoDeAlimentos> actualizar(@PathVariable Long id, @RequestBody BancoDeAlimentos bancoActualizado) {
        Optional<BancoDeAlimentos> banco = bancoDeAlimentosService.actualizar(id, bancoActualizado);
        return banco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bancoDeAlimentosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginadas")
    public ResponseEntity<Page<BancoDeAlimentos>> obtenerBancosPaginados(
          @PageableDefault(page = 0, size = 9) Pageable pageable) {  
    
        Page<BancoDeAlimentos> bancos = bancoDeAlimentosService.obtenerBancosPaginados(pageable);
        return ResponseEntity.ok(bancos);
    }
}
