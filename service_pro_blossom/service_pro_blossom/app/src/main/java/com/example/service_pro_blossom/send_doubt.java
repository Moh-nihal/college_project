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

public class send_doubt extends AppCompatActivity {

    EditText ed1;
    Button bt1;
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> did,dob,reply,date;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(),xx.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_doubt);
        ed1 = findViewById(R.id.editTextTextPersonName3);
        bt1 = findViewById(R.id.button3);
        l1 = findViewById(R.id.lst1);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        String url ="http://"+sh.getString("ip", "") + ":5000/view_reply_app";
        RequestQueue queue = Volley.newRequestQueue(send_doubt.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    did= new ArrayList<>();
                    dob= new ArrayList<>();
                    reply= new ArrayList<>();
                    date=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        did.add(jo.getString("id"));
                        dob.add(jo.getString("doubts"));
                        reply.add(jo.getString("reply"));
                        date.add(jo.getString("date"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom_view_doubt(send_doubt.this,did,dob,reply,date));



                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(send_doubt.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("lid", sh.getString("lid",""));
                params.put("expertid", sh.getString("expertid",""));


                return params;
            }
        };
        queue.add(stringRequest);




        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doub=ed1.getText().toString();

                if(doub.equalsIgnoreCase("")){
                    ed1.setError("Missing");
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(send_doubt.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/ask_doubt_app";

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
                                    Toast.makeText(send_doubt.this, "Succesfully Sent", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), send_doubt.class);
                                    startActivity(ik);

                                } else {


                                    Toast.makeText(send_doubt.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("Doubts", doub);
                            params.put("uid", sh.getString("lid", ""));
                            params.put("expertid", sh.getString("expertid", ""));

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }
            }
        });


    }
}