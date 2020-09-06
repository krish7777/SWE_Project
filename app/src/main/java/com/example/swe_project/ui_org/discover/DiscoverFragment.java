package com.example.swe_project.ui_org.discover;

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
import com.example.swe_project.R;
import com.example.swe_project.VolleySingleton;
import com.example.swe_project.ui_org.discover.DiscoverAdapter;
import com.example.swe_project.ui_org.discover.DiscoverData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiscoverFragment extends Fragment {

    RecyclerView recyclerview;
    DiscoverAdapter adapter;
    ArrayList<DiscoverData> items;//in this arraylist data is to be loaded from server to be shown in cards

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discover_org, container, false);

        String url= getString(R.string.url)+"/organisation/nearby-donations";
        final HashMap<String, String> params = new HashMap<String, String>();
        items = new ArrayList<>();



        //initialiseitems();
        recyclerview = root.findViewById(R.id.recyclerViewdiscover_org);
        recyclerview.setLayoutManager(new LinearLayoutManager(root.getContext()));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Res","evercame");
                try {

                    // Getting JSON Array node

                    JSONArray donations = response.getJSONArray("donations");

                    for(int i=0;i<donations.length();i++){
                        JSONObject donation = donations.getJSONObject(i);
                        Double distance = donation.getDouble("distance");
                        distance=distance/1000;

//                        int dis= Integer.parseInt(distance);
//                        dis=dis/1000;

                        items.add(new DiscoverData(donation.getString("_id"),donation.getString("donor"),donation.getString("donorName"),Math.round(distance)+" km", " ",
                                donation.getString("donorContact"), donation.getString("description")));
                        //items.add(new DiscoverData("check name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));


                    }
                    // initialiseitems();


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




//        adapter = new DiscoverAdapter(items);
//        recyclerview.setAdapter(adapter);



        return root;

    }

    private void initialiseitems() {
        items = new ArrayList<>();
        //here you will have to pull data from server and insert it based on distance from donor in this arraylist
        items.add(new DiscoverData("1","1","FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("2","1","FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("3","1","FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("4","1","FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("5","1","FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("6","1","FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("7","1","FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
    }
}