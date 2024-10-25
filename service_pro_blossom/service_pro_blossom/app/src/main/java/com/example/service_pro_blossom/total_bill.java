package com.example.service_pro_blossom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class total_bill extends AppCompatActivity {

    TextView tv;
    Button bt;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_bill);
        tv=findViewById(R.id.textView24);
        bt=findViewById(R.id.button18);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),PaymentActivity.class));

            }
        });
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        RequestQueue queue = Volley.newRequestQueue(total_bill.this);
        String url = "http://" + sh.getString("ip", "") + ":5000/view_bill";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    JSONObject json = new JSONObject(response);
                    String res = json.getString("task");

                    if (res.equalsIgnoreCase("valid")) {
                        String am = json.getString("amount");
//                        String oid = json.getString("amount");
//                        String lid = json.getString("id");
                        SharedPreferences.Editor edp = sh.edit();
                        edp.putString("amt", am);
                        edp.putString("oid", sh.getString("requestid",""));
                        edp.commit();
                        tv.setText(am+" Rs/-");
//                        bt.setVisibility(View.VISIBLE);


                        String st=sh.getString("st","");
                        Toast.makeText(total_bill.this, "---"+st, Toast.LENGTH_SHORT).show();

                        if(st.equalsIgnoreCase("paid")){
                            Toast.makeText(total_bill.this, "------------Enter", Toast.LENGTH_SHORT).show();
                            bt.setVisibility(View.INVISIBLE);
                        }
                        else {
                            Toast.makeText(total_bill.this, "else===", Toast.LENGTH_SHORT).show();
                            bt.setVisibility(View.VISIBLE);
                        }

                    } else {
                        tv.setText("AMOUNT IS NOT UPDATED");
                        bt.setVisibility(View.INVISIBLE);
                        Toast.makeText(total_bill.this, "AMOUNT IS NOT UPDATED", Toast.LENGTH_SHORT).show();

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
                params.put("rid", sh.getString("requestid",""));

                return params;
            }
        };
        queue.add(stringRequest);

    }
}