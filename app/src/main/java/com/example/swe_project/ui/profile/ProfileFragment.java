package com.example.swe_project.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swe_project.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    RecyclerView recyclerview;
    profileaAdapter adapter;
    ArrayList<profileData> items;//in this arraylist data is to be loaded from server to be shown in cards
    LinearLayout linearLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_profile, container, false);
        Log.d("profile","first check");
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        Log.d("profile","second check");
        //linearLayout = (LinearLayout) root.findViewById(R.id.linearlayout);

        initialiseitems();
        Log.d("profile","third check");
        recyclerview = root.findViewById(R.id.recyclerViewprofile);
        Log.d("profile","fourth check");
        recyclerview.setLayoutManager(new LinearLayoutManager(root.getContext()));
        Log.d("profile","fifth check");
        adapter = new profileaAdapter(items);
        Log.d("profile","sixth check");
        recyclerview.setAdapter(adapter);
        Log.d("profile","seventh check");

        return root;
    }

    private void initialiseitems() {
        items = new ArrayList<>();
        //here you will have to pull data from server and insert it based on distance from donor in this arraylist
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));

    }
}