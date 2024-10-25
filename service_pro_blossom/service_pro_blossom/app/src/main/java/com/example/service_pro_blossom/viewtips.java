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

public class viewtips extends AppCompatActivity {
EditText ed1;
Button bt1;
ListView lst1;
    SharedPreferences sh;
    ArrayList<String> tip,tipdet,exname,extemail,expplace,tipid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtips);
        ed1=findViewById(R.id.editTextTextPersonName7);
        bt1=findViewById(R.id.button6);
        lst1=findViewById(R.id.lst);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String url ="http://"+sh.getString("ip", "") + ":5000/view_tips_app";
        RequestQueue queue = Volley.newRequestQueue(viewtips.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    tip= new ArrayList<>();
                    tipdet= new ArrayList<>();
                    exname= new ArrayList<>();
                    extemail=new ArrayList<>();
                    expplace=new ArrayList<>();
                    tipid=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        tip.add(jo.getString("tips"));
                        tipdet.add(jo.getString("details"));
                        exname.add(jo.getString("expertname"));
                        extemail.add(jo.getString("expertemail"));
                        expplace.add(jo.getString("expertplace"));
                        tipid.add(jo.getString("id"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    lst1.setAdapter(new Custom_viewtips(viewtips.this,tip,tipdet,exname,extemail,expplace,tipid));



                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewtips.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue.add(stringRequest);




        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tips=ed1.getText().toString();
                String url ="http://"+sh.getString("ip", "") + ":5000/view_tips_app_search";
                RequestQueue queue = Volley.newRequestQueue(viewtips.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++",response);
                        try {

                            JSONArray ar=new JSONArray(response);
                            tip= new ArrayList<>();
                            tipdet= new ArrayList<>();
                            exname= new ArrayList<>();
                            extemail=new ArrayList<>();
                            expplace=new ArrayList<>();
                            tipid=new ArrayList<>();

                            for(int i=0;i<ar.length();i++)
                            {
                                JSONObject jo=ar.getJSONObject(i);
                                tip.add(jo.getString("tips"));
                                tipdet.add(jo.getString("details"));
                                exname.add(jo.getString("expertname"));
                                extemail.add(jo.getString("expertemail"));
                                expplace.add(jo.getString("expertplace"));
                                tipid.add(jo.getString("id"));


                            }

                            // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                            //lv.setAdapter(ad);

                            lst1.setAdapter(new Custom_viewtips(viewtips.this,tip,tipdet,exname,extemail,expplace,tipid));



                        } catch (Exception e) {
                            Log.d("=========", e.toString());
                        }


                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(viewtips.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                    params.put("name",tips);
                        return params;
                    }
                };
                queue.add(stringRequest);


            }
        });
    }
}