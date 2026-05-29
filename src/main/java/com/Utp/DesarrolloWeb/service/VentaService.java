package com.Utp.DesarrolloWeb.service;

import com.Utp.DesarrolloWeb.model.DetalleVenta;
import com.Utp.DesarrolloWeb.model.Venta;
import com.Utp.DesarrolloWeb.model.ZapatoProducto;
import com.Utp.DesarrolloWeb.repository.VentaRepository;
import com.Utp.DesarrolloWeb.repository.ZapatoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ZapatoProductoRepository zapatoRepository;

    // ── CREATE ────────────────────────────────────────────
    @Transactional
    public Venta registrarVenta(Venta venta) {
        double total = 0.0;

        for (DetalleVenta detalle : venta.getDetalles()) {
            // Buscar zapato y validar stock
            ZapatoProducto zapato = zapatoRepository.findById(detalle.getZapato().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Zapato no encontrado con ID: " + detalle.getZapato().getId()));

            if (zapato.getStock() < detalle.getCantidad()) {
                throw new RuntimeException(
                        "Stock insuficiente para: " + zapato.getNombre() +
                        " | Stock disponible: " + zapato.getStock());
            }

            // Descontar stock
            zapato.setStock(zapato.getStock() - detalle.getCantidad());
            zapatoRepository.save(zapato);

            // Calcular subtotal
            detalle.setPrecioUnitario(zapato.getPrecio());
            detalle.setSubtotal(zapato.getPrecio() * detalle.getCantidad());
            detalle.setVenta(venta);

            total += detalle.getSubtotal();
        }

        venta.setTotalVenta(total);
        venta.setFecha(LocalDateTime.now());
        venta.setEstado("COMPLETADA");

        return ventaRepository.save(venta);
    }

    // ── READ ──────────────────────────────────────────────
    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> buscarPorCliente(String dni) {
        return ventaRepository.findByClienteDni(dni);
    }

    public List<Venta> buscarPorEstado(String estado) {
        return ventaRepository.findByEstado(estado);
    }

    public List<Venta> buscarPorMetodoPago(String metodoPago) {
        return ventaRepository.findByMetodoPago(metodoPago);
    }

    public Double totalVentasHoy() {
        LocalDateTime inicio = LocalDate.now().atStartOfDay();
        LocalDateTime fin    = inicio.plusDays(1).minusSeconds(1);
        return ventaRepository.totalVentasPorFecha(inicio, fin);
    }

    // ── ANULAR ────────────────────────────────────────────
    @Transactional
    public Venta anularVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));

        if ("ANULADA".equals(venta.getEstado())) {
            throw new RuntimeException("La venta ya está anulada.");
        }

        // Devolver stock
        for (DetalleVenta detalle : venta.getDetalles()) {
            ZapatoProducto zapato = detalle.getZapato();
            zapato.setStock(zapato.getStock() + detalle.getCantidad());
            zapatoRepository.save(zapato);
        }

        venta.setEstado("ANULADA");
        return ventaRepository.save(venta);
    }
}