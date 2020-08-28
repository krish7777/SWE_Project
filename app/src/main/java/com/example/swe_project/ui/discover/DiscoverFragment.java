package com.example.swe_project.ui.discover;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.swe_project.AuthChecker;
import com.example.swe_project.MainActivity;

import com.example.swe_project.R;
import com.example.swe_project.UserActivity;
import com.example.swe_project.VolleySingleton;
import com.example.swe_project.organisation_login_page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiscoverFragment extends Fragment {

    RecyclerView recyclerview;
    DiscoverAdapter adapter;
    ArrayList<DiscoverData> items;//in this arraylist data is to be loaded from server to be shown in cards
    JSONArray organisations;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discover, container, false);
        String url = getString(R.string.url)+"/donor/nearbyorganisations";
        final HashMap<String, String> params = new HashMap<String, String>();
        items = new ArrayList<>();
        recyclerview = root.findViewById(R.id.recyclerViewdiscover);
        recyclerview.setLayoutManager(new LinearLayoutManager(root.getContext()));

        //params.put("password",password);
        //params.put("email",email);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Res","evercame");
                try {

                    // Getting JSON Array node

                    organisations= response.getJSONArray("organisations");

                    for(int i=0;i<organisations.length();i++){
                        JSONObject organisation = organisations.getJSONObject(i);

                        items.add(new DiscoverData(organisation.getString("name"), "5 km", organisation.getString("email"),
                                "9154862562", organisation.getString("address")));
                        items.add(new DiscoverData("check name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));

                        String id = organisation.getString("email");
                        Log.d("Response",id);
                    }
                    initialiseitems();


                    adapter = new DiscoverAdapter(items);
                    recyclerview.setAdapter(adapter);

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
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                String token = sharedPreferences.getString("token","");
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization",token);
                return headers;
            }
        };
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonObjectRequest);


        return root;
    }


    private void initialiseitems() {
        //here you will have to pull data from server and insert it based on distance from donor in this arraylist

        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new DiscoverData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));

        //here you will have to pull data from server and insert it based on distance from donor in this arraylist

    }
}