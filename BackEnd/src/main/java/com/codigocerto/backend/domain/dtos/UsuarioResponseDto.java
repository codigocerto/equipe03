package com.codigocerto.backend.domain.dtos;

import com.codigocerto.backend.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record UsuarioResponseDto(

        Long idUsuario,
        String nome,
        String sobrenome,
        String email,
        String disponibilidade,
        String area,
        String linguagem,
        String descricao,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dataCadastro,
        Status status
) {
}
