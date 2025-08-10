package com.example.appcomunicadosespol;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class VerComunicadosActivity extends AppCompatActivity {

    private EditText editText_Fecha;

    private ImageButton botonVolver;

    private LinearLayout linearLayout_VerComunicados;

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
        linearLayout_VerComunicados=findViewById(R.id.linearLayout_VerComunicados);
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

            }
        });
    }
}