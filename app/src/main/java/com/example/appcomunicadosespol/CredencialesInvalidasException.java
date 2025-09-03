package com.example.appcomunicadosespol;

/**
 * La clase CredencialesInvalidasException es una excepción creada que extiende de RuntimeException,
 * se lanza la excepción cuando las credenciales de inicio de sesión son inválidas.
 */

public class CredencialesInvalidasException extends RuntimeException {

    /**
     * Constructor de la excepción CredencialesInvalidasException.
     *
     * @param mensaje Mensaje que describe el problema.
     */

    public CredencialesInvalidasException(String mensaje) {
        super(mensaje);
    }
}
