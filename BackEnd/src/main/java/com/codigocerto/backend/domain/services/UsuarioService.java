package com.codigocerto.backend.domain.services;

import com.codigocerto.backend.domain.dtos.Mapper;
import com.codigocerto.backend.domain.dtos.UsuarioRequestDto;
import com.codigocerto.backend.domain.dtos.UsuarioResponseDto;
import com.codigocerto.backend.domain.entities.Usuario;
import com.codigocerto.backend.domain.enums.Status;
import com.codigocerto.backend.domain.repositories.UsuarioRepository;
import com.codigocerto.backend.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private static final String NENHUM_USUARIO = "Nenhum usuario encontrada com ID: ";

    public List<UsuarioResponseDto> findAll(){
        log.info("Listando todos os usuarios");
        return repository.findAll().stream().map(Mapper::toDto).toList();
    }

    public UsuarioResponseDto findById(Long id){
        log.info("Buscando usuario com ID: {}", id);
        return repository.findById(id).map(Mapper::toDto).orElseThrow(() -> new ResourceNotFoundException(NENHUM_USUARIO + id));
    }

    public UsuarioResponseDto create(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDto.nome());
        usuario.setEmail(usuarioRequestDto.email());
        usuario.setSobrenome(usuarioRequestDto.sobrenome());
        usuario.setLinguagem(usuarioRequestDto.linguagem());
        usuario.setArea(usuarioRequestDto.area());
        usuario.setDescricao(usuarioRequestDto.descricao());
        usuario.setDisponibilidade(usuarioRequestDto.disponibilidade());
        usuario.setStatus(Status.FILA_DE_ESPERA);

        // Define o fuso horário para São Paulo, Brasil
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

        // Converte para LocalDateTime
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        usuario.setDataCadastro(localDateTime);

        log.info("Criando novo usuario");
        return Mapper.toDto(repository.save(usuario));
    }
}
