package com.example.swe_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","testing git here");
    }

    @Override
    public void onStart(){
        super.onStart();

        //TO MAKE GET OR POST REQUEST WITHOUT PASSING TOKEN

        /*
        String testurl ="http://10.0.2.2:8000/test";
        HashMap<String,String> testparams= new HashMap<String, String>();
        testparams.put("name","Krish");
        JsonObjectRequest testjsonObjectRequest = new JsonObjectRequest(Request.Method.GET,testurl,new JSONObject(testparams), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String yes= response.getString("yes");
                    Log.d("Response",yes);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Response error","error");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response error", String.valueOf(error));

            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(testjsonObjectRequest);

        */

        //TO MAKE GET OR POST REQUEST BY PASSING TOKEN (PROTECTED ROUTES)

        /*
        String url ="http://10.0.2.2:8000/test";
        HashMap<String,String> params= new HashMap<String, String>();
        params.put("name","Krish");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String yes= response.getString("yes");
                    Log.d("Response",yes);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Response","error");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response error", String.valueOf(error));

            }
        }){
            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization","secret token");
                return headers;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
         */
    }
}

