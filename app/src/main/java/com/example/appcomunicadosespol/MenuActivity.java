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

public class MenuActivity extends AppCompatActivity {
    private Button botonVerComunicados;
    private Button botonPublicarComunicados;
    private Button botonTableroComunicados;

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
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(MenuActivity.this, VerComunicadosActivity.class);
            startActivity(intent);
        }
    });
    botonPublicarComunicados.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent=new Intent(MenuActivity.this, PublicarComunicadosActivity.class);
            startActivity(intent);
        }
    });
    botonTableroComunicados.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent=new Intent(MenuActivity.this, TableroComunicadosActivity.class);
            startActivity(intent);
        }
    });

    }
}