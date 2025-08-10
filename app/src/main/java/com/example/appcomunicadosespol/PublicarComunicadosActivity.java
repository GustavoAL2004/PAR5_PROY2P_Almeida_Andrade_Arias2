package com.example.appcomunicadosespol;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class PublicarComunicadosActivity extends AppCompatActivity {
    private ImageButton botonvolver;
    private RadioGroup radiogrupo;
    private Spinner spinnerArea;
    private Spinner spinnerNivelDeUrgencia;
    private CheckBox checkBoxEstudiantes;
    private CheckBox checkBoxProfesores;
    private CheckBox checkBoxAdministrativo;
    private EditText titulo;
    private EditText lugar;

    private EditText fecha;
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

        botonvolver=findViewById(R.id.ImagebuttonVolver);
        botonvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
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




        fecha=findViewById(R.id.editTextFecha);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendario= Calendar.getInstance();
                int anio=calendario.get(Calendar.YEAR);
                int mes=calendario.get(Calendar.MONTH);
                int dia=calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog PDP=new DatePickerDialog(PublicarComunicadosActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                        String fechaSeleccionada=dia+"/"+(mes+1)+"/"+anio;
                        fecha.setText(fechaSeleccionada);

                    }
                },anio,mes,dia);
                PDP.show();
            }
        });




        descripcion=findViewById(R.id.plainTextescripcion);
        botonAdjuntarImagen=findViewById(R.id.buttonAdjuntarImagen);
        botonPublicar=findViewById(R.id.buttonPublicar);
        botonCancelar=findViewById(R.id.buttonCancelar);


    }
    public void LimpiarFormulario(View view){
        titulo.setText("");
        descripcion.setText("");
        checkBoxEstudiantes.setChecked(false);
        checkBoxProfesores.setChecked(false);
        checkBoxAdministrativo.setChecked(false);
        spinnerArea.setSelection(0);
        spinnerNivelDeUrgencia.setSelection(0);
        lugar.setText("");
        fecha.setText("");
        radiogrupo.clearCheck();


    }
    private void AdjuntarImagen(View view){
        try (FileInputStream fis = openFileInput("comunicado.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",", 3);
                String uriStr = partes[1];
                String texto = partes[0];

                Uri uri = Uri.parse(uriStr);

                LinearLayout layoutInterno = new LinearLayout(this);
                layoutInterno.setOrientation(LinearLayout.VERTICAL);
                layoutInterno.setPadding(0, 0, 0, 40);

                ImageView imgv = new ImageView(this);
                imgv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imgv.setImageURI(uri);

                imgv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 600));
                // You would need to add this to a parent view to display it
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "No se pudo cargar la imagen", Toast.LENGTH_SHORT).show();
        }
    }

    public void PublicarComunicado(View view){
        String tituloText = titulo.getText().toString().trim();
        String lugarTexto = lugar.getText().toString().trim();
        String fechaTexto = fecha.getText().toString().trim();
        String descripcionTexto = descripcion.getText().toString().trim();

        if (radiogrupo.getCheckedRadioButtonId() == -1) {
            try {
                throw new datosIncompletosException("Selecciona el tipo de comunicado");
            }catch (datosIncompletosException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        }


        if (spinnerArea.getSelectedItemPosition() == -1){
            try {
                throw new datosIncompletosException("Selecciona al menos un area");
            }catch (datosIncompletosException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

        }
        if (spinnerNivelDeUrgencia.getSelectedItemPosition() == -1){
            try {
                throw new datosIncompletosException("Selecciona al menos un nivel de urgencia");
            }catch (datosIncompletosException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (!checkBoxEstudiantes.isChecked() && !checkBoxProfesores.isChecked() && !checkBoxAdministrativo.isChecked()) {
            try {
                throw new datosIncompletosException("Elige al menos una audiencia");
            }catch (datosIncompletosException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (tituloText.isEmpty()){
            try {
                throw new datosIncompletosException("LLene el campo del titulo");
            }catch (datosIncompletosException e){
            Toast.makeText(PublicarComunicadosActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            return;
        }
    }
        if (lugarTexto.isEmpty()){
            try {
                throw new datosIncompletosException("LLene el campo del lugar");
            }catch (datosIncompletosException e){
                Toast.makeText(PublicarComunicadosActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (fechaTexto.isEmpty()){
            try {
                throw new datosIncompletosException("LLene el campo de la fecha");
            }catch (datosIncompletosException e){
                Toast.makeText(PublicarComunicadosActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (descripcionTexto.isEmpty()){
            try {
                throw new datosIncompletosException("LLene el campo de la descripcion");
            }catch (datosIncompletosException e){
                Toast.makeText(PublicarComunicadosActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                return;
            }
        }





}
}

