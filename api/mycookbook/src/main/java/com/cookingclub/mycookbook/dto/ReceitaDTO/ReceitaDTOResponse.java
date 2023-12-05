package com.cookingclub.mycookbook.dto.ReceitaDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cookingclub.mycookbook.dto.ComentarioDTO.ComentarioDTOResponse;
import com.cookingclub.mycookbook.dto.ReceitaDTO.ReceitaDTOResponse;

import lombok.Data;

@Data
public class ReceitaDTOResponse {
    private String nome;
    private String ingredientes;
    private String preparo;
    private String tipoVersao;
    private Long receitaOriginal;
    private Date dataHora;
    private UUID usuario_id;
    private List<ComentarioDTOResponse> comentarios;
}