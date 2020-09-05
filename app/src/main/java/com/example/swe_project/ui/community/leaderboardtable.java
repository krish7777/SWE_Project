package com.example.swe_project.ui.community;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.swe_project.R;
import com.example.swe_project.VolleySingleton;
import com.example.swe_project.ui.discover.DiscoverAdapter;
import com.example.swe_project.ui.discover.DiscoverData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class leaderboardtable extends AppCompatActivity {

    TableLayout tableLayout;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboardtable);
        getSupportActionBar().setTitle("Leader Board");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        tableLayout=(TableLayout) findViewById(R.id.leaderboard_table);

        TableRow tr_head = new TableRow(this);
//        tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);        // part1
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


        final TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        params.weight=1.0f;
//        params.gravity= Gravity.END;



        TextView label_rank = new TextView(this);
        label_rank.setText("Rank");
        label_rank.setTextColor(Color.WHITE);



        label_rank.setLayoutParams(params);
        label_rank.setGravity(Gravity.CENTER);
                // part2
//        label_rank.setPadding(5, 5, 5, 5);
        tr_head.addView(label_rank);// add the column to the table row here

        TextView label_name = new TextView(this);
        label_name.setText("Name");
        label_name.setTextColor(Color.WHITE);
        label_name.setGravity(Gravity.CENTER);
//        label_name.setPadding(5, 5, 5, 5);
        label_name.setLayoutParams(params);
        tr_head.addView(label_name);


        TextView label_email = new TextView(this);
        label_email.setText("Email");
        label_email.setTextColor(Color.WHITE);
//        label_email.setPadding(5, 5, 5, 5);
        label_email.setGravity(Gravity.CENTER);
        label_email.setLayoutParams(params);
        tr_head.addView(label_email);

        TextView label_people = new TextView(this);
        label_people.setText("People Fed");
        label_people.setTextColor(Color.WHITE);
        label_people.setGravity(Gravity.CENTER);
//        label_people.setPadding(5, 5, 5, 5);
        label_people.setLayoutParams(params);
        tr_head.addView(label_people);

        tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,                    //part4
                TableRow.LayoutParams.WRAP_CONTENT));



        String url = getString(R.string.url)+"/donor/leaderboard";


        //params.put("password",password);
        //params.put("email",email);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Res","evercame");
                try {

                    // Getting JSON Array node

                    JSONArray donors= response.getJSONArray("donors");


                    Log.d("Resp",response.toString());
                    for(int i=0;i<donors.length();i++){
                        JSONObject donor = donors.getJSONObject(i);

                        String email = donor.getString("email");
                        String name = donor.getString("name");
                        String peopleFed= Integer.toString(donor.getInt("peopleFed"));


                        TableRow tr_head = new TableRow(leaderboardtable.this);
                        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));


                        TextView label_rank = new TextView(leaderboardtable.this);
                        label_rank.setText(Integer.toString(i+1));
//                        label_rank.setTextColor(Color.WHITE);

                        label_rank.setLayoutParams(params);
                        label_rank.setGravity(Gravity.CENTER);
                        // part2
        label_rank.setPadding(5, 5, 5, 5);
                        tr_head.addView(label_rank);// add the column to the table row here

                        TextView label_name = new TextView(leaderboardtable.this);
                        label_name.setText(name);
//                        label_name.setTextColor(Color.WHITE);
                        label_name.setGravity(Gravity.CENTER);
        label_name.setPadding(5, 5, 5, 5);
                        label_name.setLayoutParams(params);
                        tr_head.addView(label_name);


                        TextView label_email = new TextView(leaderboardtable.this);
                        label_email.setText(email);
//                        label_email.setTextColor(Color.WHITE);
        label_email.setPadding(5, 5, 5, 5);
                        label_email.setGravity(Gravity.CENTER);
                        label_email.setLayoutParams(params);
                        tr_head.addView(label_email);

                        TextView label_people = new TextView(leaderboardtable.this);
                        label_people.setText(peopleFed);
//                        label_people.setTextColor(Color.WHITE);
                        label_people.setGravity(Gravity.CENTER);
        label_people.setPadding(5, 5, 5, 5);
                        label_people.setLayoutParams(params);
                        tr_head.addView(label_people);

                        tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));


                    }

                } catch (Exception e) {

                    e.printStackTrace();
                    Log.d("Response", "inside error");

                    // Toast.makeText(getApplicationContext(),"Some Error Occured. Please Try Again", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response error", String.valueOf(error));
                // AuthChecker.logout(MainActivity.this);
            }
        }){
            @Override
            public Map<String, String> getHeaders()
            {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String token = sharedPreferences.getString("token","");
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization",token);
                return headers;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);



    }
}