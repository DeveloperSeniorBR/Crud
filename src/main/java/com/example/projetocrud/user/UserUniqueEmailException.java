package com.example.projetocrud.user;

public class UserUniqueEmailException extends RuntimeException {

    public UserUniqueEmailException(String email) {
        super("Já existe usuário cadastrado com o email " + email);
    }
}
