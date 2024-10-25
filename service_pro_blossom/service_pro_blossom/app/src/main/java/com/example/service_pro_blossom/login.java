package com.example.service_pro_blossom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class login extends AppCompatActivity {
EditText eduname,edpassword;
Button btlogin;
TextView  tx1;
String url;
SharedPreferences sh;
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(),login.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eduname=findViewById(R.id.editText);
        edpassword=findViewById(R.id.editText2);
        btlogin=findViewById(R.id.button);
        tx1=findViewById(R.id.textView3);
//        eduname.setText("nihal2");
//        edpassword.setText("123");
        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg=new Intent(getApplicationContext(),user_reg.class);
                startActivity(reg);
            }
        });

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=eduname.getText().toString();
                String password=edpassword.getText().toString();
                if(username.equalsIgnoreCase("")){
                    eduname.setError("Misiing");
                }
                else if(password.equalsIgnoreCase("")){
                    edpassword.setError("Missing");
                }
                else {


                    RequestQueue queue = Volley.newRequestQueue(login.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/login_code";

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
                                    String lid = json.getString("id");
                                    SharedPreferences.Editor edp = sh.edit();
                                    edp.putString("lid", lid);
                                    edp.commit();
                                    Toast.makeText(login.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    startService(new Intent(getApplicationContext(), LocationService.class));
                                    Intent ik = new Intent(getApplicationContext(), xx.class);
                                    startActivity(ik);

                                } else {

                                    Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("uname", username);
                            params.put("pass", password);

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }





            }
        });
    }
}