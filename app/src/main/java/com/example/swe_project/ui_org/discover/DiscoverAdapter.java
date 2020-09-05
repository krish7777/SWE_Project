package com.example.swe_project.ui_org.discover;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.swe_project.R;
import com.example.swe_project.VolleySingleton;
import com.example.swe_project.ui_org.discover.DiscoverData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {

    public static class DiscoverViewHolder extends RecyclerView.ViewHolder{
        TextView title,dist, name, phone, desc;
        Button acceptBtn;

        DiscoverViewHolder(View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.titletext);
            dist=itemView.findViewById(R.id.distance);
            name=itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.phone);
            desc=itemView.findViewById(R.id.description);
            acceptBtn=itemView.findViewById(R.id.acceptbtn);
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
    public void onBindViewHolder(DiscoverViewHolder discoverViewHolder, final int i) {
        discoverViewHolder.title.setText(data.get(i).title);
        discoverViewHolder.dist.setText(data.get(i).distance);
        discoverViewHolder.name.setText(data.get(i).name);
        discoverViewHolder.phone.setText(data.get(i).phone);
        discoverViewHolder.desc.setText(data.get(i).description);

        final String donor_id=data.get(i).donor_id;
        final String donation_id=data.get(i).donation_id;

        discoverViewHolder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d("Resp","Btn clicked"+data.get(i).distance);

                LayoutInflater li =LayoutInflater.from(v.getContext());
                View promptsView = li.inflate(R.layout.accept_donation_prompt,null);

                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.peopleFedCount);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.d("Resp",userInput.getText().toString());
                                        String peopleFedCount= userInput.getText().toString();
                                        if(peopleFedCount.length()==0){
                                            Toast.makeText(v.getContext(),"Please provide the people fed count",Toast.LENGTH_LONG).show();
                                        }else{

                                                Log.d("Resp",v.getContext().getString(R.string.url));
                                                String url= v.getContext().getString(R.string.url)+"/organisation/accept-donation";

                                            final HashMap<String, String> params = new HashMap<String, String>();
                                            params.put("donation_id", donation_id);
                                            params.put("donor_id", donor_id);
                                            params.put("peopleFed",peopleFedCount);

                                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    Log.d("Res","evercame");
                                                    try {

                                                        Log.d("Resp",response.toString());
                                                        removeAt(i);


                                                    } catch (Exception e) {

                                                        e.printStackTrace();
                                                        Log.d("Response", "inside error");
                                                         Toast.makeText(v.getContext(),"Some Error Occured. Please Try Again", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Log.d("Response error", String.valueOf(error));
                                                    // AuthChecker.logout(MainActivity.this);
                                                }
                                            }){
                                                @Override
                                                public Map<String, String> getHeaders()
                                                {
                                                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(v.getContext().getApplicationContext());
                                                    String token = sharedPreferences.getString("token","");
                                                    Map<String, String> headers = new HashMap<String, String>();
                                                    headers.put("Content-Type", "application/json");
                                                    headers.put("Authorization",token);
                                                    return headers;
                                                }
                                            };
                                            VolleySingleton.getInstance(v.getContext().getApplicationContext()).addToRequestQueue(jsonObjectRequest);



                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog=alertDialogBuilder.create();

                alertDialog.show();

                //removeAt(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void acceptDonation(){
        Log.d("Resp","CLicked to accept do");
    }

    public void removeAt(int position) {
       data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }

}
