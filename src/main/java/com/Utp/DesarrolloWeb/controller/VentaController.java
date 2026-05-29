package com.Utp.DesarrolloWeb.controller;

import com.Utp.DesarrolloWeb.model.Venta;
import com.Utp.DesarrolloWeb.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // ── POST /api/ventas ─────────────────────────────────
    // Registrar nueva venta con sus detalles
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Venta venta) {
        try {
            Venta nueva = ventaService.registrarVenta(venta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ── GET /api/ventas ──────────────────────────────────
    // Listar todas las ventas
    @GetMapping
    public ResponseEntity<List<Venta>> listarTodas() {
        return ResponseEntity.ok(ventaService.listarTodas());
    }

    // ── GET /api/ventas/{id} ─────────────────────────────
    // Obtener venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable Long id) {
        return ventaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ── GET /api/ventas/cliente/{dni} ────────────────────
    // Historial de ventas por cliente
    @GetMapping("/cliente/{dni}")
    public ResponseEntity<List<Venta>> obtenerPorCliente(@PathVariable String dni) {
        return ResponseEntity.ok(ventaService.buscarPorCliente(dni));
    }

    // ── GET /api/ventas/estado/{estado} ─────────────────
    // Ventas por estado: COMPLETADA, ANULADA, PENDIENTE
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Venta>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(ventaService.buscarPorEstado(estado));
    }

    // ── GET /api/ventas/pago/{metodo} ────────────────────
    // Ventas por método de pago: EFECTIVO, TARJETA, YAPE, PLIN
    @GetMapping("/pago/{metodo}")
    public ResponseEntity<List<Venta>> obtenerPorMetodoPago(@PathVariable String metodo) {
        return ResponseEntity.ok(ventaService.buscarPorMetodoPago(metodo));
    }

    // ── GET /api/ventas/total-hoy ────────────────────────
    // Total de ventas del día (caja diaria)
    @GetMapping("/total-hoy")
    public ResponseEntity<Map<String, Double>> totalHoy() {
        Double total = ventaService.totalVentasHoy();
        return ResponseEntity.ok(Map.of("totalHoy", total));
    }

    // ── PATCH /api/ventas/{id}/anular ────────────────────
    // Anular venta y devolver stock automáticamente
    @PatchMapping("/{id}/anular")
    public ResponseEntity<?> anular(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ventaService.anularVenta(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}