package com.example.swe_project.ui.community;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.swe_project.R;

import java.util.List;

public class activitesAdapter extends RecyclerView.Adapter<activitesAdapter.activityViewHolder> {

    public static class activityViewHolder extends RecyclerView.ViewHolder{
        TextView title,dist, name, phone, desc;

        activityViewHolder(View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.titletext);
            dist=itemView.findViewById(R.id.distance);
            name=itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.phone);
            desc=itemView.findViewById(R.id.description);
        }
    }

    List<activitesData> data;
    activitesAdapter(List<activitesData> datalist){
        this.data=datalist;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public activitesAdapter.activityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_discover_org, viewGroup, false);
        activitesAdapter.activityViewHolder pvh = new activitesAdapter.activityViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(activitesAdapter.activityViewHolder activityViewHolder, int i) {
        activityViewHolder.title.setText(data.get(i).title);
        activityViewHolder.dist.setText(data.get(i).distance);
        activityViewHolder.name.setText(data.get(i).name);
        activityViewHolder.phone.setText(data.get(i).phone);
        activityViewHolder.desc.setText(data.get(i).description);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}