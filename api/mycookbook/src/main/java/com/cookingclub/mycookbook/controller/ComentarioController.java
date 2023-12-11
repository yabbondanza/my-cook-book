package com.cookingclub.mycookbook.controller;

import com.cookingclub.mycookbook.dto.ComentarioDTO.ComentarioDTORequest;
import com.cookingclub.mycookbook.dto.ComentarioDTO.ComentarioDTOResponse;
import com.cookingclub.mycookbook.model.Comentario;
import com.cookingclub.mycookbook.repository.ComentarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/adicionar-comentario")
    public String adicionarComentario(@RequestBody ComentarioDTORequest comentarioDTORequest) {
        Comentario comentario = modelMapper.map(comentarioDTORequest, Comentario.class);
        comentarioRepository.save(comentario);
        return "Comentário adicionado com sucesso";
    }

    @GetMapping("/por-usuario/{idUsuario}")
    public List<ComentarioDTOResponse> listarComentariosPorUsuario(@PathVariable Long idUsuario) {
        List<Comentario> comentarios = comentarioRepository.findByUsuario_IdUsuario(idUsuario);
        return comentarios.stream()
                .map(comentario -> modelMapper.map(comentario, ComentarioDTOResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/por-receita/{idReceita}")
    public List<ComentarioDTOResponse> listarComentariosPorReceita(@PathVariable Long idReceita) {
        List<Comentario> comentarios = comentarioRepository.findByReceita_IdReceita(idReceita);
        return comentarios.stream()
                .map(comentario -> modelMapper.map(comentario, ComentarioDTOResponse.class))
                .collect(Collectors.toList());
    }


    @PutMapping("/editar-comentario")
    public String editarComentario(@RequestBody ComentarioDTORequest comentarioDTORequest) {
        Comentario comentario = modelMapper.map(comentarioDTORequest, Comentario.class);
        comentarioRepository.save(comentario);
        return "Comentário editado com sucesso";
    }

    @DeleteMapping("/excluir-comentario/{idComentario}")
    public String excluirComentario(@PathVariable Long idComentario) {
        comentarioRepository.deleteById(idComentario);
        return "Comentário excluído com sucesso";
    }
}