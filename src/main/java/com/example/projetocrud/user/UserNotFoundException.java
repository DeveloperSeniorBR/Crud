package com.example.projetocrud.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Usu�rio n�o encontrado com id=" + id);
    }
}
