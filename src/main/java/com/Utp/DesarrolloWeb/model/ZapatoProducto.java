package com.Utp.DesarrolloWeb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "zapato_producto")
public class ZapatoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false, length = 30)
    private String talla;

    @Column(nullable = false, length = 30)
    private String color;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 255)
    private String descripcion;

    @Column(length = 255)
    private String imagenUrl;

    public ZapatoProducto() {}

    public ZapatoProducto(String nombre, String marca, String modelo,
                          String talla, String color, String categoria,
                          Double precio, Integer stock,
                          String descripcion, String imagenUrl) {
        this.nombre     = nombre;
        this.marca      = marca;
        this.modelo     = modelo;
        this.talla      = talla;
        this.color      = color;
        this.categoria  = categoria;
        this.precio     = precio;
        this.stock      = stock;
        this.descripcion = descripcion;
        this.imagenUrl  = imagenUrl;
    }

    public Long getId()                    { return id; }
    public void setId(Long id)             { this.id = id; }
    public String getNombre()              { return nombre; }
    public void setNombre(String nombre)   { this.nombre = nombre; }
    public String getMarca()               { return marca; }
    public void setMarca(String marca)     { this.marca = marca; }
    public String getModelo()              { return modelo; }
    public void setModelo(String modelo)   { this.modelo = modelo; }
    public String getTalla()               { return talla; }
    public void setTalla(String talla)     { this.talla = talla; }
    public String getColor()               { return color; }
    public void setColor(String color)     { this.color = color; }
    public String getCategoria()           { return categoria; }
    public void setCategoria(String cat)   { this.categoria = cat; }
    public Double getPrecio()              { return precio; }
    public void setPrecio(Double precio)   { this.precio = precio; }
    public Integer getStock()              { return stock; }
    public void setStock(Integer stock)    { this.stock = stock; }
    public String getDescripcion()         { return descripcion; }
    public void setDescripcion(String d)   { this.descripcion = d; }
    public String getImagenUrl()           { return imagenUrl; }
    public void setImagenUrl(String url)   { this.imagenUrl = url; }
}