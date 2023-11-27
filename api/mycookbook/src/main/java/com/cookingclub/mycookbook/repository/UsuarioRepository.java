package com.cookingclub.mycookbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cookingclub.mycookbook.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
}