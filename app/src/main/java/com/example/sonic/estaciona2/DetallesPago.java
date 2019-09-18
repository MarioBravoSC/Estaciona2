package com.example.sonic.estaciona2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DetallesPago extends AppCompatActivity {

    TextView idPago,Estado,Cantidad,Tiempo;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pago);

        idPago=findViewById(R.id.id_pago);
        Estado=findViewById(R.id.Estatus);
        Cantidad=findViewById(R.id.Monto);
        Tiempo=findViewById(R.id.Time);

        Intent intent = getIntent();
        try{
            JSONObject objeto=new JSONObject(intent.getStringExtra("PaymentDetails"));
            verDetalles(objeto.getJSONObject("response"),intent.getStringExtra("PaymentAmount"),
                    intent.getStringExtra("Time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        back=findViewById(R.id.Regresar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetallesPago.this,MenuPrincipal.class);
                startActivity(intent);
            }
        });

    }

    private void verDetalles(JSONObject response, String paymentAmount, String T1) {
        try{
            idPago.setText(response.getString("id"));
            Estado.setText(response.getString("state"));
            Cantidad.setText("$"+paymentAmount);
            Tiempo.setText(T1);
            ConsumirServicio(response.getString("id"),"$"+paymentAmount,T1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void ConsumirServicio(String descrip,String monto,String clock){
    ServicePagos sp=new ServicePagos(this,"http://192.168.43.48:8181/pago/clientes",descrip,
            monto,clock);
    sp.execute();
    }
}
