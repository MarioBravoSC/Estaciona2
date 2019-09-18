package com.example.sonic.estaciona2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Historial extends AppCompatActivity implements Response.ErrorListener,
        Response.Listener<JSONObject> {

    TextView tx;

    private RequestQueue req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        req = Volley.newRequestQueue(this);
        ObtenerDatos();

    }

    private void ObtenerDatos() {
        String url = "http://192.168.0.4:8181/pago/listar";
        JsonObjectRequest re = new JsonObjectRequest(Request.Method.GET, url, null,
                this, this);
        req.add(re);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray jar = response.getJSONArray("");
            for (int i = 0; i < jar.length(); i++) {
                JSONObject job = jar.getJSONObject(i);
                String desc = job.getString("descripcion");
                Toast.makeText(Historial.this, "descripcion:" + desc, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
