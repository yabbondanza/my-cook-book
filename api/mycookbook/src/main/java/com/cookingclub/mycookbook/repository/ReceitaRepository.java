package com.cookingclub.mycookbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cookingclub.mycookbook.model.Receita;
import com.cookingclub.mycookbook.model.Usuario;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    Optional<Receita> findById(Long idReceita);
    List<Receita> findByUsuario(Usuario usuario);
}
