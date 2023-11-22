package com.example.mycookbookapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nome;
    private List<String> ingredientes;
    private String preparo;
    private String data;
    private String horario;
    private int idUsuario;
    private String tipoVersao;
    private Receita receitaOriginal;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparo() {
        return preparo;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoVersao() {
        return tipoVersao;
    }

    public void setTipoVersao(String tipoVersao) {
        this.tipoVersao = tipoVersao;
    }

    public Receita getReceitaOriginal() {
        return receitaOriginal;
    }

    public void setReceitaOriginal(Receita receitaOriginal) {
        this.receitaOriginal = receitaOriginal;
    }
}
