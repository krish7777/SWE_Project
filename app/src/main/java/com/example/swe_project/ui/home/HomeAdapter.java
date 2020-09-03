package com.example.swe_project.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swe_project.DiscoverMapsActivity;
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


    View vi;
    
    @Override
    public HomeViewHolder  onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_home, viewGroup, false);
        HomeViewHolder pvh = new HomeViewHolder(v);

        return pvh;
    }

    /*
    @Override
    public DetailViewHolder  onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_home2, viewGroup, false);
        DetailViewHolder pvh = new DetailViewHolder(v);
        return pvh;
    }
*/

    @Override
    public void onBindViewHolder(HomeViewHolder homeViewHolder, int i) {
        homeViewHolder.desc.setText(data.get(i).description);
        homeViewHolder.donate.setText(data.get(i).donate);
        homeViewHolder.imageView.setImageResource(data.get(i).imageResource);

        if(i==0) {
            homeViewHolder.donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vi = view;
                    openActivity();
                }
            });
        }

        if(i==1) {
            homeViewHolder.donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vi = view;
                    Toast.makeText(vi.getContext(), "Not available" , Toast.LENGTH_SHORT ).show();
                }
            });
        }


        if(i==2) {
            homeViewHolder.donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vi = view;
                    openActivity2();
                }
            });
        }

        if(i==3) {
            homeViewHolder.donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vi = view;
                    Toast.makeText(vi.getContext(), "Lets feed even more!" , Toast.LENGTH_LONG ).show();
                }
            });
        }
    }

    private void openActivity() {
        Intent intent = new Intent(vi.getContext(), DiscoverMapsActivity.class);

        vi.getContext().startActivity(intent);
    }


    private void openActivity2() {

        Uri uri = Uri.parse("https://donatenow.wfp.org/wfp/~my-donation?_ga=2.175744097.210311577.1598616345-856329094.1598616345"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        vi.getContext().startActivity(intent);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


}
