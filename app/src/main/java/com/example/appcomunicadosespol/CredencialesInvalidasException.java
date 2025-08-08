package com.example.appcomunicadosespol;

public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException(String message) {
        message = "Usuario o la contraseña son incorrectos";
    }
}
