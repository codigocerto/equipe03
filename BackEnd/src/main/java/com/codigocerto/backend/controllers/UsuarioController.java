package com.codigocerto.backend.controllers;


import com.codigocerto.backend.domain.dtos.UsuarioRequestDto;
import com.codigocerto.backend.domain.dtos.UsuarioResponseDto;
import com.codigocerto.backend.domain.entities.Usuario;
import com.codigocerto.backend.domain.repositories.UsuarioRepository;
import com.codigocerto.backend.domain.services.UsuarioService;
import com.codigocerto.backend.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> createUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        UsuarioResponseDto usuarioResponseDto = usuarioService.create(usuarioRequestDto);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        List<UsuarioResponseDto> usuarioResponseDtos = usuarioService.findAll();
        return new ResponseEntity<>(usuarioResponseDtos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id) {
        UsuarioResponseDto usuarioResponseDto = usuarioService.findById(id);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.OK);
    }



    @DeleteMapping
    public ResponseEntity deleteAll() {
        usuarioRepository.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }
}
