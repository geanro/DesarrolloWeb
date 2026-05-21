package com.Utp.DesarrolloWeb.service;

import com.Utp.DesarrolloWeb.model.ZapatoProducto;
import com.Utp.DesarrolloWeb.repository.ZapatoProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ZapatoProductoService {

    private final ZapatoProductoRepository repository;

    public ZapatoProductoService(ZapatoProductoRepository repository) {
        this.repository = repository;
    }

    public ZapatoProducto guardar(ZapatoProducto producto) {
        return repository.save(producto);
    }

    public List<ZapatoProducto> listarTodos() {
        return repository.findAll();
    }

    public Optional<ZapatoProducto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<ZapatoProducto> buscarPorMarca(String marca) {
        return repository.findByMarcaIgnoreCase(marca);
    }

    public List<ZapatoProducto> buscarPorCategoria(String categoria) {
        return repository.findByCategoriaIgnoreCase(categoria);
    }

    public List<ZapatoProducto> buscarPorTalla(String talla) {
        return repository.findByTalla(talla);
    }

    public List<ZapatoProducto> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<ZapatoProducto> listarConStock() {
        return repository.findByStockGreaterThan(0);
    }

    @Transactional
    public ZapatoProducto actualizar(Long id, ZapatoProducto nuevo) {
        return repository.findById(id).map(p -> {
            p.setNombre(nuevo.getNombre());
            p.setMarca(nuevo.getMarca());
            p.setModelo(nuevo.getModelo());
            p.setTalla(nuevo.getTalla());
            p.setColor(nuevo.getColor());
            p.setCategoria(nuevo.getCategoria());
            p.setPrecio(nuevo.getPrecio());
            p.setStock(nuevo.getStock());
            p.setDescripcion(nuevo.getDescripcion());
            p.setImagenUrl(nuevo.getImagenUrl());
            return repository.save(p);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        repository.deleteById(id);
    }
}