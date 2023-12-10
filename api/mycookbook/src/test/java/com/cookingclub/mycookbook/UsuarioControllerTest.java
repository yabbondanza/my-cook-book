package com.cookingclub.mycookbook;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.cookingclub.mycookbook.controller.UsuarioController;
import com.cookingclub.mycookbook.dto.UsuarioDTO.UsuarioDTOLogin;
import com.cookingclub.mycookbook.dto.UsuarioDTO.UsuarioDTORequest;
import com.cookingclub.mycookbook.model.Usuario;
import com.cookingclub.mycookbook.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void testCadastrarUsuario() {
        UsuarioDTORequest usuarioDTORequest = new UsuarioDTORequest();
        usuarioDTORequest.setEmail("usuarioteste@example.com");
        usuarioDTORequest.setNome("Usuário Teste");
        usuarioDTORequest.setNomeUsuario("usuarioteste");
        usuarioDTORequest.setSenha("senha123");

        Usuario usuario = new Usuario();
        usuario.setEmail("usuarioteste@example.com");
        usuario.setNome("Usuário Teste");
        usuario.setNomeUsuario("usuarioteste");
        usuario.setSenha("senha123");

        when(modelMapper.map(usuarioDTORequest, Usuario.class)).thenReturn(usuario);
        when(usuarioRepository.findByEmail("usuarioteste@example.com")).thenReturn(Optional.empty());
        when(usuarioRepository.findByNomeUsuario("usuarioteste")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> usuarioController.cadastrarUsuario(usuarioDTORequest));
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testCadastrarUsuarioWithExistingEmail() {
        UsuarioDTORequest usuarioDTORequest = new UsuarioDTORequest();
        usuarioDTORequest.setEmail("usuarioteste@example.com");
        usuarioDTORequest.setNome("Usuário Teste");
        usuarioDTORequest.setNomeUsuario("usuarioteste");
        usuarioDTORequest.setSenha("senha123");

        Usuario usuario = new Usuario();
        usuario.setEmail("usuarioteste@example.com");
        usuario.setNome("Usuário Teste");
        usuario.setNomeUsuario("usuarioteste");
        usuario.setSenha("senha123");

        when(modelMapper.map(usuarioDTORequest, Usuario.class)).thenReturn(usuario);
        when(usuarioRepository.findByEmail("usuarioteste@example.com")).thenReturn(Optional.of(usuario));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> usuarioController.cadastrarUsuario(usuarioDTORequest));

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        assertEquals("Email já existe no sistema.", exception.getReason());
    }

    @Test
    void testLoginUsuario() {
        UsuarioDTOLogin loginUsuarioDTO = new UsuarioDTOLogin();
        loginUsuarioDTO.setEmail("usuarioteste@example.com");
        loginUsuarioDTO.setSenha("senha123");

        Usuario usuario = new Usuario();
        usuario.setEmail("usuarioteste@example.com");
        usuario.setSenha("senha123");

        when(usuarioRepository.findByEmail("usuarioteste@example.com")).thenReturn(Optional.of(usuario));

        ResponseEntity<String> response = usuarioController.login(loginUsuarioDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário autorizado a entrar", response.getBody());

        loginUsuarioDTO.setSenha("senhaincorreta");

        when(usuarioRepository.findByEmail("usuarioteste@example.com")).thenReturn(Optional.of(usuario));

        ResponseStatusException exceptionSenhaIncorreta = assertThrows(ResponseStatusException.class,
                () -> usuarioController.login(loginUsuarioDTO));

        assertEquals(HttpStatus.UNAUTHORIZED, exceptionSenhaIncorreta.getStatusCode());
        assertEquals("Senha incorreta", exceptionSenhaIncorreta.getReason());

        loginUsuarioDTO.setEmail("emailincorreto@example.com");

        when(usuarioRepository.findByEmail("emailincorreto@example.com")).thenReturn(Optional.empty());

        ResponseStatusException exceptionEmailIncorreto = assertThrows(ResponseStatusException.class,
                () -> usuarioController.login(loginUsuarioDTO));

        assertEquals(HttpStatus.UNAUTHORIZED, exceptionEmailIncorreto.getStatusCode());
        assertEquals("Email incorreto", exceptionEmailIncorreto.getReason());
    }
}
