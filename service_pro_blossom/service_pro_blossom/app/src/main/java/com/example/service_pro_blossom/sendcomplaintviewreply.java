package com.example.service_pro_blossom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class sendcomplaintviewreply extends AppCompatActivity {
EditText ed1;
Button bt1;
ListView lt1;
String url;
    ArrayList<String> cid,reply,comp,date;

    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendcomplaintviewreply);
        ed1=findViewById(R.id.editTextTextPersonName3);
        bt1=findViewById(R.id.button3);
        lt1=findViewById(R.id.lst1);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String url ="http://"+sh.getString("ip", "") + ":5000/view_complaint_app";
        RequestQueue queue = Volley.newRequestQueue(sendcomplaintviewreply.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    cid= new ArrayList<>();
                    comp= new ArrayList<>();
                    reply= new ArrayList<>();
                    date=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        cid.add(jo.getString("id"));
                        comp.add(jo.getString("complaint"));
                        reply.add(jo.getString("reply"));
                        date.add(jo.getString("date"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    lt1.setAdapter(new custom_viewcomplaint(sendcomplaintviewreply.this,cid,comp,reply,date));



                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(sendcomplaintviewreply.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("lid", sh.getString("lid",""));

                return params;
            }
        };
        queue.add(stringRequest);



        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comp=ed1.getText().toString();
                if (comp.equalsIgnoreCase("")){
                    ed1.setError("Missing");
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(sendcomplaintviewreply.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/send_complaint_app";

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
                                    Toast.makeText(sendcomplaintviewreply.this, "Succesfully Sent", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), xx.class);
                                    startActivity(ik);

                                } else {


                                    Toast.makeText(sendcomplaintviewreply.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("Complaint", comp);
                            params.put("uid", sh.getString("lid", ""));

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }



            }
        });
    }
}