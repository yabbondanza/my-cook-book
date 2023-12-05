package com.cookingclub.mycookbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cookingclub.mycookbook.model.Receita;
import com.cookingclub.mycookbook.model.Usuario;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByUsuario(Usuario usuario);
    List<Receita> findByUsuariosQueSalvaram(Usuario usuario);
}
