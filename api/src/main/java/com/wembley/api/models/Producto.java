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
    private String descripcion; // ¡Aquí está el nuevo campo!

    private String categoria; // Ej: "Botines", "Indumentaria", "Accesorios"

    private String marca; // Ej: "Nike", "Adidas"

    @Column(nullable = false)
    private BigDecimal precio;

    private Integer stock;

    private String talle;

    private String imagenUrl;
}