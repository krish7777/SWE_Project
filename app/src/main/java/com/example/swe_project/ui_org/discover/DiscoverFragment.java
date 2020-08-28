package com.example.swe_project.ui_org.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swe_project.R;
import com.example.swe_project.ui_org.discover.DiscoverAdapter;
import com.example.swe_project.ui_org.discover.DiscoverData;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment {

    RecyclerView recyclerview;
    DiscoverAdapter adapter;
    ArrayList<DiscoverData> items;//in this arraylist data is to be loaded from server to be shown in cards

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discover_org, container, false);
        initialiseitems();
        recyclerview = root.findViewById(R.id.recyclerViewdiscover_org);
        recyclerview.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new DiscoverAdapter(items);
        recyclerview.setAdapter(adapter);
        return root;
    }

    private void initialiseitems() {
        items = new ArrayList<>();
        //here you will have to pull data from server and insert it based on distance from donor in this arraylist
        items.add(new DiscoverData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new DiscoverData("FOOD DONATION", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
    }
}