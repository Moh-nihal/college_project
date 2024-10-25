package com.example.service_pro_blossom;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service_pro_blossom.databinding.ActivityXxBinding;

public class xx extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityXxBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityXxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarXx.toolbar);
        binding.appBarXx.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_xx);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.xx, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_xx);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.nav_center)
        {
            startActivity(new Intent(getApplicationContext(),searchservicecenter.class));
        }else if(id==R.id.nav_expert)
        {
            startActivity(new Intent(getApplicationContext(),View_expert.class));
        }else if(id==R.id.nav_tip)
        {
            startActivity(new Intent(getApplicationContext(),viewtips.class));
        }else if(id==R.id.nav_complants)
        {
            startActivity(new Intent(getApplicationContext(),sendcomplaintviewreply.class));
        }else if(id==R.id.nav_feedback)
        {
            startActivity(new Intent(getApplicationContext(),sendfeedbackrating.class));
        }else if(id==R.id.nav_chatbot)
        {
            startActivity(new Intent(getApplicationContext(),Test.class));
        }else if(id==R.id.nav_logout)
        {
            startActivity(new Intent(getApplicationContext(),login.class));
        }else if(id==R.id.nav_request)
        {
            startActivity(new Intent(getApplicationContext(),view_request.class));
        }
        return true;
    }
}