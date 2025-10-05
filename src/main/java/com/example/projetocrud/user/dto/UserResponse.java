package com.example.projetocrud.user.dto;

import java.time.LocalDateTime;

public record UserResponse(Long id, String nome, String email, LocalDateTime dataCriacao) { }
