package com.cookingclub.mycookbook.dto.ComentarioDTO;

import java.util.Date;

import com.cookingclub.mycookbook.model.Receita;
import com.cookingclub.mycookbook.model.Usuario;

import lombok.Data;

@Data
public class ComentarioDTORequest {
    private String descricao;
    private Date dataHora;
    private Usuario usuario;
    private Receita receita;
}