package com.codigocerto.backend.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codigocerto.backend.domain.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}