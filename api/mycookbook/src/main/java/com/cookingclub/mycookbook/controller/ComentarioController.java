package com.cookingclub.mycookbook.controller;

import com.cookingclub.mycookbook.dto.ComentarioDTO.ComentarioDTORequest;
import com.cookingclub.mycookbook.dto.ComentarioDTO.ComentarioDTOResponse;
import com.cookingclub.mycookbook.model.Comentario;
import com.cookingclub.mycookbook.repository.ComentarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @ResponseStatus(HttpStatus.CREATED)
    public String adicionarComentario(@RequestBody ComentarioDTORequest comentarioDTORequest) {
        Comentario comentario = modelMapper.map(comentarioDTORequest, Comentario.class);
        comentarioRepository.save(comentario);
        return "Comentário adicionado com sucesso";
    }

    @GetMapping("/por-usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    public List<ComentarioDTOResponse> listarComentariosPorUsuario(@PathVariable Long idUsuario) {
        List<Comentario> comentarios = comentarioRepository.findByUsuario_IdUsuario(idUsuario);
        return comentarios.stream()
                .map(comentario -> modelMapper.map(comentario, ComentarioDTOResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/por-receita/{idReceita}")
    @ResponseStatus(HttpStatus.OK)
    public List<ComentarioDTOResponse> listarComentariosPorReceita(@PathVariable Long idReceita) {
        List<Comentario> comentarios = comentarioRepository.findByReceita_IdReceita(idReceita);
        return comentarios.stream()
                .map(comentario -> modelMapper.map(comentario, ComentarioDTOResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/por-id/{idComentario}")
    @ResponseStatus(HttpStatus.OK)
    public ComentarioDTOResponse buscarComentarioPorId(@PathVariable Long idComentario) {
        Comentario comentario = comentarioRepository.findById(idComentario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Comentário não encontrado com o ID: " + idComentario));

        return modelMapper.map(comentario, ComentarioDTOResponse.class);
    }

    @PutMapping("/editar-comentario")
    @ResponseStatus(HttpStatus.OK)
    public String editarComentario(@RequestBody ComentarioDTORequest comentarioDTORequest) {
        Comentario comentario = modelMapper.map(comentarioDTORequest, Comentario.class);
        comentarioRepository.save(comentario);
        return "Comentário editado com sucesso";
    }

    @DeleteMapping("/excluir-comentario/{idComentario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String excluirComentario(@PathVariable Long idComentario) {
        comentarioRepository.deleteById(idComentario);
        return "Comentário excluído com sucesso";
    }
}