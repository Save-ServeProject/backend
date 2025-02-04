package com.example.saveandserve.demo.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "empresas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(length = 200)
    private String direccion;

    @Column(length = 15)
    private String telefono;

    @Column(nullable = false )
    private String cif;

    @Column(nullable = false)
    private String contrasenia;

    private String tipo;
    private String ciudad;

    public static enum Suscripcion {
        BASICA,
        ESTANDAR, 
        PREMIUM
    }

    @Enumerated(EnumType.STRING)
    private Suscripcion suscripcion;    


    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @JsonBackReference
    @JsonIgnore
    private List<Donacion> donaciones;
}
