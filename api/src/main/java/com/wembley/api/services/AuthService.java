package com.wembley.api.services;

import com.wembley.api.config.JwtService;
import com.wembley.api.dto.AuthRequest;
import com.wembley.api.dto.AuthResponse;
import com.wembley.api.dto.RegisterRequest;
import com.wembley.api.models.Rol;
import com.wembley.api.models.Usuario;
import com.wembley.api.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registrar(RegisterRequest request) {
        // 1. Creamos el usuario con rol de ADMIN (porque eres tú)
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // ¡Contraseña encriptada!
                .rol(Rol.ADMIN)
                .build();

        // 2. Lo guardamos en PostgreSQL
        usuarioRepository.save(usuario);

        // 3. Generamos su llave maestra
        String jwtToken = jwtService.generateToken(usuario);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse login(AuthRequest request) {
        // 1. Spring Security verifica si el email y contraseña coinciden
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 2. Si todo es correcto, buscamos al usuario
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();

        // 3. Le damos un nuevo token
        String jwtToken = jwtService.generateToken(usuario);
        return AuthResponse.builder().token(jwtToken).build();
    }
}