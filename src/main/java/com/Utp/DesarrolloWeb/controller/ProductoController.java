package com.Utp.DesarrolloWeb.controller;

import com.Utp.DesarrolloWeb.model.Producto;
import com.Utp.DesarrolloWeb.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService servicio;

    public ProductoController(ProductoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Producto> listar() {
        return servicio.listar();
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto p) {
        return servicio.guardar(p);
    }

    @GetMapping("/{id}")
    public Producto buscar(@PathVariable Long id) {
        return servicio.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        return servicio.eliminar(id);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto p) {
        return servicio.actualizar(id, p);
    }
}