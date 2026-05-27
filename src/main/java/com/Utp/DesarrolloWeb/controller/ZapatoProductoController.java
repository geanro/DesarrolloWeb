package com.Utp.DesarrolloWeb.controller;

import com.Utp.DesarrolloWeb.model.ZapatoProducto;
import com.Utp.DesarrolloWeb.service.ZapatoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zapatos")
@CrossOrigin(origins = "*")
public class ZapatoProductoController {

    @Autowired
    private ZapatoProductoService zapatoProductoService;

    // ── POST /api/zapatos ────────────────────────────────
    // Crear nuevo producto
    @PostMapping
    public ResponseEntity<ZapatoProducto> crear(@RequestBody ZapatoProducto producto) {
        ZapatoProducto nuevo = zapatoProductoService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // ── GET /api/zapatos ─────────────────────────────────
    // Listar todos los productos
    @GetMapping
    public ResponseEntity<List<ZapatoProducto>> listarTodos() {
        return ResponseEntity.ok(zapatoProductoService.listarTodos());
    }

    // ── GET /api/zapatos/{id} ────────────────────────────
    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ZapatoProducto> obtenerPorId(@PathVariable Long id) {
        return zapatoProductoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ── GET /api/zapatos/marca/{marca} ───────────────────
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<ZapatoProducto>> obtenerPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(zapatoProductoService.buscarPorMarca(marca));
    }

    // ── GET /api/zapatos/categoria/{categoria} ───────────
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ZapatoProducto>> obtenerPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(zapatoProductoService.buscarPorCategoria(categoria));
    }

    // ── GET /api/zapatos/talla/{talla} ───────────────────
    @GetMapping("/talla/{talla}")
    public ResponseEntity<List<ZapatoProducto>> obtenerPorTalla(@PathVariable String talla) {
        return ResponseEntity.ok(zapatoProductoService.buscarPorTalla(talla));
    }

    // ── GET /api/zapatos/buscar?nombre=... ───────────────
    @GetMapping("/buscar")
    public ResponseEntity<List<ZapatoProducto>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(zapatoProductoService.buscarPorNombre(nombre));
    }

    // ── GET /api/zapatos/disponibles ─────────────────────
    @GetMapping("/disponibles")
    public ResponseEntity<List<ZapatoProducto>> listarDisponibles() {
        return ResponseEntity.ok(zapatoProductoService.listarConStock());
    }

    // ── PUT /api/zapatos/{id} ────────────────────────────
    // Actualizar producto completo
    @PutMapping("/{id}")
    public ResponseEntity<ZapatoProducto> actualizar(@PathVariable Long id,
                                                     @RequestBody ZapatoProducto producto) {
        try {
            return ResponseEntity.ok(zapatoProductoService.actualizar(id, producto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── DELETE /api/zapatos/{id} ─────────────────────────
    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            zapatoProductoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}