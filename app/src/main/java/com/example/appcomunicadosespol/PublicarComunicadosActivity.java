package com.example.appcomunicadosespol;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PublicarComunicadosActivity extends AppCompatActivity {
    private RadioGroup radiogrupo;
    private Spinner spinnerArea;
    private Spinner spinnerNivelDeUrgencia;
    private CheckBox checkBoxEstudiantes;
    private CheckBox checkBoxProfesores;
    private CheckBox checkBoxAdministrativo;
    private EditText titulo;
    private EditText lugar;

    //private calendario
    private EditText descripcion;
    private Button botonAdjuntarImagen;
    private Button botonPublicar;
    private Button botonCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_publicar_comunicados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        radiogrupo=findViewById(R.id.RadioGrupo1);

        spinnerArea=findViewById(R.id.spinnerArea);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.areas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(adapter);

        spinnerNivelDeUrgencia=findViewById(R.id.spinnerNivelDeUrgencia);
        ArrayAdapter<CharSequence>adapter1=ArrayAdapter.createFromResource(this,R.array.niveles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNivelDeUrgencia.setAdapter(adapter1);

        checkBoxEstudiantes=findViewById(R.id.ckBoxEstudiantes);
        checkBoxProfesores=findViewById(R.id.ckBoxProfesores);
        checkBoxAdministrativo=findViewById(R.id.ckBoxAdministrativo);
        titulo=findViewById(R.id.EditTextTitulo);
        lugar=findViewById(R.id.editTextLugar);
        //calendario
        descripcion=findViewById(R.id.plainTextescripcion);
        botonAdjuntarImagen=findViewById(R.id.buttonAdjuntarImagen);
        botonPublicar=findViewById(R.id.buttonPublicar);
        botonCancelar=findViewById(R.id.buttonCancelar);
    }
}