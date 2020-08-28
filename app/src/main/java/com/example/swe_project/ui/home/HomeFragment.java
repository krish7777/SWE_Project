package com.example.swe_project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.swe_project.R;
import com.example.swe_project.ui.home.HomeAdapter;
import com.example.swe_project.ui.home.HomeData;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerview;
    HomeAdapter adapter;
    ArrayList<HomeData> items;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initialiseitems();
        recyclerview = root.findViewById(R.id.recyclerViewHome);
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
        items.add(new HomeData(R.drawable.home_foodphoto, "Donate Food", "Don't waste food! Help us reach our goal of zero hunger"));
        items.add(new HomeData(R.drawable.home_mealphoto, "Donate Money", "A donation as low as $1 gets someone a meal"));
        items.add(new HomeData(R.drawable.home_unwfp, "Find out more", "Feed people in need around the world, contribute to United Nations World  Food Programme"));
    }
}