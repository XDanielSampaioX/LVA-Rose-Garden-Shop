package com.LVA_Rose_Garden_Shop.util;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

public class CriptografarSenha {

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String criptografar(String senha) {
        if (senha.length() > 72) { // recomendação do BCrypt
            throw new IllegalArgumentException("Senha muito longa. Máximo permitido é 72 caracteres.");
        }
        return encoder.encode(senha);
    }

    public static boolean verificarSenha(String senha, String senhaCriptografada) {
        if (!encoder.matches(senha, senhaCriptografada)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
        }

        return encoder.matches(senha, senhaCriptografada);
    }
}
