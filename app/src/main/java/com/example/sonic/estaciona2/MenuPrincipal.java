package com.example.sonic.estaciona2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    Button Mapa1,Pagare,Historial,CSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Pagare=findViewById(R.id.Pago);
        Pagare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1=new Intent(MenuPrincipal.this,PagoPaypal.class);
                startActivity(in1);
            }
        });

        Historial=findViewById(R.id.HP);
        Historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2=new Intent(MenuPrincipal.this,Historial.class);
                startActivity(in2);
            }
        });

        CSesion=findViewById(R.id.CerrarSesion);
        CSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in3=new Intent(MenuPrincipal.this,InicioSesion.class);
                startActivity(in3);
            }
        });
        Mapa1=findViewById(R.id.Map);
        Mapa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in4=new Intent(MenuPrincipal.this,Mapa.class);
                startActivity(in4);
            }
        });
    }

}
