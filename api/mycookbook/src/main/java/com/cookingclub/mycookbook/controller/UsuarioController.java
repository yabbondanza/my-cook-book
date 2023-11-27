package com.cookingclub.mycookbook.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.cookingclub.mycookbook.dto.UsuarioDto;
import com.cookingclub.mycookbook.model.Usuario;
import com.cookingclub.mycookbook.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void cadastrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        if (usuarioRepository.findByEmail(usuarioDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já existe no sistema.");
        }

        if (usuarioRepository.findByNomeUsuario(usuarioDto.getNomeUsuario()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome de usuário já existe no sistema.");
        }

        Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
        usuarioRepository.save(usuario);
    }

    @GetMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    UsuarioDto buscarUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    @GetMapping("/por-email/{email}")
    @ResponseStatus(HttpStatus.OK)
    UsuarioDto buscarPorEmail(@PathVariable(value = "email") String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        
        // Mapear o resultado para o DTO e retornar
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    
}
