package com.codigocerto.backend.controllers;


import com.codigocerto.backend.domain.dtos.UsuarioRequestDto;
import com.codigocerto.backend.domain.dtos.UsuarioResponseDto;
import com.codigocerto.backend.domain.entities.Usuario;
import com.codigocerto.backend.domain.services.UsuarioService;
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

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> createUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        UsuarioResponseDto usuarioResponseDto = usuarioService.create(usuarioRequestDto);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.CREATED);
    }
    @GetMapping
    public  ResponseEntity<List<UsuarioResponseDto>> findAll(){
        List<UsuarioResponseDto> usuarioResponseDtos = usuarioService.findAll();
        return new ResponseEntity<>(usuarioResponseDtos, HttpStatus.OK);
            }
}
