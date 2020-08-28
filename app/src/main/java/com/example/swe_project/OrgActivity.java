package com.example.swe_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class OrgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("organ", "first");
        setContentView(R.layout.activity_org);
        BottomNavigationView navView = findViewById(R.id.nav_view_org);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home_org, R.id.navigation_discover_org, R.id.navigation_community_org, R.id.navigation_profile_org)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_org);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(AuthChecker.authChecker(this).length()==0){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}