package com.Utp.DesarrolloWeb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false, length = 100)
    private String clienteNombre;

    @Column(nullable = false, length = 20)
    private String clienteDni;

    @Column(nullable = false, length = 30)
    private String metodoPago; // EFECTIVO, TARJETA, YAPE, PLIN

    @Column(nullable = false)
    private Double totalVenta;

    @Column(nullable = false, length = 20)
    private String estado; // COMPLETADA, ANULADA, PENDIENTE

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DetalleVenta> detalles;

    // ── Constructors ──────────────────────────────────
    public Venta() {
        this.fecha  = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    // ── Getters & Setters ─────────────────────────────
    public Long getId()                          { return id; }
    public void setId(Long id)                   { this.id = id; }

    public LocalDateTime getFecha()              { return fecha; }
    public void setFecha(LocalDateTime fecha)    { this.fecha = fecha; }

    public String getClienteNombre()             { return clienteNombre; }
    public void setClienteNombre(String n)       { this.clienteNombre = n; }

    public String getClienteDni()                { return clienteDni; }
    public void setClienteDni(String dni)        { this.clienteDni = dni; }

    public String getMetodoPago()                { return metodoPago; }
    public void setMetodoPago(String m)          { this.metodoPago = m; }

    public Double getTotalVenta()                { return totalVenta; }
    public void setTotalVenta(Double t)          { this.totalVenta = t; }

    public String getEstado()                    { return estado; }
    public void setEstado(String estado)         { this.estado = estado; }

    public List<DetalleVenta> getDetalles()      { return detalles; }
    public void setDetalles(List<DetalleVenta> d){ this.detalles = d; }
}