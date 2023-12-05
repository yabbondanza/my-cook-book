package com.cookingclub.mycookbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.cookingclub.mycookbook.model.Usuario;
import com.cookingclub.mycookbook.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    void cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já existe no sistema.");
        }

        if (usuarioRepository.findByNomeUsuario(usuario.getNomeUsuario()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome de usuário já existe no sistema.");
        }

        usuarioRepository.save(usuario);
    }

    @GetMapping("/buscar/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    Usuario buscarUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return usuario;
    }

    @GetMapping("/buscar-por-email/{email}")
    @ResponseStatus(HttpStatus.OK)
    Usuario buscarPorEmail(@PathVariable(value = "email") String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return usuario;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    Usuario login(@RequestBody Usuario loginUsuario) {
        String email = loginUsuario.getEmail();
        String senha = loginUsuario.getSenha();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado"));

        if (!usuario.getSenha().equals(senha)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta");
        }

        return usuario;
    }
}