package com.Utp.DesarrolloWeb.repository;

import com.Utp.DesarrolloWeb.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}