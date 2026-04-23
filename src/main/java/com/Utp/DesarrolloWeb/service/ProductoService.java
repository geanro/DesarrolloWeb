package com.Utp.DesarrolloWeb.service;
import com.Utp.DesarrolloWeb.model.Producto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private List<Producto> lista = new ArrayList<>();
    private int cont = 11;

    public ProductoService() {

    lista.add(new Producto(1, "Nike Air Max", 250, 10, "Deportivo"));
    lista.add(new Producto(2, "Adidas Ultraboost", 300, 8, "Deportivo"));
    lista.add(new Producto(3, "Puma Runner", 180, 12, "Casual"));
    lista.add(new Producto(4, "Reebok Classic", 200, 6, "Casual"));
    lista.add(new Producto(5, "Converse Chuck Taylor", 150, 15, "Casual"));
    lista.add(new Producto(6, "Vans Old Skool", 170, 9, "Casual"));
    lista.add(new Producto(7, "Nike Mercurial", 320, 5, "Fútbol"));
    lista.add(new Producto(8, "Adidas Predator", 310, 4, "Fútbol"));
    lista.add(new Producto(9, "Zapato Formal Cuero", 220, 7, "Formal"));
    lista.add(new Producto(10, "Botines Elegantes", 260, 6, "Formal"));
    }

    public List<Producto> listar() {
        return lista;
    }

    public Producto guardar(Producto p) {
        p.setId(cont++);
        lista.add(p);
        return p;
    }

    public Producto buscarPorId(int id) {
        return lista.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public String eliminar(int id) {
        lista.removeIf(p -> p.getId() == id);
        return "Producto eliminado";
    }
    
    public Producto actualizar(int id, Producto nuevo) {
    Producto p = buscarPorId(id);
    if (p != null) {
        p.setNombre(nuevo.getNombre());
        p.setPrecio(nuevo.getPrecio());
        p.setStock(nuevo.getStock());
        p.setCategoria(nuevo.getCategoria());
    }
    return p;
}

    
}