package com.example.saveandserve.demo.entity;

import java.math.BigDecimal;
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

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String idProducto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransporte tipoTransporte;

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
}
