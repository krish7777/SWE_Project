package com.example.swe_project.ui_org.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.swe_project.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerview;
    HomeAdapter adapter;
    ArrayList<HomeData> items;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_org, container, false);
        initialiseitems();
        recyclerview = root.findViewById(R.id.recyclerViewHome_org);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerview.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(recyclerview);
        adapter = new HomeAdapter(items);
        recyclerview.setAdapter(adapter);

        return root;
    }

    private void initialiseitems() {
        items = new ArrayList<>();
        //here you will have to pull data from server and insert it based on distance from donor in this arraylist
        items.add(new HomeData(R.drawable.home_foodphoto, "Accept Food", "Check out nearby food donations and find one that fits your needs"));

        items.add(new HomeData(R.drawable.home_unwfp, "Find out more", "Connect with UN world food program!"));
        items.add(new HomeData(R.drawable.home_earth, "45", "Number of people fed:"));
    }
}