package com.Utp.DesarrolloWeb.repository;

import com.Utp.DesarrolloWeb.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p WHERE p.total > :monto")
    List<Pedido> buscarPorMontoMayor(@Param("monto") double monto);
}
