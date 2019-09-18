package com.example.sonic.estaciona2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sonic.estaciona2.ConfigPaypal.Paypal;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class PagoPaypal extends AppCompatActivity {
    /*Codigo de requerido para procesar pago*/
    private static final  int PAYPAL_REQUEST_CODE=7171;


    /*Procesa pagos a una cuenta sandbox(esto es solo para pruebas y
    no genera cargos reales a una cuenta real de paypal)*/
    private static PayPalConfiguration config=new PayPalConfiguration().
            environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
    .clientId(Paypal.PAYPAL_CLIENT_ID);/*Esto es para utilizar la cuenta sandbox creada*/

    Button Pagar1;
    Chronometer crom;
    TextView precio;
    double Time=0;
    Thread hilosegundos;
    boolean cronoactivo=true;
    double pago=15,total=0;


    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_paypal);

        /*Inicia el servicio de paypal*/
        Intent in1=new Intent(this,PayPalService.class);
        in1.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(in1);

        crom=findViewById(R.id.cronometro);
        crom.setBase(SystemClock.elapsedRealtime());
        crom.start();

        precio=findViewById(R.id.PrecioA);

        hilosegundos=new Thread(new Runnable() {
            @Override
            public void run() {
                while(cronoactivo){
                    try {
                        hilosegundos.sleep(60000);
                        Time++;
                        total=pago+total;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                precio.setText(total+"");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        hilosegundos.start();

        Pagar1=findViewById(R.id.Pagar);
        Pagar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cronoactivo=false;
                hilosegundos.interrupt();
                ProcesarPago(""+total);
            }
        });
    }

    private void ProcesarPago(String monto){

        /*Recibe monto a pagar*/
        PayPalPayment ppp=new PayPalPayment(new BigDecimal(String.valueOf(monto)),
                "MXN","Pagado por EstacionaME",PayPalPayment.PAYMENT_INTENT_AUTHORIZE);

        /*Envia los parametros*/
        Intent in= new Intent(this,PaymentActivity.class);
        in.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        in.putExtra(PaymentActivity.EXTRA_PAYMENT,ppp);
        startActivityForResult(in,PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PAYPAL_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                PaymentConfirmation confirmation=data.getParcelableExtra(PaymentActivity.
                        EXTRA_RESULT_CONFIRMATION);

                if(confirmation!=null){
                    try{
                      String paymentdetails=confirmation.toJSONObject().toString(4);
                      Intent in3 =new Intent(PagoPaypal.this,DetallesPago.class)
                              .putExtra("PaymentDetails",paymentdetails)
                              .putExtra("PaymentAmount",total+"")
                              .putExtra("Time",crom.getText().toString());
                      startActivity(in3);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }else if(resultCode==Activity.RESULT_CANCELED){
            Toast.makeText(this,"Cancelada",Toast.LENGTH_SHORT).show();
        }else if(resultCode==PaymentActivity.RESULT_EXTRAS_INVALID){
            Toast.makeText(this,"invalida",Toast.LENGTH_SHORT).show();;
        }
    }


}

