package com.wembley.api.repositories;

import com.wembley.api.models.Producto;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {


    List<Producto> findByCategoria(String categoria);
    // Solo con extender JpaRepository, ya tienes métodos para guardar, borrar y buscar productos.
}