package com.cookingclub.mycookbook.dto.UsuarioDTO;

import java.util.List;

import com.cookingclub.mycookbook.dto.ComentarioDTO.ComentarioDTOResponse;
import com.cookingclub.mycookbook.dto.ReceitaDTO.ReceitaDTOResponse;

import lombok.Data;

@Data
public class UsuarioDTOResponse {
    private Long idUsuario;
    private String nome;
    private String nomeUsuario;
    private String email;
    private String senha;
    private List<ReceitaDTOResponse> receitas;
    private List<ComentarioDTOResponse> comentarios;
}