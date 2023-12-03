package com.cookingclub.mycookbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.cookingclub.mycookbook.model.Receita;
import com.cookingclub.mycookbook.model.Usuario;
import com.cookingclub.mycookbook.repository.ReceitaRepository;
import com.cookingclub.mycookbook.repository.UsuarioRepository;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void cadastrarReceita(@Valid @RequestBody Receita receita) {
        receitaRepository.save(receita);
    }

    @GetMapping("/{idReceita}")
    @ResponseStatus(HttpStatus.OK)
    Receita buscarReceita(@PathVariable(value = "idReceita") Long idReceita) {
        return receitaRepository.findById(idReceita)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));
    }


    @PutMapping("/{idReceita}")
    @ResponseStatus(HttpStatus.OK)
    void atualizarReceita(@PathVariable Long idReceita, @Valid @RequestBody Receita novaReceita) {
        if (!receitaRepository.existsById(idReceita)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada");
        }

        novaReceita.setId(idReceita);
        receitaRepository.save(novaReceita);
    }

    @DeleteMapping("/{idReceita}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletarReceita(@PathVariable Long idReceita) {
        if (!receitaRepository.existsById(idReceita)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada");
        }

        receitaRepository.deleteById(idReceita);
    }


    @GetMapping("/por-usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    List<Receita> buscarReceitasPorUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return receitaRepository.findByUsuario(usuario);
    }

    @GetMapping("/por-usuario/{idUsuario}/salvas")
    @ResponseStatus(HttpStatus.OK)
    List<Receita> buscarReceitasSalvasPorUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return receitaRepository.findByUsuario(usuario);
    }
}
