package com.example.service_pro_blossom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
EditText edip;
Button btip;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edip=findViewById(R.id.editTextTextPersonName2);
        btip=findViewById(R.id.button2);
//        edip.setText("192.168.29.5");
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        btip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipval=edip.getText().toString();

                if(ipval.equalsIgnoreCase("")){

                }
                SharedPreferences.Editor ed= sh.edit();
                ed.putString("ip",ipval);
                ed.commit();
                Intent i=new Intent(getApplicationContext(),login.class);
                startActivity(i);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


            }
        });



    }
}