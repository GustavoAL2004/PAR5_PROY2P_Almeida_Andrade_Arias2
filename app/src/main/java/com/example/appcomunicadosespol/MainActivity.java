package com.example.appcomunicadosespol;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayout_IniciarSesion;
    private EditText editText_Usuario;
    private EditText editText_contraseña;
    private Button button_IniciarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TextView_IniciarSesion), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linearLayout_IniciarSesion=findViewById(R.id.linearLayout_IniciarSesion);
        editText_Usuario=findViewById(R.id.editText_Usuario);
        editText_contraseña=findViewById(R.id.editText_contraseña);
        button_IniciarSesion=findViewById(R.id.button_IniciarSesion);

    }

    public void IniciarSesion(View view){
        String usuario=editText_Usuario.getText().toString();
        String contrasenia=editText_contraseña.getText().toString();

        try (InputStream is = getAssets().open("usuarios.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] listado=linea.split(";");
                String cred_usuario=listado[0];
                String cred_contasenia=listado[1];
                reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}