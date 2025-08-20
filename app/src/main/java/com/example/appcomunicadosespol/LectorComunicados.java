package com.example.appcomunicadosespol;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorComunicados {

    public List<Comunicado> leerComunicados(String rutaArchivo) {
        List<Comunicado> comunicados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", -1);

                if (partes.length >= 7) {
                    try {
                        String tipo = partes[1].trim();

                        if ("evento".equalsIgnoreCase(tipo)) {
                            if (partes.length == 9) {
                                Evento evento = new Evento(
                                        partes[0].trim(),
                                        tipo,
                                        partes[2].trim(),
                                        partes[3].trim(),
                                        partes[4].trim(),
                                        partes[5].trim(),
                                        partes[6].trim(),
                                        partes[7].trim(),
                                        partes[8].trim()
                                );
                                comunicados.add(evento);
                            } else {
                                System.err.println("Línea de evento mal formada: " + linea);
                            }
                        } else if ("anuncio".equalsIgnoreCase(tipo)) {
                            if (partes.length == 8) {
                                Anuncio anuncio = new Anuncio(
                                        partes[0].trim(),
                                        tipo,
                                        partes[2].trim(),
                                        partes[3].trim(),
                                        partes[4].trim(),
                                        partes[5].trim(),
                                        partes[6].trim(),
                                        partes[7].trim()
                                );
                                comunicados.add(anuncio);
                            } else {
                                System.err.println("Línea de anuncio mal formada: " + linea);
                            }
                        }

                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear ID o datos en la línea: " + linea);
                    }
                } else {
                    System.err.println("Línea incompleta: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comunicados;
    }
}