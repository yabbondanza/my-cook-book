package com.example.mycookbookapi.controller;

import com.example.mycookbookapi.model.Receita;
import com.example.mycookbookapi.repository.ReceitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceitaController {
    
    @Autowired
    private ReceitaRepository repositorio;

    @GetMapping
    public List<Receita> listarReceitas() {
        return repositorio.findAll();
    }

    @GetMapping("/exibir/{id}")
    public Receita obterReceita(@PathVariable int id) {
        return repositorio.findById(id);
    }

    @PostMapping
    public Receita criarReceita(@RequestBody Receita receita) {
        return repositorio.save(receita);
    }

    @PutMapping("/atualiza")
    public Receita atualizarReceita(@PathVariable int id, @RequestBody Receita receita) {
        Receita receitaExistente = repositorio.findById(receita.getId());

        if (receitaExistente != null) {
            
            receitaExistente.setNome(receita.getNome());
            receitaExistente.setPreparo(receita.getPreparo());
            receitaExistente.setIngredientes(receita.getIngredientes());
            receitaExistente.setTipoVersao(receita.getTipoVersao());
        }

        return repositorio.save(receitaExistente);
    }

    @DeleteMapping("/deleta/{id}")
    public void deletarReceita(@PathVariable int id) {
        repositorio.deleteById(id);
    }
    
}
