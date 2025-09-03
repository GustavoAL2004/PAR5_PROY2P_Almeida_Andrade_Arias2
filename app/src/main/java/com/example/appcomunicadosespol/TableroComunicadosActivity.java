package com.example.appcomunicadosespol;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TableroComunicadosActivity extends AppCompatActivity {

    private TableLayout miTabla;
    private ImageButton botonVolver;
    private Button botonOrdenarTitulo;
    private Button botonGuardarLista;
    private Button botonCancelar;
    public static String usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero_comunicados);

        miTabla = findViewById(R.id.mi_tabla);
        botonVolver = findViewById(R.id.imagebuttonVolver);
        botonOrdenarTitulo = findViewById(R.id.button_OrdernarPorTitulo);
        botonGuardarLista = findViewById(R.id.button_GuardarLista);
        botonCancelar = findViewById(R.id.button_Cancelar);

        cargarDatosIniciales();

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        botonOrdenarTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordenarPorTitulo();
            }
        });

        botonGuardarLista.setOnClickListener(v -> {
            guardarListaSerializada();
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatosIniciales();
            }
        });
    }

    private void cargarDatosIniciales() {
        miTabla.removeAllViews();
        try (FileInputStream fi = openFileInput("comunicado.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(fi))) {

            String linea=reader.readLine();
            while(linea!=null){
                String[] partes=linea.split(",");

                if(partes[partes.length-1].equals(usuarioActual)){

                    //Se crea la fila de tabla
                    TableRow fila = new TableRow(this);

                    fila.setLayoutParams(new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT
                    ));

                    String titulo =partes[3];
                    //Se crean los TextView
                    TextView tvTitulo = new TextView(this);
                    tvTitulo.setText(titulo);
                    tvTitulo.setLayoutParams(new TableRow.LayoutParams(
                            0, TableRow.LayoutParams.WRAP_CONTENT, 1f
                    ));

                    tvTitulo.setGravity(Gravity.CENTER);
                    fila.addView(tvTitulo);

                    if(partes[1].equals("Evento")){
                        String fecha=partes[8];

                        TextView tvFecha = new TextView(this);
                        tvFecha.setText(fecha);
                        tvFecha.setLayoutParams(new TableRow.LayoutParams(
                                0, TableRow.LayoutParams.WRAP_CONTENT, 1f
                        ));

                        tvFecha.setGravity(Gravity.CENTER);
                        fila.addView(tvFecha);

                    }else{
                        TextView tvFecha = new TextView(this);
                        tvFecha.setText("No contiene fecha porque es anuncio");
                        tvFecha.setLayoutParams(new TableRow.LayoutParams(
                                0, TableRow.LayoutParams.WRAP_CONTENT, 1f
                        ));

                        tvFecha.setGravity(Gravity.CENTER);
                        fila.addView(tvFecha);
                    }
                    //Se agrega la tabla
                    miTabla.addView(fila);
                }
                linea=reader.readLine();
            }

        }catch (FileNotFoundException e){
            Toast.makeText(this, "Este usuario no ha realizado comunicados", Toast.LENGTH_SHORT).show();

        }catch (IOException e){
            Toast.makeText(this, "Ocurrio un problema al leer el archivo", Toast.LENGTH_SHORT).show();
        }
    }

    private void ordenarPorTitulo(){
        miTabla.removeAllViews();
        ArrayList<String[]> lista=new ArrayList<>();
        try (FileInputStream fi = openFileInput("comunicado.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(fi))) {
            String linea=reader.readLine();
            while(linea!=null) {
                String[] partes = linea.split(",");

                if (partes[partes.length - 1].equals(usuarioActual)) {
                    lista.add(partes);
                }
                linea=reader.readLine();
            }
        }catch (FileNotFoundException e) {
        }catch (IOException e) {
        }

        //Se ordena la lista de comunicados por tema
        Collections.sort(lista, new Comparator<String[]>() {
            @Override
            public int compare(String[] lista, String[] otraLista) {
                return lista[3].trim().compareToIgnoreCase(otraLista[3].trim());
            }
        });
        for(String[] partes:lista){
            String titulo =partes[3];


            //Se crea la fila de tabla
            TableRow fila = new TableRow(this);

            fila.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
            ));

            //Se crean los TextView
            TextView tvTitulo = new TextView(this);
            tvTitulo.setText(titulo);
            tvTitulo.setLayoutParams(new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1f
            ));

            //tvTitulo.setPadding(8, 8, 8, 8);

            TextView tvFecha = new TextView(this);
            if(partes[1].equals("Evento")){
                String fecha=partes[8];
                tvFecha.setText(fecha);
            }else{
                tvFecha.setText("No contiene fecha porque es anuncio");
            }
            tvFecha.setLayoutParams(new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1f
            ));

            //tvFecha.setPadding(8, 8, 8, 8);

            //Se agrega a la tabla
            fila.addView(tvTitulo);
            fila.addView(tvFecha);
            miTabla.addView(fila);
        }
    }

    private void guardarListaSerializada() {
        ArrayList<String[]> lista = new ArrayList<>();
        try (FileInputStream fi = openFileInput("comunicado.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(fi))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[partes.length - 1].equals(usuarioActual)) {
                    lista.add(partes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al preparar la lista para guardar.", Toast.LENGTH_SHORT).show();
        }

        if (lista.isEmpty()) {
            Toast.makeText(this, "No hay comunicados para guardar.", Toast.LENGTH_SHORT).show();
        }
        if(!lista.isEmpty()) {
            // Genera el nombre de archivo con la fecha actual
            String fechaActual = new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault()).format(new Date());
            String nombreArchivo = "comunicados_al_" + fechaActual + ".dat";


            try (FileOutputStream fos = openFileOutput(nombreArchivo, MODE_PRIVATE);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(lista);
                Toast.makeText(this, "Lista guardada en " + nombreArchivo, Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al guardar la lista", Toast.LENGTH_LONG).show();
            }
        }
    }
}