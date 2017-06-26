package com.example.lucatosto.smarthome;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.view.View.OnClickListener;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements SensorEventListener, OnClickListener {

    //private SensorManager mSensorManager;
    private Sensor sensor;
    private Sensor sensor2;
/*

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //invia();   //send object to cloud MQTT



        final Button button = (Button)findViewById(R.id.invia);
        button.setOnClickListener(this);

    }



   /* public void invia(){
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor2 = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        System.out.println("sensore1  -------------------->>>>" + sensor);

        System.out.println("sensore2  -------------------->>>>" + sensor2);


        System.out.println("val: "+ sensor.getName());



        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://arcane-retreat-62277.herokuapp.com/test";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("HO FINITO : -------------->>>>>>>>>>>>>>>"+ response.substring(0,21));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);



    }*/


    @Override
    public void onSensorChanged(SensorEvent event)
    {
        /*
         * Codice di gestione dei nuovi eventi del sensore
         * */
    }

    @Override
    public void onAccuracyChanged(Sensor s, int i)
    {

    }

    @Override
    public void onClick(View v) {
        EditText nome = (EditText)findViewById(R.id.nome);
        final String a = nome.getText().toString();

        EditText cognome = (EditText)findViewById(R.id.cognome);
        final String b = cognome.getText().toString();

        System.out.println("Nome: ---> "+a);
        System.out.println("Cognome:  --->"+b);



        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://arcane-retreat-62277.herokuapp.com/test";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        System.out.println("Response: --->"+response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        // Log.d("Error.Response", response);
                        System.out.println("Errore");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", a);
                params.put("surname", b);

                return params;
            }
        };
        queue.add(postRequest);
    }
}
