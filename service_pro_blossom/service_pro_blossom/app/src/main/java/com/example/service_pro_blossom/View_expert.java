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

public class View_expert extends AppCompatActivity {
EditText ed1;
Button bt1;
ListView gd1;
    SharedPreferences sh;
    ArrayList<String> eid,ename,eemail,eplace,ephone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expert);
        ed1=findViewById(R.id.editTextTextPersonName8);
        bt1=findViewById(R.id.button16);
        gd1=findViewById(R.id.gdv);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String url ="http://"+sh.getString("ip", "") + ":5000/view_expert_app";
        RequestQueue queue = Volley.newRequestQueue(View_expert.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    eid= new ArrayList<>();
                    ename= new ArrayList<>();
                    eemail= new ArrayList<>();
                    eplace=new ArrayList<>();
                    ephone=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        eid.add(jo.getString("id"));
                        ename.add(jo.getString("expname"));
                        eemail.add(jo.getString("expemail"));
                        eplace.add(jo.getString("expplace"));
                        ephone.add(jo.getString("expphone"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    gd1.setAdapter(new custom_viewexpert(View_expert.this,eid,ename,eemail,eplace,ephone));



                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(View_expert.this, "err"+error, Toast.LENGTH_SHORT).show();
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
                String expert=ed1.getText().toString();

                String url ="http://"+sh.getString("ip", "") + ":5000/view_expert_app_search";
                RequestQueue queue = Volley.newRequestQueue(View_expert.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++",response);
                        try {

                            JSONArray ar=new JSONArray(response);
                            eid= new ArrayList<>();
                            ename= new ArrayList<>();
                            eemail= new ArrayList<>();
                            eplace=new ArrayList<>();
                            ephone=new ArrayList<>();

                            for(int i=0;i<ar.length();i++)
                            {
                                JSONObject jo=ar.getJSONObject(i);
                                eid.add(jo.getString("id"));
                                ename.add(jo.getString("expname"));
                                eemail.add(jo.getString("expemail"));
                                eplace.add(jo.getString("expplace"));
                                ephone.add(jo.getString("expphone"));


                            }

                            // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                            //lv.setAdapter(ad);

                            gd1.setAdapter(new custom_viewexpert(View_expert.this,eid,ename,eemail,eplace,ephone));



                        } catch (Exception e) {
                            Log.d("=========", e.toString());
                        }


                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(View_expert.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();

                        params.put("lid", sh.getString("lid",""));
                        params.put("name",expert);

                        return params;
                    }
                };
                queue.add(stringRequest);



            }
        });

    }
}