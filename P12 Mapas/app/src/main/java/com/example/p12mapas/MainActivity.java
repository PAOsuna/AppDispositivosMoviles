package com.example.p12mapas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSitios;
    private Button btnTipos;
    private Button btnUbicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSitios=findViewById(R.id.btn_sitios);
        btnTipos=findViewById(R.id.btn_tipos);
        btnUbicacion=findViewById(R.id.btn_ubicacion);
        btnSitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });
    }
    public void MapaSitios(View view){
        Intent intent= new Intent(getApplicationContext(),MapsActivityTipos.class);
        startActivity(intent);
    }
    public void miLocalizacion(View view){
        Intent i = new Intent(getApplicationContext(),MapsMiLocalizacion.class);
        startActivity(i);
    }
}