package com.example.swe_project.ui_org.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swe_project.AuthChecker;
import com.example.swe_project.MainActivity;
import com.example.swe_project.R;
import com.example.swe_project.ui_org.discover.DiscoverData;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    RecyclerView recyclerview;
    profileaAdapter adapter;
    ArrayList<profileData> items;//in this arraylist data is to be loaded from server to be shown in cards
    LinearLayout linearLayout;

    private Button logoutButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_profile, container, false);

        View root = inflater.inflate(R.layout.fragment_profile_org, container, false);

        //linearLayout = (LinearLayout) root.findViewById(R.id.linearlayout);

        initialiseitems();

        recyclerview = root.findViewById(R.id.recyclerViewprofile_org);

        recyclerview.setLayoutManager(new LinearLayoutManager(root.getContext()));

        adapter = new profileaAdapter(items);

        recyclerview.setAdapter(adapter);

        logoutButton=(Button) root.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        return root;
    }


    public void logout() {
        AuthChecker.logout(getActivity());
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
    private void initialiseitems() {
        items = new ArrayList<>();
        //here you will have to pull data from server and insert it based on distance from donor in this arraylist
        items.add(new profileData("FOOD DONATION HEADING", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new profileData("FOOD DONATION HEADING", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new profileData("FOOD DONATION HEADING", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new profileData("FOOD DONATION HEADING", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));
        items.add(new profileData("FOOD DONATION HEADING", "5 km", "bunny bhai", "9154862562", "40 kg's of flour, 30kg of rice and 20 kg of pulses"));


    }
}