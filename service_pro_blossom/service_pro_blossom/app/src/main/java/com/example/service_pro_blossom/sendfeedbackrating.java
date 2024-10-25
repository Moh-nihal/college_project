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
import android.widget.RatingBar;
import android.widget.TextView;
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

public class sendfeedbackrating extends AppCompatActivity {
RatingBar rb1;
TextView tx1;
EditText ed1;
Button bt1;
ListView lst;
    String url;
    ArrayList<String> fid,feed,rtn,date;
    SharedPreferences sh;
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(),xx.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendfeedbackrating);
        rb1=findViewById(R.id.ratingBar);
        tx1=findViewById(R.id.textView2);
        ed1=findViewById(R.id.editTextTextPersonName6);
        bt1=findViewById(R.id.button5);
        lst=findViewById(R.id.lst2);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        String url ="http://"+sh.getString("ip", "") + ":5000/view_feedback_app";
        RequestQueue queue = Volley.newRequestQueue(sendfeedbackrating.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    fid= new ArrayList<>();
                    feed= new ArrayList<>();
                    rtn= new ArrayList<>();
                    date=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        fid.add(jo.getString("id"));
                        feed.add(jo.getString("feedback"));
                        rtn.add(jo.getString("rating"));
                        date.add(jo.getString("date"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    lst.setAdapter(new custom_viewfeedback(sendfeedbackrating.this,fid,feed,rtn,date));



                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(sendfeedbackrating.this, "err"+error, Toast.LENGTH_SHORT).show();
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
                String feedback=ed1.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(sendfeedbackrating.this);
                String url = "http://" + sh.getString("ip","") + ":5000/feedback_app";

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
                                Toast.makeText(sendfeedbackrating.this, "Succesfully Sent", Toast.LENGTH_SHORT).show();
                                Intent ik = new Intent(getApplicationContext(), xx.class);
                                startActivity(ik);

                            } else {


                                Toast.makeText(sendfeedbackrating.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                        params.put("Feedback", feedback);
                        params.put("rating", String.valueOf(rb1.getRating()));
                        params.put("lid", sh.getString("lid",""));

                        return params;
                    }
                };
                queue.add(stringRequest);

            }
        });

    }
}