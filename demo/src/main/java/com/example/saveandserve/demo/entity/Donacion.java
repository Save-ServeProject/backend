package com.example.saveandserve.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "donacion") 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDonacion;

    @Column(nullable = false)
    private BigDecimal totalDonacion;

    @Column(nullable = false)
    private LocalDate fechaEntrega;

    public static enum EstadoEnvio {
        ENTREGADO,
        ENVIADO,
        PENDIENTE
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEnvio estadoEnvio;

    @OneToMany(mappedBy = "donacionPadre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donacion> subdonaciones; 
    
    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;
}
