package com.example.sonic.estaciona2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Sesion extends AppCompatActivity {
    Button Iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        Iniciar=findViewById(R.id.Ingresar);
        Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Sesion.this,MenuPrincipal.class);
                startActivity(in);
            }
        });
    }
}

/*<uses-permission android:name="android.permission.INTERNET" />*/