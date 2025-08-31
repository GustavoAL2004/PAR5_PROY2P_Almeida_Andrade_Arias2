package com.example.appcomunicadosespol;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class VerComunicadosActivity extends AppCompatActivity {

    private EditText editText_Fecha;

    private ImageButton botonVolver;
    private Button BotonFecha;
    private LinearLayout contenidoDinamico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_comunicados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText_Fecha = findViewById(R.id.editText_Fecha);
        contenidoDinamico=findViewById(R.id.contenidoDinamico);
        botonVolver=findViewById(R.id.imagebuttonVolver);

        //BotonVolver
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button botonFecha=findViewById(R.id.buttonFecha);

        //Uso de calendario para seleccionar fecha
        botonFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get((Calendar.DAY_OF_MONTH));
                DatePickerDialog datePick = new DatePickerDialog(VerComunicadosActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int anioSel, int mesSel, int daySel) {
                        String fechaSeleccionada = daySel + "/" + (mesSel + 1) + "/" + anioSel;
                        editText_Fecha.setText(fechaSeleccionada);

                        if(editText_Fecha!=null) {
                            String fecha = editText_Fecha.getText().toString();

                            //Se seleccionan los eventos a partir de la fecha seleccionada.
                            contenidoDinamico.removeAllViews();
                            try (FileInputStream fi = openFileInput("comunicado.txt");
                                 BufferedReader reader = new BufferedReader(new InputStreamReader(fi))) {
                                String linea = reader.readLine();
                                Boolean estado=false;

                                while (linea != null) {
                                    String[] partes = linea.split(",");
                                    if (partes[1].trim().equals("Evento")) {
                                        if (partes[8].trim().equals(fecha)) {
                                            estado=true;
                                            String tema = partes[3].trim();
                                            String codigoImagen = partes[6].trim();
                                            String descripcion = partes[5].trim();

                                            //ContenidoDinamico


                                            //TEMA
                                            TextView temaActivity = new TextView(VerComunicadosActivity.this);
                                            temaActivity.setText(tema);
                                            temaActivity.setTextSize(22);
                                            temaActivity.setTypeface(Typeface.DEFAULT_BOLD); //Negrita
                                            temaActivity.setTextColor(Color.parseColor("#1565C0")); //Color azul
                                            temaActivity.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                            temaActivity.setPadding(0, 16, 0, 16); //Espacio arriba y abajo
                                            contenidoDinamico.addView(temaActivity);

                                            //IMAGEN
                                            ImageView imagenActivity = new ImageView(VerComunicadosActivity.this);

                                            LinearLayout.LayoutParams imgLp = new LinearLayout.LayoutParams(
                                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                                    (int) (200 * getResources().getDisplayMetrics().density) // 200dp en píxeles
                                            );
                                            imgLp.setMargins(0, 16, 0, 16); // márgenes arriba y abajo
                                            imagenActivity.setLayoutParams(imgLp);

                                            //Aqui estoy recuperando la imagen que se guardo en el almacenamiento del celular anteriormente
                                            File file = new File(getFilesDir(), codigoImagen);
                                            Uri uri = Uri.fromFile(file);
                                            //Y aqui la coloco en el View imagenActivit
                                            imagenActivity.setImageURI(uri);

                                            imagenActivity.setAdjustViewBounds(true);
                                            imagenActivity.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                            contenidoDinamico.addView(imagenActivity);

                                            //DESCRIPCION
                                            TextView descripcionActivity = new TextView(VerComunicadosActivity.this);
                                            descripcionActivity.setText(descripcion);
                                            descripcionActivity.setTextSize(16);
                                            descripcionActivity.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                            descripcionActivity.setTypeface(Typeface.SERIF);
                                            descripcionActivity.setPadding(0, 16, 0, 0);
                                            contenidoDinamico.addView(descripcionActivity);
                                        }
                                    }
                                    linea = reader.readLine();
                                }
                                if(!estado){
                                    Toast.makeText(VerComunicadosActivity.this, "No se encontro eventos en la fecha dada", Toast.LENGTH_SHORT).show();
                                }
                            } catch (FileNotFoundException e) {
                                Toast.makeText(VerComunicadosActivity.this, "No se encontro el archivo", Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                Toast.makeText(VerComunicadosActivity.this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, year, month, day);
                datePick.show();
            }
        });
    }
}