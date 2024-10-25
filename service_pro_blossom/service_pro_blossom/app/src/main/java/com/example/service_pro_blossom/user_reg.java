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

import java.util.HashMap;
import java.util.Map;


public class user_reg extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6,regname,regpassword;
    Button bt1;
    String url;
    SharedPreferences sh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        ed1=findViewById(R.id.editTextTextPersonName8);
        ed2=findViewById(R.id.editTextTextPersonName12);
        ed3=findViewById(R.id.editTextTextPersonName13);
        ed4=findViewById(R.id.editTextTextPersonName14);
        ed5=findViewById(R.id.editTextTextPersonName15);
        ed6=findViewById(R.id.editTextTextPersonName16);
        regname=findViewById(R.id.usr);
        regpassword=findViewById((R.id.editTextTextPersonName18));
        bt1=findViewById(R.id.button5);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=ed1.getText().toString();
                String place=ed2.getText().toString();
                String post=ed3.getText().toString();
                String pin=ed4.getText().toString();
                String phone=ed5.getText().toString();
                String email=ed6.getText().toString();
                String username=regname.getText().toString();
                String password=regpassword.getText().toString();
                if(name.equalsIgnoreCase("")){
                    ed1.setError("Missing");
                } else if(place.equalsIgnoreCase("")){
                    ed2.setError("Missing");
                }
                else if(post.equalsIgnoreCase("")){
                    ed4.setError("Missing");
                }
                else if(pin.equalsIgnoreCase("")){
                    ed4.setError("Missing");
                }
                else if(phone.equalsIgnoreCase("")){
                    ed5.setError("Missing");
                }
                else if(email.equalsIgnoreCase("")){
                    ed6.setError("Missing");
                }
                else if(username.equalsIgnoreCase("")){
                    regname.setError("Missing");
                }
                else if(password.equalsIgnoreCase("")){
                    regpassword.setError("Missing");
                }
                else if(!(pin.length() ==6)){
                    ed4.setError("Must Valid pin");
                }else if(!(phone.length() ==10)){
                    ed5.setError("Must be 10");
                }
                else {


                    RequestQueue queue = Volley.newRequestQueue(user_reg.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/registration";

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
                                    Toast.makeText(user_reg.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), login.class);
                                    startActivity(ik);

                                } else {

                                    Toast.makeText(user_reg.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("Firstname", name);
                            params.put("Place", place);
                            params.put("Post", post);
                            params.put("Pin", pin);
                            params.put("Phone", phone);
                            params.put("Email", email);
                            params.put("Username", username);
                            params.put("Password", password);

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }

            }
        });
    }
}