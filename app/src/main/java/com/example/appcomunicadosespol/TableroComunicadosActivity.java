package com.example.appcomunicadosespol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TableroComunicadosActivity extends AppCompatActivity {

    private TableLayout miTabla;
    private ImageButton botonVolver;
    private Button botonOrdenarTitulo;
    private Button botonGuardarLista;
    private Button botonCancelar;

    private List<Comunicado> listaComunicados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero_comunicados);

        // 1. Initialize UI components
        miTabla = findViewById(R.id.mi_tabla);
        botonVolver = findViewById(R.id.imagebuttonVolver);
        botonOrdenarTitulo = findViewById(R.id.button_OrdernarPorTitulo);
        botonGuardarLista = findViewById(R.id.button_GuardarLista);
        botonCancelar = findViewById(R.id.button_Cancelar);

        cargarDatosIniciales();

        botonVolver.setOnClickListener(v -> finish());

        botonOrdenarTitulo.setOnClickListener(v -> ordenarPorTitulo());

        botonGuardarLista.setOnClickListener(v -> {
            Toast.makeText(this, "Lista guardada correctamente.", Toast.LENGTH_SHORT).show();
        });

        botonCancelar.setOnClickListener(v -> finish());
    }

    private void cargarDatosIniciales() {
        // Here we read from the file
        LectorComunicados lector = new LectorComunicados();

        listaComunicados = lector.leerComunicados("path/to/comunicados.txt");  //no coge la direccion del archivo
        mostrarComunicadosEnTabla(listaComunicados);
    }

    private void mostrarComunicadosEnTabla(List<Comunicado> comunicados) {
        // Clear existing rows, except for the header
        if (miTabla.getChildCount() > 1) {
            miTabla.removeViews(1, miTabla.getChildCount() - 1);
        }

        for (Comunicado c : comunicados) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView tvTitulo = new TextView(this);
            tvTitulo.setText(c.getTitulo());
            tvTitulo.setPadding(8, 8, 8, 8);

            TextView tvFecha = new TextView(this);
            if (c instanceof Evento) {
                tvFecha.setText(((Evento) c).getFecha());
            } else {
                tvFecha.setText("");
            }
            tvFecha.setPadding(8, 8, 8, 8);

            row.addView(tvTitulo);
            row.addView(tvFecha);
            miTabla.addView(row);
        }
    }

    private void ordenarPorTitulo() {
        Collections.sort(listaComunicados, Comparator.comparing(Comunicado::getTitulo));
        mostrarComunicadosEnTabla(listaComunicados);
    }
}