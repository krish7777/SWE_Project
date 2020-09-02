package com.example.swe_project.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swe_project.R;

import java.util.ArrayList;

public class activitesFragment extends Fragment{

    RecyclerView recyclerview;
    activitesAdapter adapter;
    ArrayList<activitesData> items;//in this arraylist data is to be loaded from server to be shown in cards

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.communityevents, container, false);
        initialiseitems();
        recyclerview = root.findViewById(R.id.recyclerViewactivities);
        recyclerview.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new activitesAdapter(items);
        recyclerview.setAdapter(adapter);
        return root;
    }

    private void initialiseitems() {
        items = new ArrayList<>();
        //here you will have to pull data from server and insert it based on distance from donor in this arraylist
        items.add(new activitesData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new activitesData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new activitesData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new activitesData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new activitesData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new activitesData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new activitesData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
    }
}