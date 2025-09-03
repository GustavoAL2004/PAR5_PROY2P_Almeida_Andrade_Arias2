package com.example.appcomunicadosespol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * La clase MenuActivity representa la pantalla principal del menú,
 * donde el usuario puede seleccionar entre:
 * Ver comunicados, Publicar comunicado o Tablero Comunicados.
 */


public class MenuActivity extends AppCompatActivity {
    private Button botonVerComunicados;
    private Button botonPublicarComunicados;
    private Button botonTableroComunicados;

    /**
     * Se ejecuta cuando Android crea la actividad e inicializa los elementos de la interfaz.
     *
     * @param savedInstanceState Información guardada anteriormente de la actividad.
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    botonVerComunicados=findViewById(R.id.buttonVerComunicados);
    botonPublicarComunicados=findViewById(R.id.buttonPublicarComunicados);
    botonTableroComunicados=findViewById(R.id.buttonTableroComunicados);

    botonVerComunicados.setOnClickListener(new View.OnClickListener() {

        /**
         * Dirige a la pantalla "Ver Comunicados" cuando se da clic en el botón.
         *
         * @param v Vista que realiza la acción al momento de hacer clic.
         */

        @Override
        public void onClick(View v) {
            Intent intent =new Intent(MenuActivity.this, VerComunicadosActivity.class);
            startActivity(intent);
        }
    });
    botonPublicarComunicados.setOnClickListener(new View.OnClickListener(){

        /**
         * Dirige a la pantalla "Publicar Comunicados" cuando se da clic en el botón.
         *
         * @param v Vista que realiza la acción al momento de hacer clic.
         */

        @Override
        public void onClick(View v){
            Intent intent=new Intent(MenuActivity.this, PublicarComunicadosActivity.class);
            startActivity(intent);
        }
    });
    botonTableroComunicados.setOnClickListener(new View.OnClickListener(){

        /**
         * Dirige a la pantalla "Tablero Comunicados" cuando se da clic en el botón.
         *
         * @param v Vista que realiza la acción al momento de hacer clic.
         */

        @Override
        public void onClick(View v){
            Intent intent=new Intent(MenuActivity.this, TableroComunicadosActivity.class);
            startActivity(intent);
        }
    });

    }
}