package com.example.projetocrud.user;

public class UserUniqueEmailException extends RuntimeException {

    public UserUniqueEmailException(String email) {
        super("J� existe usu�rio cadastrado com o email " + email);
    }
}
