package com.cookingclub.mycookbook.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comentario")
@Data
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentario_id")
    private Long idComentario;

    @Column(name = "descricao")
    private String descricao;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "data_hora")
    private Date dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receita_id")
    private Receita receita;
}