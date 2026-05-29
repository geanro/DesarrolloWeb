package com.Utp.DesarrolloWeb.repository;

import com.Utp.DesarrolloWeb.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Ventas por estado
    List<Venta> findByEstado(String estado);

    // Ventas por DNI del cliente
    List<Venta> findByClienteDni(String clienteDni);

    // Ventas en un rango de fechas
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    // Ventas por método de pago
    List<Venta> findByMetodoPago(String metodoPago);

    // Total de ventas del día
    @Query("SELECT COALESCE(SUM(v.totalVenta), 0) FROM Venta v WHERE v.estado = 'COMPLETADA' AND v.fecha >= :inicio AND v.fecha <= :fin")
    Double totalVentasPorFecha(LocalDateTime inicio, LocalDateTime fin);
}