package com.cookingclub.mycookbook.controller;

import com.cookingclub.mycookbook.model.Comentario;
import com.cookingclub.mycookbook.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @PostMapping("/adicionar")
    public String adicionarComentario(@RequestBody Comentario comentario) {
        comentarioRepository.save(comentario);
        return "Comentário adicionado com sucesso";
    }

    @GetMapping("/porUsuario/{idUsuario}")
    public List<Comentario> listarComentariosPorUsuario(@PathVariable Long idUsuario) {
        return comentarioRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @GetMapping("/porReceita/{idReceita}")
    public List<Comentario> listarComentariosPorReceita(@PathVariable Long idReceita) {
        return comentarioRepository.findByReceita_IdReceita(idReceita);
    }

    @PutMapping("/editar")
    public String editarComentario(@RequestBody Comentario comentario) {
        comentarioRepository.save(comentario);
        return "Comentário editado com sucesso";
    }

    @DeleteMapping("/excluir/{idComentario}")
    public String excluirComentario(@PathVariable Long idComentario) {
        comentarioRepository.deleteById(idComentario);
        return "Comentário excluído com sucesso";
    }
}