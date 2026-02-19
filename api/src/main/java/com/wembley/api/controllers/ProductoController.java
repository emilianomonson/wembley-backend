package com.wembley.api.controllers;

import com.wembley.api.models.Producto;
import com.wembley.api.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor // Lombok inyecta el repositorio automáticamente
public class ProductoController {

    private final ProductoRepository productoRepository;

    // 1. Obtener todos los productos (Para mostrar en tu catálogo web)
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        return ResponseEntity.ok(productoRepository.findAll());
    }

    // 2. Crear un nuevo producto (Para cuando subas botines desde tu panel de admin)
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto productoGuardado = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    // 3. Obtener productos por categoría (Ej: Solo mostrar los de "Fútbol 11")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> obtenerPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(productoRepository.findByCategoria(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        // En lugar de productoService, usamos directamente tu productoRepository
        productoRepository.deleteById(id);
        return ResponseEntity.ok().body("{\"mensaje\": \"Producto eliminado correctamente\"}");
    }
}