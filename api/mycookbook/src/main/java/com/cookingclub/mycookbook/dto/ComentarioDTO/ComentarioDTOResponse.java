package com.cookingclub.mycookbook.dto.ComentarioDTO;

import java.util.Date;

import lombok.Data;

@Data
public class ComentarioDTOResponse {
    private Long idComentario;
    private String descricao;
    private Date dataHora;
    private Long idUsuario;
    private Long idReceita;
}