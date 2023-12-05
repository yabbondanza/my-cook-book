package com.cookingclub.mycookbook.controller;

import com.cookingclub.mycookbook.dto.ReceitaDTO.ReceitaDTORequest;
import com.cookingclub.mycookbook.dto.ReceitaDTO.ReceitaDTOResponse;
import com.cookingclub.mycookbook.model.Receita;
import com.cookingclub.mycookbook.model.Usuario;
import com.cookingclub.mycookbook.repository.ReceitaRepository;
import com.cookingclub.mycookbook.repository.UsuarioRepository;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="*")
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/cadastrar-receita")
    @ResponseStatus(HttpStatus.CREATED)
    void cadastrarReceita(@Valid @RequestBody ReceitaDTORequest receitaDTO) {
        Receita receita = modelMapper.map(receitaDTO, Receita.class);

        receitaRepository.save(receita);
    }

    @GetMapping("/buscar-receita/{idReceita}")
    @ResponseStatus(HttpStatus.OK)
    ReceitaDTOResponse buscarReceita(@PathVariable("idReceita") Long idReceita) {
        Receita receita = receitaRepository.findById(idReceita)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada com o ID: " + idReceita));

        return modelMapper.map(receita, ReceitaDTOResponse.class);
    }

    @PutMapping("/atualizar-receita/{idReceita}")
    @ResponseStatus(HttpStatus.OK)
    void atualizarReceita(@PathVariable Long idReceita, @Valid @RequestBody ReceitaDTORequest novaReceitaDTO) {
        Receita receita = receitaRepository.findById(idReceita)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));

        modelMapper.map(novaReceitaDTO, receita);

        receitaRepository.save(receita);
    }

    @DeleteMapping("/deletar-receita/{idReceita}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletarReceita(@PathVariable Long idReceita) {
        if (!receitaRepository.existsById(idReceita)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada");
        }

        receitaRepository.deleteById(idReceita);
    }

    @GetMapping("/listar-por-usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    List<ReceitaDTOResponse> listarReceitasPorUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        List<Receita> receitas = receitaRepository.findByUsuario(usuario);

        return receitas.stream()
                .map(receita -> modelMapper.map(receita, ReceitaDTOResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/listar-todas-receitas")
    @ResponseStatus(HttpStatus.OK)
    List<ReceitaDTOResponse> listarTodasReceitas() {
        List<Receita> receitas = receitaRepository.findAll();
    
        return receitas.stream()
                .map(receita -> modelMapper.map(receita, ReceitaDTOResponse.class))
                .collect(Collectors.toList());
    }    

}