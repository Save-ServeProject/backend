package com.example.saveandserve.demo.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "articulos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;
    
    @Column(nullable = false, length = 200)
    private String titulo;
    
    @Column(length = 300)
    private String subtitulo;
    
    @Column(length = 500)
    private String imagen;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenido;
}
