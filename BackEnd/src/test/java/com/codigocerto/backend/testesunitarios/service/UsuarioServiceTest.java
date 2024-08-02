package com.codigocerto.backend.testesunitarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codigocerto.backend.domain.dtos.UsuarioRequestDto;
import com.codigocerto.backend.domain.dtos.UsuarioResponseDto;
import com.codigocerto.backend.domain.entities.Usuario;
import com.codigocerto.backend.domain.enums.Status;
import com.codigocerto.backend.domain.repositories.UsuarioRepository;
import com.codigocerto.backend.domain.services.UsuarioService;
import com.codigocerto.backend.exceptions.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    
    @Mock
    private UsuarioRepository repository;

    @Mock
    private UsuarioRequestDto usuarioRequestDto;
    
    @Mock
    private UsuarioResponseDto usuarioResponseDto;
    
    @InjectMocks
    private UsuarioService service;
    
    private Usuario usuario;    
    private static final Long ID_USUARIO = 1L;
    
    @BeforeEach
    void setup() {
        usuario = new Usuario();
        usuario.setNome("Carlos");
        usuario.setSobrenome("Black");
        usuario.setEmail("carlos@email.com");
        usuario.setDisponibilidade("Tarde");
        usuario.setArea("Back-end");
        usuario.setLinguagem("Java");
        usuario.setStatus(Status.FILA_DE_ESPERA);
        usuario.setDataCadastro(LocalDateTime.now());
    }

    @Test
    @DisplayName("Quando salvar um usuario deve retornar um objeto do tipo UsuarioResponseDto")
    void testQuandoSalvarUmUsuarioDeveRetornarUmObjetoDoTipoUsuarioResponseDto() {

        given(repository.save(usuario)).willReturn(usuario);

        UsuarioResponseDto usuarioSalvo = service.create(usuarioRequestDto);

        assertNotNull(usuarioSalvo);
        assertEquals("Carlos", usuarioSalvo.nome());
        assertEquals("carlos@email.com", usuarioSalvo.email());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Quando buscar todos os usuarios deve retornar uma lista de objetos do tipo UsuarioResponseDto")
    void testQuandoBuscarTodosOsUsuariosDeveRetornarUmaListaDeObjetosDoTipoUsuarioResponseDto() {

        given(repository.findAll()).willReturn(List.of(usuario));

        List<UsuarioResponseDto> usuarios = service.findAll();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertTrue(usuarios.stream().anyMatch(x -> x.sobrenome().equals("Black")));
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    @DisplayName("Deve retornar um usuario com base no ID informado")
    void testDeveRetornarUmUsuarioComBaseNoIdInformado() {

        given(repository.findById(anyLong())).willReturn(Optional.of(usuario));

        UsuarioResponseDto usuarioEncontrado = service.findById(ID_USUARIO);

        assertNotNull(usuarioEncontrado);
        assertEquals("Carlos", usuarioEncontrado.nome());
        verify(repository, atLeast(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve retornar um ResourceNotFoundException se o usuario nao for encontrado")   
    void testDeveRetornarUmResourceNotFoundExceptionSeOUsuarioNaoForEncontrado() {

        given(repository.findById(anyLong())).willReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.findById(ID_USUARIO));

        assertEquals("Nenhum usuario encontrada com ID: " + ID_USUARIO, exception.getMessage());
        verify(repository, times(1)).findById(anyLong());
    }
}