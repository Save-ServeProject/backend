package com.example.saveandserve.demo.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alergenos") 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alergenos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @ManyToMany(mappedBy = "alergenos")
    private Set<Producto> productos;
}
