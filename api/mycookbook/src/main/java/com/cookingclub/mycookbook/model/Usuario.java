package com.cookingclub.mycookbook.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class Usuario { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long idUsuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Receita> receitas;
    
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Comentario> comentarios;
}