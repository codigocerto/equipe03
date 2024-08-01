package com.codigocerto.backend.domain.dtos;

import com.codigocerto.backend.domain.entities.Usuario;

public class Mapper {

    private Mapper(){}

    public static UsuarioResponseDto toDto(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getEmail(),
                usuario.getDisponibilidade(),
                usuario.getArea(),
                usuario.getLinguagem(),
                usuario.getDescricao(),
                usuario.getDataCadastro(),
                usuario.getStatus()
                );
    }
}
