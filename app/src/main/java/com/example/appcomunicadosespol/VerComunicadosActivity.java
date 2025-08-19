package com.example.appcomunicadosespol;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class VerComunicadosActivity extends AppCompatActivity {

    private EditText editText_Fecha;

    private ImageButton botonVolver;

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
        contenidoDinamico=findViewById(R.id.linearLayout_VerComunicados);
        botonVolver=findViewById(R.id.imagebuttonVolver);


        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editText_Fecha.setOnClickListener(new View.OnClickListener() {
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
                    }
                }, year, month, day);
                datePick.show();

                String fecha=editText_Fecha.getText().toString();

                try (InputStream is = getAssets().open("comunicados.txt");
                     BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                    String linea=reader.readLine();
                    while(linea!=null){
                        String[] partes=linea.split(",");
                        if(partes[1].equals("evento")&&partes[8].equals(fecha)){

                            String tema=partes[3];
                            String imagen=partes[6];
                            String descripcion=partes[5];

                            TextView temaActivity=new TextView(VerComunicadosActivity.this);
                            temaActivity.setText(tema);
                            contenidoDinamico.addView(temaActivity);
                            //Creo que solo aqui se puede modificar los atributos del view.
                            ImageView imagenActivity=new ImageView(VerComunicadosActivity.this);
                            imagenActivity.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            ));
                            //Como guardar imagen
                            imagenActivity.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            contenidoDinamico.addView(imagenActivity);

                            TextView descripcionActivity=new TextView(VerComunicadosActivity.this);
                            descripcionActivity.setText(descripcion);
                            contenidoDinamico.addView(descripcionActivity);
                            //Creo que solo aqui se puede modificar los atributos del view.
                        }
                        linea=reader.readLine();
                    }
                    }catch (IOException e){
                }
            }
        });
    }
}