package com.codigocerto.backend.domain.dtos;

import com.codigocerto.backend.domain.enums.Disponibilidade;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDto(

    @NotBlank(message = "{nome.obrigatorio}") 
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "{campo.letras}")
    @Size(min = 3, message = "'${validatedValue}' precisa ter, pelo menos, {min} caracteres.")
    String nome,

    @NotBlank(message = "{sobrenome.obrigatorio}") 
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "{campo.letras}")
    @Size(min = 3, message = "'${validatedValue}' precisa ter, pelo menos, {min} caracteres.")
    String sobrenome,

    @NotBlank(message = "{email.obrigatorio}") 
    @Email(message = "{email.invalido}")
    String email,

    @NotNull(message = "{campo.obrigatorio}")
    Disponibilidade disponibilidade,

    @NotBlank(message = "{campo.obrigatorio}")
    String linguagem,

    @NotBlank(message = "{campo.obrigatorio}")
    String area, 

    @NotBlank(message = "{campo.obrigatorio}")
    @Size(max = 300, message = "O campo '${validatedValue}' não pode ter mais que {max} caracteres")
    String descricao
) {}