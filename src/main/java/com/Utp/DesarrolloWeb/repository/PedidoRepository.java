package com.Utp.DesarrolloWeb.repository;

import com.Utp.DesarrolloWeb.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
