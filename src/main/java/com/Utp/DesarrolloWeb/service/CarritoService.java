package com.Utp.DesarrolloWeb.service;
import com.Utp.DesarrolloWeb.model.Carrito;
import com.Utp.DesarrolloWeb.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService {

    private List<Carrito> carrito = new ArrayList<>();

    public String agregar(Producto producto, int cantidad) {

        for (Carrito item : carrito) {
            if (item.getProducto().getId() == producto.getId()) {
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
        carrito.removeIf(item -> item.getProducto().getId() == idProducto);
        return "Producto eliminado";
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