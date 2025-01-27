package com.example.saveandserve.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.saveandserve.demo.entity.BancoDeAlimentos;

public interface BancoDeAlimentosRepository extends JpaRepository<BancoDeAlimentos, Long> {

}
