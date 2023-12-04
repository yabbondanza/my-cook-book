package com.cookingclub.mycookbook.controller;

import com.cookingclub.mycookbook.model.Usuario;
import com.cookingclub.mycookbook.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return "Email já cadastrado";
        }

        if (usuarioRepository.findByNomeUsuario(usuario.getNomeUsuario()).isPresent()) {
            return "Nome de usuário já em uso";
        }

        usuarioRepository.save(usuario);
        return "Usuário cadastrado com sucesso";
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @GetMapping("/porEmail")
    public Usuario getUsuarioByEmail(@RequestParam String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return "Login bem-sucedido";
        } else {
            return "Credenciais inválidas";
        }
    }
}
