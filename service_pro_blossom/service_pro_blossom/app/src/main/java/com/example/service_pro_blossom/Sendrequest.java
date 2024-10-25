package com.example.service_pro_blossom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sendrequest extends AppCompatActivity {
    EditText pl;
    Button bn1;
    SharedPreferences sh;
    ArrayList<String> rid,services,cenname,reqst,date,status;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(),xx.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendrequest);
        pl=findViewById(R.id.editTextTextPersonName9);
        bn1=findViewById(R.id.button8);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());









        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String request=pl.getText().toString();

                if (request.equalsIgnoreCase("")){
                    pl.setError("Missing");
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(Sendrequest.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/service_req_app";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("success")) {
                                    Toast.makeText(Sendrequest.this, "Succesfully Sent", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), xx.class);
                                    startActivity(ik);

                                } else {


                                    Toast.makeText(Sendrequest.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("serviceid", sh.getString("service_id", ""));
                            params.put("Request", request);
                            params.put("uid", sh.getString("lid", ""));

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }


            }
        });
}}