package com.cookingclub.mycookbook.dto.ReceitaDTO;

import java.util.Date;

import com.cookingclub.mycookbook.model.Usuario;

import lombok.Data;

@Data
public class ReceitaDTORequest {
    private String nome;
    private String ingredientes;
    private String preparo;
    private String tipoVersao;
    private Long receitaOriginal;
    private Date dataHora;
    private Usuario usuario;
}