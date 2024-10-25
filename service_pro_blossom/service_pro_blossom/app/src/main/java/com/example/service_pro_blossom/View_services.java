package com.example.service_pro_blossom;

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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class View_services extends AppCompatActivity {
    EditText ed1;
    Button bt1;
    ListView lt1;
    SharedPreferences sh;
    ArrayList<String> servname,servdesc,serid;
//    servname,servdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_services);
        ed1=findViewById(R.id.editTextTextPersonName5);
        bt1=findViewById(R.id.button4);
        lt1=findViewById(R.id.lst);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String url ="http://"+sh.getString("ip", "") + ":5000/view_service_app";
        RequestQueue queue = Volley.newRequestQueue(View_services.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    serid= new ArrayList<>();

                    servname=new ArrayList<>();
                    servdesc=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        serid.add(jo.getString("id"));

                        servname.add(jo.getString("serc_name"));
                        servdesc.add(jo.getString("details"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    lt1.setAdapter(new Custom_view_services(View_services.this,serid,servname,servdesc));



                } catch (Exception e) {
                    Toast.makeText(View_services.this, "---"+e, Toast.LENGTH_SHORT).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(View_services.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("service_centerid", sh.getString("service_centerid",""));

                return params;
            }
        };
        queue.add(stringRequest);


        bt1.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                String servicecenter=ed1.getText().toString();
                String url ="http://"+sh.getString("ip", "") + ":5000/view_service_app_search";
                RequestQueue queue = Volley.newRequestQueue(View_services.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++",response);
                        try {

                            JSONArray ar=new JSONArray(response);
                            serid= new ArrayList<>();

                            servname=new ArrayList<>();
                            servdesc=new ArrayList<>();

                            for(int i=0;i<ar.length();i++)
                            {
                                JSONObject jo=ar.getJSONObject(i);
                                serid.add(jo.getString("id"));

                                servname.add(jo.getString("serc_name"));
                                servdesc.add(jo.getString("details"));


                            }

                            // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                            //lv.setAdapter(ad);

                            lt1.setAdapter(new Custom_view_services(View_services.this,serid,servname,servdesc));



                        } catch (Exception e) {
                            Toast.makeText(View_services.this, "---"+e, Toast.LENGTH_SHORT).show();
                            Log.d("=========", e.toString());
                        }


                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(View_services.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();

                        params.put("service_centerid", sh.getString("service_centerid",""));
                        params.put("name",servicecenter);
                        return params;
                    }
                };
                queue.add(stringRequest);



            }
        });

    }
}