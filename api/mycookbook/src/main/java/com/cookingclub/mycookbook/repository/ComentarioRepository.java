package com.cookingclub.mycookbook.repository;

import com.cookingclub.mycookbook.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByUsuario_IdUsuario(Long idUsuario);
    List<Comentario> findByReceita_IdReceita(Long idReceita);
}