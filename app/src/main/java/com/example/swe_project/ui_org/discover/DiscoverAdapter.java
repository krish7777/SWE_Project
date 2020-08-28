package com.example.swe_project.ui_org.discover;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.swe_project.R;
import com.example.swe_project.ui_org.discover.DiscoverData;

import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {

    public static class DiscoverViewHolder extends RecyclerView.ViewHolder{
        TextView title,dist, name, phone, desc;

        DiscoverViewHolder(View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.titletext);
            dist=itemView.findViewById(R.id.distance);
            name=itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.phone);
            desc=itemView.findViewById(R.id.description);
        }
    }

    List<DiscoverData> data;
    DiscoverAdapter(List<DiscoverData> datalist){
        this.data=datalist;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public DiscoverViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_discover_org, viewGroup, false);
        DiscoverViewHolder pvh = new DiscoverViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder discoverViewHolder, int i) {
        discoverViewHolder.title.setText(data.get(i).title);
        discoverViewHolder.dist.setText(data.get(i).distance);
        discoverViewHolder.name.setText(data.get(i).name);
        discoverViewHolder.phone.setText(data.get(i).phone);
        discoverViewHolder.desc.setText(data.get(i).description);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
