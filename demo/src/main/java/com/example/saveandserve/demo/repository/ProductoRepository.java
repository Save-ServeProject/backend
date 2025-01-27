package com.example.saveandserve.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.saveandserve.demo.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
