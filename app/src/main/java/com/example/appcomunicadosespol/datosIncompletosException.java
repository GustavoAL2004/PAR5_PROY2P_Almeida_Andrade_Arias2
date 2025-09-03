package com.example.appcomunicadosespol;

/**
 * La clase datosIncompletosException es una excepción creada que extiende de RuntimeException,
 * se lanza cuando todos los campos no se han ingresado en el fomulario.
 */

public class datosIncompletosException extends RuntimeException {

    /**
     * Constructor de la excepción datosIncompletosException.
     *
     * @param message Mensaje que describe el problema.
     */
    public datosIncompletosException(String message) {
        super(message);
    }
}
