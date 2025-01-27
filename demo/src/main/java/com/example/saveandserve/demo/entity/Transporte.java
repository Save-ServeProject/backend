package com.example.saveandserve.demo.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transporte") 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreTransporte;

    @ManyToMany
    @JoinTable(
        name = "transporte_tipo",
        joinColumns = @JoinColumn(name = "transporte_id"),
        inverseJoinColumns = @JoinColumn(name = "tipo_transporte_id")
    )
    private Set<TipoTransporte> tipoTransporte;


}
