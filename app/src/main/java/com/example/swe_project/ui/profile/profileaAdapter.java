package com.example.swe_project.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swe_project.R;

import java.util.List;

public class profileaAdapter extends RecyclerView.Adapter<profileaAdapter.profileViewHolder> {

    public static class profileViewHolder extends RecyclerView.ViewHolder{
        TextView title,dist, name, phone, desc;

        profileViewHolder(View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.titletext);
            dist=itemView.findViewById(R.id.distance);
            name=itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.phone);
            desc=itemView.findViewById(R.id.description);
        }
    }

    List<profileData> data;
    profileaAdapter(List<profileData> datalist){
        this.data=datalist;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public profileViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_profile, viewGroup, false);
        profileViewHolder pvh = new profileViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(profileViewHolder discoverViewHolder, int i) {
        discoverViewHolder.title.setText(data.get(i).title);
        discoverViewHolder.dist.setText(data.get(i).peopleFed);
        discoverViewHolder.name.setText(data.get(i).name);
        discoverViewHolder.phone.setText(data.get(i).phone);
        discoverViewHolder.desc.setText(data.get(i).description);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
