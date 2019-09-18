package com.example.sonic.estaciona2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CrearCuenta extends AppCompatActivity {

    Button b1;
    EditText us,cor,pas;
    String us1,cor1,pas1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        us=findViewById(R.id.usuar);
        cor=findViewById(R.id.CorreoE);
        pas=findViewById(R.id.Pass);

        b1=findViewById(R.id.Terminar);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                us1=us.getText().toString();
                cor1=cor.getText().toString();
                pas1=pas.getText().toString();
                consumirServicio(us1,cor1,pas1);
                Intent in=new Intent(CrearCuenta.this,MenuPrincipal.class);
                startActivity(in);

            }
        });
    }

    public void consumirServicio(String usuario,String correo,String contra){
        ServicioTask st=new ServicioTask(this,
                "http://192.168.43.48:8181/usr/usuario",usuario,correo,contra);
        st.execute();
    }
}
