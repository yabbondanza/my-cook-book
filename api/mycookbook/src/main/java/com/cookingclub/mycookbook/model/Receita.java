package com.cookingclub.mycookbook.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "receita")
@Data
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receita_id")
    private Long idReceita;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ingredientes")
    private String ingredientes;

    @Column(name = "preparo")
    private String preparo;

    @Column(name = "tipo_versao")
    private String tipoVersao;

    @Column(name = "receita_original")
    private Long receitaOriginal;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "data_hora")
    private Date dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "receita")
    private List<Comentario> comentarios;
}