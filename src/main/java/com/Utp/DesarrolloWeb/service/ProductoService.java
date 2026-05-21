package com.Utp.DesarrolloWeb.service;

import com.Utp.DesarrolloWeb.model.Producto;
import com.Utp.DesarrolloWeb.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto guardar(Producto p) {
        return repository.save(p);
    }

    public Producto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public String eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Producto eliminado";
        }
        return "Producto no encontrado";
    }
    
    @Transactional
    public Producto actualizar(Long id, Producto nuevo) {
        Producto p = buscarPorId(id);
        if (p != null) {
            p.setNombre(nuevo.getNombre());
            p.setPrecio(nuevo.getPrecio());
            p.setStock(nuevo.getStock());
            p.setCategoria(nuevo.getCategoria());
            return repository.save(p);
        }
        return null;
    }
}