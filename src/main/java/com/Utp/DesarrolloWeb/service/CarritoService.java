package com.Utp.DesarrolloWeb.service;

import com.Utp.DesarrolloWeb.model.Carrito;
import com.Utp.DesarrolloWeb.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService {

    private List<Carrito> carrito = new ArrayList<>();

    private final ProductoService productoService;

    public CarritoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public String agregar(int idProducto, int cantidad) {
        Producto producto = productoService.buscarPorId(idProducto);
        if (producto == null) {
            return "Producto no encontrado";
        }
        for (Carrito item : carrito) {
            if (item.getProducto().getId() == idProducto) {
                item.setCantidad(item.getCantidad() + cantidad);
                return "Cantidad actualizada";
            }
        }
        carrito.add(new Carrito(producto, cantidad));
        return "Producto agregado";
    }

    public List<Carrito> listar() {
        return carrito;
    }

    public String eliminar(int idProducto) {
        boolean eliminado = carrito.removeIf(item -> item.getProducto().getId() == idProducto);
        if (eliminado) {
            return "Producto eliminado";
        } else {
            return "Producto no encontrado en el carrito";
        }
    }

    public double total() {
        return carrito.stream()
                .mapToDouble(Carrito::getSubtotal)
                .sum();
    }

    public String vaciar() {
        carrito.clear();
        return "Carrito vacío";
    }
}