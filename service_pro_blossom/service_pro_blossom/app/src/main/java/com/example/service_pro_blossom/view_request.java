package com.example.service_pro_blossom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.util.Log;
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

public class view_request extends AppCompatActivity {
    ArrayList<String> rid,services,cenname,reqst,date,status;
    SharedPreferences sh;
ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);
        lst=findViewById(R.id.lst);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        String url ="http://"+sh.getString("ip", "") + ":5000/view_service_req_app";
        RequestQueue queue = Volley.newRequestQueue(view_request.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    rid= new ArrayList<>();
                    services= new ArrayList<>();
                    cenname= new ArrayList<>();
                    reqst= new ArrayList<>();
                    date=new ArrayList<>();
                    status=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        rid.add(jo.getString("id"));
                        services.add(jo.getString("servicename"));
                        cenname.add(jo.getString("centername"));
                        reqst.add(jo.getString("req"));
                        date.add(jo.getString("date"));
                        status.add(jo.getString("stat"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    lst.setAdapter(new custom_view_request(view_request.this,rid,services,cenname,reqst,date,status));



                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_request.this, "err"+error, Toast.LENGTH_SHORT).show();
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
    }
}