package com.cookingclub.mycookbook.dto.ComentarioDTO;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class ComentarioDTOResponse {
    private Long idComentario;
    private String descricao;
    private Date dataHora;
    private UUID usuario_id;
    private UUID receita_id;
}