package com.example.saveandserve.demo.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_transporte") 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoTransporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String tipo; 

    @OneToMany(mappedBy = "tipoTransporte", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos;

    @ManyToMany(mappedBy = "tipoTransporte")
    private Set<Transporte> transportes;
}

