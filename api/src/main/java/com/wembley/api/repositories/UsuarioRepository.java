package com.wembley.api.repositories;

import com.wembley.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // JPA creará el "SELECT * FROM usuarios WHERE email = ?" automáticamente
    Optional<Usuario> findByEmail(String email);

}