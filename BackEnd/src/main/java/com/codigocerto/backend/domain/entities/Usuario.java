package com.codigocerto.backend.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.codigocerto.backend.domain.enums.Disponibilidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(of = "idUsuario")
@Setter
@Getter
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Disponibilidade disponibilidade;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String linguagem;

    @Column(nullable = false)
    private String area;

    public Usuario() {
        this.dataCadastro = LocalDateTime.now();
    }
}