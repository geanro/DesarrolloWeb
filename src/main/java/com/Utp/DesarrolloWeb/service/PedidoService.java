package com.Utp.DesarrolloWeb.service;

import com.Utp.DesarrolloWeb.model.Carrito;
import com.Utp.DesarrolloWeb.model.DetallePedido;
import com.Utp.DesarrolloWeb.model.Pedido;
import com.Utp.DesarrolloWeb.model.Producto;
import com.Utp.DesarrolloWeb.repository.CarritoRepository;
import com.Utp.DesarrolloWeb.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Transactional
    public Pedido crearPedidoDesdeCarrito() {
        List<Carrito> itemsCarrito = carritoRepository.findAll();
        if (itemsCarrito.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }
        Pedido pedido = new Pedido();
        double total = 0;
        for (Carrito item : itemsCarrito) {
            Producto producto = item.getProducto();
            if (producto.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            double subtotal = producto.getPrecio() * item.getCantidad();
            detalle.setSubtotal(subtotal);
            pedido.getDetalles().add(detalle);
            total += subtotal;
            producto.setStock(producto.getStock() - item.getCantidad());
        }
        pedido.setTotal(total);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        carritoRepository.deleteAll();
        return pedidoGuardado;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedido(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }
    public List<Pedido> buscarPorMontoMayor(double monto){
        return pedidoRepository.buscarPorMontoMayor(monto);
    }
}