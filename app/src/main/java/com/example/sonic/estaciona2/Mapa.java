package com.example.sonic.estaciona2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Mapa extends AppCompatActivity {
ImageView im1,im2,im3,im4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        im1=findViewById(R.id.imv1);
        im2=findViewById(R.id.imv2);
        im3=findViewById(R.id.imv3);
        im4=findViewById(R.id.imv4);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.setImageResource(R.drawable.falso);
                Intent in=new Intent(Mapa.this,PagoPaypal.class);
                startActivity(in);
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im2.setImageResource(R.drawable.falso);
                Intent in=new Intent(Mapa.this,PagoPaypal.class);
                startActivity(in);
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im3.setImageResource(R.drawable.falso);
                Intent in=new Intent(Mapa.this,PagoPaypal.class);
                startActivity(in);
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im4.setImageResource(R.drawable.falso);
                Intent in=new Intent(Mapa.this,PagoPaypal.class);
                startActivity(in);
            }
        });
    }
}
