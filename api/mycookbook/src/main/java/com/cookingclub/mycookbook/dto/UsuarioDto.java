package com.cookingclub.mycookbook.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private Long idUsuario;
    private String nome;
    private String nomeUsuario;
    private String email;
    private String senha;
}

