package com.cookingclub.mycookbook.controller;

import com.cookingclub.mycookbook.dto.UsuarioDTO.UsuarioDTOLogin;
import com.cookingclub.mycookbook.dto.UsuarioDTO.UsuarioDTORequest;
import com.cookingclub.mycookbook.dto.UsuarioDTO.UsuarioDTOResponse;
import com.cookingclub.mycookbook.model.Usuario;
import com.cookingclub.mycookbook.repository.UsuarioRepository;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins="*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    void cadastrarUsuario(@Validated @RequestBody UsuarioDTORequest usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

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
    UsuarioDTOResponse buscarUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return modelMapper.map(usuario, UsuarioDTOResponse.class);
    }

    @GetMapping("/buscar-por-email/{email}")
    @ResponseStatus(HttpStatus.OK)
    UsuarioDTOResponse buscarPorEmail(@PathVariable(value = "email") String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return modelMapper.map(usuario, UsuarioDTOResponse.class);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestBody UsuarioDTOLogin loginUsuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(loginUsuarioDTO.getEmail());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (usuario.getSenha().equals(loginUsuarioDTO.getSenha())) {
                return ResponseEntity.ok("Usuário autorizado a entrar");
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email incorreto");
        }
    }
}