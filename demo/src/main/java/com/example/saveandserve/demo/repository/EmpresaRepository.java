package com.example.saveandserve.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.saveandserve.demo.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
