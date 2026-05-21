package com.Utp.DesarrolloWeb.repository;

import com.Utp.DesarrolloWeb.model.ZapatoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZapatoProductoRepository extends JpaRepository<ZapatoProducto, Long> {
    List<ZapatoProducto> findByMarcaIgnoreCase(String marca);
    List<ZapatoProducto> findByCategoriaIgnoreCase(String categoria);
    List<ZapatoProducto> findByTalla(String talla);
    List<ZapatoProducto> findByStockGreaterThan(Integer minStock);
    List<ZapatoProducto> findByNombreContainingIgnoreCase(String nombre);
}