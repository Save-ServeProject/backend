package com.example.saveandserve.demo.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.saveandserve.demo.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByEmail(String email);
    Page<Empresa> findAll(Pageable pageable);
}
