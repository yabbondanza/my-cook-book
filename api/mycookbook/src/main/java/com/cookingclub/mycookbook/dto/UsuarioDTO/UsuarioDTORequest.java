package com.cookingclub.mycookbook.dto.UsuarioDTO;

import lombok.Data;

@Data
public class UsuarioDTORequest {
    private String nome;
    private String nomeUsuario;
    private String email;
    private String senha;
}