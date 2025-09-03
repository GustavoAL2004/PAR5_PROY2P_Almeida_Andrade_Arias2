package com.example.appcomunicadosespol;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

    private LinearLayout layoutAnuncio;
    private LinearLayout layoutEvento1;
    private LinearLayout layoutEvento2;

    private ImageView imagenSeleccionada;
    private static final int REQUEST_IMAGE_PICK = 100;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private Uri imageUriSeleccionada;

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
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNivelDeUrgencia.setAdapter(adapter1);

        checkBoxEstudiantes=findViewById(R.id.ckBoxEstudiantes);
        checkBoxProfesores=findViewById(R.id.ckBoxProfesores);
        checkBoxAdministrativo=findViewById(R.id.ckBoxAdministrativo);
        titulo=findViewById(R.id.EditTextTitulo);
        lugar=findViewById(R.id.editTextLugar);
        layoutAnuncio = findViewById(R.id.LinearLayout_Anuncio);
        layoutEvento1 = findViewById(R.id.LinearLayout_Evento_pt1);
        layoutEvento2 = findViewById(R.id.LinearLayout_Evento_pt2);
        imagenSeleccionada = findViewById(R.id.imagenSeleccionada);

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    // manejar aquí la imagen seleccionada
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {// Se asegura de que la actividad terminó correctamente (RESULT_OK) y que hay datos disponibles (la imagen seleccionada)
                        Uri originalUri = result.getData().getData(); // Obtiene la URI original de la imagen que el usuario seleccionó en la galería.
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(originalUri);//Abre un InputStream para leer los datos binarios de la imagen desde la URI.
                            String nombreArchivo = "img_" + System.currentTimeMillis() + ".jpg";//Crea un nombre único para la imagen usando la hora actual (en milisegundos).
                            File file = new File(getFilesDir(), nombreArchivo);//Crea un archivo dentro del almacenamiento interno de la app.
                            FileOutputStream outputStream = new FileOutputStream(file);//Abre un OutputStream para escribir en el archivo.
                            byte[] buffer = new byte[1024];//Lee la imagen por partes (en bloques de 1024 bytes) y la escribe en el archivo local. Esto permite copiar imágenes de cualquier tamaño.
                            int length;
                            while ((length = inputStream.read(buffer)) > 0) {
                                outputStream.write(buffer, 0, length);
                            }
                            inputStream.close();
                            outputStream.close();
                            imageUriSeleccionada = Uri.fromFile(file);//Obtiene una URI segura para la imagen local
                            imagenSeleccionada.setImageURI(imageUriSeleccionada); // y luego la muestra en el ImageView
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Error al copiar imagen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        botonAdjuntarImagen=findViewById(R.id.buttonAdjuntarImagen);
        botonAdjuntarImagen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                imagePickerLauncher.launch(intent);
            }
        });

        radiogrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonEsAnuncio) {
                    // Si el usuario selecciona com.example.appcomunicadosespol.Anuncio
                    layoutAnuncio.setVisibility(View.VISIBLE); // VISIBLE visible para el usuario
                    layoutEvento1.setVisibility(View.GONE); // GONE no es visible y deja de ocupar espacio
                    layoutEvento2.setVisibility(View.GONE);
                } else if (checkedId == R.id.radioButtonEsEvento) {
                    // Si el usuario selecciona com.example.appcomunicadosespol.Evento
                    layoutAnuncio.setVisibility(View.GONE);
                    layoutEvento1.setVisibility(View.VISIBLE);
                    layoutEvento2.setVisibility(View.VISIBLE);
                }
            }
        });

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
        imagenSeleccionada.setImageURI(null);
    }

    public void PublicarComunicado(View view){
        String tituloText = titulo.getText().toString().trim();
        String lugarTexto = lugar.getText().toString().trim();
        String fechaTexto = fecha.getText().toString().trim();
        String descripcionTexto = descripcion.getText().toString().trim();
        int tipoSeleccionadoId = radiogrupo.getCheckedRadioButtonId();
        String areaSeleccionada = spinnerArea.getSelectedItem().toString();
        String audiencia = "";

        if (checkBoxEstudiantes.isChecked()) audiencia += "Estudiantes;";
        if (checkBoxProfesores.isChecked()) audiencia += "Profesores;";
        if (checkBoxAdministrativo.isChecked()) audiencia += "Administrativo;";
        String lineaComunicado;
        String nombreArchivoImagen = "";
        //Validaciones
        boolean comprobacion=true;
        try {
            if (tipoSeleccionadoId == -1) {
                throw new datosIncompletosException("Selecciona el tipo de comunicado.");
            }
            if (spinnerArea.getSelectedItemPosition() == 0) {
                throw new datosIncompletosException("Seleccione una área.");
            }
            if (!checkBoxEstudiantes.isChecked() && !checkBoxProfesores.isChecked() && !checkBoxAdministrativo.isChecked()) {
                throw new datosIncompletosException("Elige al menos una audiencia.");
            }
            if (tituloText.isEmpty()) {
                throw new datosIncompletosException("Llene el campo del título.");
            }
            if (descripcionTexto.isEmpty()) {
                throw new datosIncompletosException("Llene el campo de la descripción.");
            }
            if (imageUriSeleccionada == null) {
                throw new datosIncompletosException("Adjunte una imagen.");
            }
            if (tipoSeleccionadoId == R.id.radioButtonEsAnuncio) {
                if (spinnerNivelDeUrgencia.getSelectedItemPosition() == 0) {
                    throw new datosIncompletosException("Seleccione un nivel de urgencia.");
                }
            } else if (tipoSeleccionadoId == R.id.radioButtonEsEvento) {
                if (lugarTexto.isEmpty()) {
                    throw new datosIncompletosException("Llene el campo de lugar.");
                }
                if (fechaTexto.isEmpty()) {
                    throw new datosIncompletosException("Llene el campo de la fecha.");
                }
            }
        } catch (datosIncompletosException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            comprobacion=false;
        }
        if (imageUriSeleccionada != null) {
            nombreArchivoImagen = imageUriSeleccionada.getLastPathSegment();

        if(comprobacion){
            int ultimoId=0;
            try(FileInputStream fis = openFileInput("comunicado.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis))){
                String ultimaLinea = null;
                String linea;

                while ((linea = reader.readLine()) != null) {
                    ultimaLinea = linea;
                }
                    String[] partes = ultimaLinea.split(",");
                    ultimoId = Integer.parseInt(partes[0].trim());
            } catch (FileNotFoundException e) {
                ultimoId=0;
            }catch (IOException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            ultimoId++;
            String idComunicado = String.valueOf(ultimoId);

            if (tipoSeleccionadoId == R.id.radioButtonEsAnuncio) {
                String nivelUrgencia = spinnerNivelDeUrgencia.getSelectedItem().toString();
                Anuncio anuncio = new Anuncio(idComunicado,"Anuncio", areaSeleccionada, tituloText, audiencia, descripcionTexto, nombreArchivoImagen, nivelUrgencia);
                lineaComunicado = anuncio.toString();
            } else {
                Evento evento = new Evento(idComunicado,"Evento", areaSeleccionada, tituloText, audiencia, descripcionTexto, nombreArchivoImagen, lugarTexto, fechaTexto);
                lineaComunicado = evento.toString();
            }

            try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("comunicado.txt", MODE_APPEND)))){
                writer.write(lineaComunicado+","+TableroComunicadosActivity.usuarioActual);
                writer.newLine();
            }catch (FileNotFoundException e){
                Toast.makeText(this, "No se encontro el archivo", Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                e.printStackTrace();
                Toast.makeText(this, "Error guardando comunicado", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this, "Se ha publicado exitosamente su comunicado.", Toast.LENGTH_SHORT).show();
            LimpiarFormulario(view);
        }
    }
        }}