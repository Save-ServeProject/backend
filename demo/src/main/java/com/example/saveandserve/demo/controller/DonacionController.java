// package com.example.saveandserve.demo.controller;

// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.saveandserve.demo.entity.Donacion;
// import com.example.saveandserve.demo.service.DonacionService;

// @RestController
// @RequestMapping("/donaciones")
// public class DonacionController {

//     private static final Logger logger = LoggerFactory.getLogger(DonacionController.class);

//     @Autowired
//     private DonacionService donacionService;

//     @GetMapping
//     public ResponseEntity<List<Donacion>> obtenerTodas() {
//         List<Donacion> donaciones = donacionService.obtenerTodas();
//         return donaciones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(donaciones);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Donacion> obtenerPorId(@PathVariable Long id) {
//         Optional<Donacion> donacion = donacionService.obtenerPorId(id);
//         return donacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public ResponseEntity<Donacion> registrar(@RequestBody Donacion donacion) {
//         Donacion nuevaDonacion = donacionService.registrar(donacion);
//         return ResponseEntity.ok(nuevaDonacion);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Donacion> actualizar(@PathVariable Long id, @RequestBody Donacion donacionActualizada) {
//         Optional<Donacion> donacion = donacionService.actualizar(id, donacionActualizada);
//         return donacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> eliminar(@PathVariable Long id) {
//         donacionService.eliminar(id);
//         return ResponseEntity.noContent().build();
//     }

// //     @GetMapping("/empresa/{empresaId}")
// // public ResponseEntity<List<Donacion>> obtenerDonacionesPorEmpresa(@PathVariable Long empresaId) {
// //     List<Donacion> donaciones = donacionService.obtenerDonacionesPorEmpresa(empresaId);
// //     return donaciones.isEmpty() 
// //         ? ResponseEntity.noContent().build() 
// //         : ResponseEntity.ok(donaciones);
// // }

// // @GetMapping("/empresa/{empresaId}")
// // public ResponseEntity<List<Donacion>> obtenerDonacionesPorEmpresa(@PathVariable Long empresaId) {
// //     try {
// //         List<Donacion> donaciones = donacionService.obtenerDonacionesPorEmpresa(empresaId);
// //         return ResponseEntity.ok()
// //             .contentType(MediaType.APPLICATION_JSON)
// //             .body(donaciones);
// //     } catch (Exception e) {
// //         logger.error("Error al obtener donaciones para empresa {}: ", empresaId, e);
// //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
// //             .body(Collections.emptyList());
// //     }
// // }

//    @GetMapping("/empresa/{empresaId}")
//     public ResponseEntity<List<Donacion>> obtenerDonacionesPorEmpresa(@PathVariable Long empresaId) {
//         try {
//             log.info("Solicitando donaciones para empresa ID: {}", empresaId);
//             List<Donacion> donaciones = donacionService.obtenerDonacionesPorEmpresa(empresaId);
//             log.info("Encontradas {} donaciones para empresa ID: {}", donaciones.size(), empresaId);
//             return ResponseEntity.ok(donaciones);
//         } catch (Exception e) {
//             log.error("Error al obtener donaciones para empresa {}: ", empresaId, e);
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                 .body(Collections.emptyList());
//         }
//     }
// }


package com.example.saveandserve.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.saveandserve.demo.entity.Donacion;
import com.example.saveandserve.demo.service.DonacionService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/donaciones")
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    @GetMapping
    public ResponseEntity<List<Donacion>> obtenerTodas() {
        try {
            List<Donacion> donaciones = donacionService.obtenerTodas();
            inicializarColecciones(donaciones);
            return donaciones.isEmpty() ? 
                ResponseEntity.noContent().build() : 
                ResponseEntity.ok(donaciones);
        } catch (Exception e) {
            log.error("Error al obtener todas las donaciones", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.emptyList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donacion> obtenerPorId(@PathVariable Long id) {
        try {
            Optional<Donacion> donacion = donacionService.obtenerPorId(id);
            if (donacion.isPresent()) {
                inicializarColecciones(Collections.singletonList(donacion.get()));
                return ResponseEntity.ok(donacion.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error al obtener donación con ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Donacion> registrar(@RequestBody Donacion donacion) {
        try {
            Donacion nuevaDonacion = donacionService.registrar(donacion);
            inicializarColecciones(Collections.singletonList(nuevaDonacion));
            return ResponseEntity.ok(nuevaDonacion);
        } catch (Exception e) {
            log.error("Error al registrar donación", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donacion> actualizar(@PathVariable Long id, @RequestBody Donacion donacionActualizada) {
        try {
            Optional<Donacion> donacion = donacionService.actualizar(id, donacionActualizada);
            if (donacion.isPresent()) {
                inicializarColecciones(Collections.singletonList(donacion.get()));
                return ResponseEntity.ok(donacion.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error al actualizar donación con ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            donacionService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error al eliminar donación con ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Donacion>> obtenerDonacionesPorEmpresa(@PathVariable Long empresaId) {
        try {
            log.info("Solicitando donaciones para empresa ID: {}", empresaId);
            List<Donacion> donaciones = donacionService.obtenerDonacionesPorEmpresa(empresaId);
            inicializarColecciones(donaciones);
            log.info("Encontradas {} donaciones para empresa ID: {}", donaciones.size(), empresaId);
            return ResponseEntity.ok(donaciones);
        } catch (Exception e) {
            log.error("Error al obtener donaciones para empresa {}: ", empresaId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.emptyList());
        }
    }

    @JsonIgnore
    private void inicializarColecciones(List<Donacion> donaciones) {
        donaciones.forEach(donacion -> {
            donacion.getLineasProducto().size(); // Forzar inicialización
            donacion.getLineasProducto().forEach(linea -> {
                if (linea.getProducto() != null) {
                    linea.getProducto().getAlergenos().size(); // Forzar inicialización de alérgenos
                }
            });
        });
    }
}