package com.example.saveandserve.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.saveandserve.demo.entity.Donacion;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {
    List<Donacion> findByEmpresaId(Long empresaId);
}
