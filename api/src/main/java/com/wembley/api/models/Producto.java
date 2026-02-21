package com.wembley.api.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String categoria;

    private String marca;

    @Column(nullable = false)
    private BigDecimal precio; // Volvemos a tu precio original de lista

    private Integer stock;

    private String talle;

    private String imagenUrl;
}