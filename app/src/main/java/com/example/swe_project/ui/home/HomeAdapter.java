package com.example.swe_project.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swe_project.R;
import com.example.swe_project.ui.home.HomeAdapter;
import com.example.swe_project.ui.home.HomeData;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<com.example.swe_project.ui.home.HomeAdapter.HomeViewHolder> {

    public static class HomeViewHolder extends RecyclerView.ViewHolder{
        TextView desc;
        Button donate;
        ImageView imageView;

        HomeViewHolder(View itemView){
            super(itemView);
            desc=itemView.findViewById(R.id.homeDescription);
            donate=itemView.findViewById(R.id.homeDonatebutton);
            imageView=itemView.findViewById(R.id.imageHome);
        }
    }

    List<HomeData> data;
    HomeAdapter(List<HomeData> datalist){
        this.data=datalist;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public HomeViewHolder  onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_home, viewGroup, false);
        HomeViewHolder pvh = new HomeViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder homeViewHolder, int i) {
        homeViewHolder.desc.setText(data.get(i).description);
        homeViewHolder.donate.setText(data.get(i).donate);
        homeViewHolder.imageView.setImageResource(data.get(i).imageResource);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
