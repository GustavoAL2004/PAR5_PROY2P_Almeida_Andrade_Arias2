package com.example.appcomunicadosespol;
import android.os.Bundle;
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
import java.util.Locale;

public class TableroComunicadosActivity extends AppCompatActivity {

    private TableLayout miTabla;
    private ImageButton botonVolver;
    private Button botonOrdenarTitulo;
    private Button botonGuardarLista;
    private Button botonCancelar;
    public static String usuarioActual;

    private ArrayList<Comunicado> listaComunicados = new ArrayList<>();

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
        listaComunicados.clear();

        try (FileInputStream fi = openFileInput("comunicado.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(fi))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[partes.length - 1].equals(usuarioActual)) {
                    // Crea el objeto Comunicado y lo agrega a la lista
                    String tipo = partes[1];
                    String titulo = partes[3];
                    String fecha = partes[1].equals("Evento") ? partes[8] : "No contiene fecha porque es anuncio";
                    listaComunicados.add(new Comunicado(titulo, fecha));

                    // Crea la fila de la tabla
                    TableRow fila = new TableRow(this);
                    fila.setLayoutParams(new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT
                    ));

                    // Crea y configura el TextView para el título
                    TextView tvTitulo = new TextView(this);
                    tvTitulo.setText(titulo);
                    tvTitulo.setLayoutParams(new TableRow.LayoutParams(
                            0, TableRow.LayoutParams.WRAP_CONTENT, 1f
                    ));
                    fila.addView(tvTitulo);

                    // Crea y configura el TextView para la fecha
                    TextView tvFecha = new TextView(this);
                    tvFecha.setText(fecha);
                    tvFecha.setLayoutParams(new TableRow.LayoutParams(
                            0, TableRow.LayoutParams.WRAP_CONTENT, 1f
                    ));
                    fila.addView(tvFecha);

                    miTabla.addView(fila);
                }
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Este usuario no ha realizado comunicados", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Ocurrió un problema al leer el archivo", Toast.LENGTH_SHORT).show();
        }
    }

    private void ordenarPorTitulo() {
        if (listaComunicados.isEmpty()) {
            Toast.makeText(this, "No hay comunicados para ordenar.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Ordena la lista de comunicados por el título
        Collections.sort(listaComunicados, new Comparator<Comunicado>() {
            @Override
            public int compare(Comunicado c1, Comunicado c2) {
                return c1.getTitulo().trim().compareToIgnoreCase(c2.getTitulo().trim());
            }
        });

        // Limpia la tabla y la vuelve a llenar con la lista ordenada
        miTabla.removeAllViews();
        for (Comunicado comunicado : listaComunicados) {
            TableRow fila = new TableRow(this);
            fila.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
            ));

            TextView tvTitulo = new TextView(this);
            tvTitulo.setText(comunicado.getTitulo());
            tvTitulo.setLayoutParams(new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1f
            ));
            fila.addView(tvTitulo);

            TextView tvFecha = new TextView(this);
            tvFecha.setText(comunicado.getFecha());
            tvFecha.setLayoutParams(new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1f
            ));
            fila.addView(tvFecha);

            miTabla.addView(fila);
        }
        Toast.makeText(this, "Lista ordenada por título.", Toast.LENGTH_SHORT).show();
    }

    private void guardarListaSerializada() {
        if (listaComunicados.isEmpty()) {
            Toast.makeText(this, "No hay comunicados para guardar.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Genera el nombre de archivo con la fecha actual
        String fechaActual = new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault()).format(new Date());
        String nombreArchivo = "comunicados_al_" + fechaActual + ".dat";

        try (FileOutputStream fos = openFileOutput(nombreArchivo, MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(listaComunicados);
            Toast.makeText(this, "Lista guardada en " + nombreArchivo, Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar la lista: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private static class Comunicado implements Serializable {
        private String titulo;
        private String fecha;

        public Comunicado(String titulo, String fecha) {
            this.titulo = titulo;
            this.fecha = fecha;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getFecha() {
            return fecha;
        }
    }
}