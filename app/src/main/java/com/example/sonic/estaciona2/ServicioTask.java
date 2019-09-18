package com.example.sonic.estaciona2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class ServicioTask extends AsyncTask<Void,Void,String> {
    private Context httpContext;
    ProgressDialog progressDialog;
    public String resultadoapi="";
    public String linkrequestAPI="";
    String usu;
    String mail;
    String password;


    public ServicioTask(Context ctx,String linkAPI,String usuario,String correo,String contraseña){
        this.httpContext=ctx;
        this.linkrequestAPI=linkAPI;
        this.usu=usuario;
        this.mail=correo;
        this.password=contraseña;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=ProgressDialog.show(httpContext,"Procesando Solicitud","por favor espere");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        resultadoapi=s;
        Toast.makeText(httpContext,resultadoapi,Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String resultado=null;
        String wsURL=linkrequestAPI;
        URL url=null;

        try{
            url=new URL(wsURL);
            HttpURLConnection Conexion= (HttpURLConnection) url.openConnection();
            JSONObject parametropost=new JSONObject();
            parametropost.put("nombre",usu);
            parametropost.put("correo",mail);
            parametropost.put("contraseña",password);

            Conexion.setReadTimeout(15000);
            Conexion.setConnectTimeout(15000);
            Conexion.setRequestMethod("POST");
            Conexion.setDoInput(true);
            Conexion.setDoOutput(true);

            OutputStream os=Conexion.getOutputStream();
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(getPostDataString(parametropost));
            writer.flush();
            writer.close();
            os.close();

            int responseCode=Conexion.getResponseCode();
            if(responseCode==HttpURLConnection.HTTP_OK){
                BufferedReader in=new BufferedReader(new InputStreamReader(Conexion.getInputStream()));
                StringBuffer sb=new StringBuffer("");
                String linea="";
                while((linea=in.readLine())!=null){
                    sb.append(linea);
                    break;
                }
                in.close();
                resultado=sb.toString();
            }else{
             resultado=new String("Error: "+responseCode);
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}
