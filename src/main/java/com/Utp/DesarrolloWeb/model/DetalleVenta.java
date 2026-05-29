package com.Utp.DesarrolloWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonIgnore
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "zapato_id", nullable = false)
    private ZapatoProducto zapato;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal;

    // ── Constructors ──────────────────────────────────
    public DetalleVenta() {}

    // ── Getters & Setters ─────────────────────────────
    public Long getId()                          { return id; }
    public void setId(Long id)                   { this.id = id; }

    public Venta getVenta()                      { return venta; }
    public void setVenta(Venta venta)            { this.venta = venta; }

    public ZapatoProducto getZapato()            { return zapato; }
    public void setZapato(ZapatoProducto z)      { this.zapato = z; }

    public Integer getCantidad()                 { return cantidad; }
    public void setCantidad(Integer cantidad)    { this.cantidad = cantidad; }

    public Double getPrecioUnitario()            { return precioUnitario; }
    public void setPrecioUnitario(Double p)      { this.precioUnitario = p; }

    public Double getSubtotal()                  { return subtotal; }
    public void setSubtotal(Double subtotal)     { this.subtotal = subtotal; }
}