package com.example.saveandserve.demo.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos") 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String idProducto;

    @Column(nullable = false)
    private String precio; 

    public enum TipoProducto {
        SECO,
        REFRIGERADO,
        CONGELADO
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProducto tipoProducto; 

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LineaProducto> lineaProducto;

    @ManyToMany
    @JoinTable(
        name = "producto_alergeno",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "alergeno_id")
    )
    private Set<Alergenos> alergenos;

    @ManyToOne
    @JoinColumn(name = "tipo_transporte_id", nullable = false)
    private TipoTransporte tipoTransporte;
    
}
