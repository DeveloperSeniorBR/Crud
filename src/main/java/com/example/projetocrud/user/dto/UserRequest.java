package com.example.projetocrud.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "O nome � obrigat�rio") String nome,
        @NotBlank(message = "O email � obrigat�rio") @Email(message = "Formato de email inv�lido") String email
) { }
