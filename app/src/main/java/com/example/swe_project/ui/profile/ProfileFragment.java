package com.example.swe_project.ui.profile;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.swe_project.AuthChecker;
import com.example.swe_project.MainActivity;
import com.example.swe_project.R;
import com.example.swe_project.UserActivity;
import com.example.swe_project.VolleyMultipartRequest;
import com.example.swe_project.VolleySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {


    RecyclerView recyclerview;
    profileaAdapter adapter;
    ArrayList<profileData> items;//in this arraylist data is to be loaded from server to be shown in cards
    LinearLayout linearLayout;
    private ImageView profileImageView;








    private static final int REQUEST_PERMISSIONS = 100;
    private static final int PICK_IMAGE_REQUEST =1 ;

    private Bitmap bitmap;
    private String filePath;
    private String imageUrl;

    private TextView nameView;
    private TextView emailView;
    private TextView peopleFedView;
    private TextView moneyRaisedView;
    private Button logoutButton;
    String url;
    String imageUploadUrl;
    String ROOT_URL;

    DonorData donorData;







    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("profile","INNNN ONCREATEEEEE");
    }





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("profile","first check");
        View root = inflater.inflate(R.layout.fragment_profile, container, false);



        Log.d("profile","second check");
        //linearLayout = (LinearLayout) root.findViewById(R.id.linearlayout);

        initialiseitems();
        Log.d("profile","third check");
        recyclerview = root.findViewById(R.id.recyclerViewprofile);
        Log.d("profile","fourth check");
        recyclerview.setLayoutManager(new LinearLayoutManager(root.getContext()));
        Log.d("profile","fifth check");
        adapter = new profileaAdapter(items);
        Log.d("profile","sixth check");
        recyclerview.setAdapter(adapter);
        Log.d("profile","seventh check");
        url=  getActivity().getString(R.string.url)+"/donor/get-details";
        imageUrl= getActivity().getString(R.string.url)+"/upload";


        nameView= (TextView)root.findViewById(R.id.donor_name);
        emailView=(TextView)root.findViewById(R.id.donor_email);
        peopleFedView=(TextView)root.findViewById(R.id.donor_people_fed);
        moneyRaisedView=(TextView)root.findViewById(R.id.donor_money_raised);
        profileImageView= (ImageView) root.findViewById(R.id.profile_photo);
        logoutButton=(Button) root.findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        Log.d("Response","finished setting up views");







        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Response here", response.toString());
                    String name = response.getString("name");
                    String email = response.getString("email");
                    String contactNumber = response.getString("contactNumber");
                    String peopleFed = response.getString("peopleFed");
                    String moneyRaised= response.getString("moneyRaised");
                    String profilePicPath = response.getString("profilePicPath");
                    donorData=new DonorData(name,email,contactNumber,peopleFed,moneyRaised, profilePicPath);

                    nameView.setText(donorData.name);
                    emailView.setText(donorData.email);
                    peopleFedView.setText(donorData.peopleFed);
                    moneyRaisedView.setText(donorData.moneyRaised);

                    if(donorData.profilePicPath.length()!=0){
                        profileImageView.setBackground(null);
                        Picasso.get().load(profilePicPath).into(profileImageView);
                        Log.d("Response","pic already there will be updated");

                    }else{
                        profileImageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if ((ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                    if ((ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                                            Manifest.permission.READ_EXTERNAL_STORAGE))) {

                                    } else {
                                        ActivityCompat.requestPermissions(getActivity(),
                                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                                REQUEST_PERMISSIONS);
                                    }
                                } else {
                                    Log.e("Else", "Else");
                                    showFileChooser();
                                }
                            }
                        });
                    }
                    Log.d("Response name","finished creating object");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Response", "error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response error", String.valueOf(error));

            }
        }){
            @Override
            public Map<String, String> getHeaders()
            {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                String token = sharedPreferences.getString("token","");
                Log.d("token is ",token);
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization",token);
                return headers;
            }
        };

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);

        return root;
    }




    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri picUri = data.getData();
            filePath = getPath(picUri);
            if (filePath != null) {
                try {
                    Log.d("filePath", String.valueOf(filePath));
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), picUri);
                    uploadBitmap(bitmap);
//                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Toast.makeText(
                        getActivity(),"no image selected",
                        Toast.LENGTH_LONG).show();
            }
        }

    }
    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void uploadBitmap(final Bitmap bitmap) {

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, ROOT_URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Log.d("RESP",obj.toString());
                            Toast.makeText(getActivity().getApplicationContext(), obj.getString("location"), Toast.LENGTH_SHORT).show();
                            imageUrl = obj.getString("location");
                            profileImageView.setBackground(null);
                            Picasso.get().load(imageUrl).into(profileImageView);

                            final HashMap<String, String> params = new HashMap<String, String>();
                            params.put("profilePicPath",imageUrl);

                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, imageUploadUrl, new JSONObject(params), new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        Log.d("Response here", response.toString());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.d("Response", "error");
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("Response error", String.valueOf(error));

                                }
                            }){
                                @Override
                                public Map<String, String> getHeaders()
                                {
                                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                                    String token = sharedPreferences.getString("token","");
                                    Log.d("token is ",token);
                                    Map<String, String> headers = new HashMap<String, String>();
                                    headers.put("Content-Type", "application/json");
                                    headers.put("Authorization",token);
                                    return headers;
                                }
                            };

                            VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("GotError",""+error.getMessage());
                    }
                }) {


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("file", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(getActivity()).add(volleyMultipartRequest);
    }



    public void logout() {
        AuthChecker.logout(getActivity());
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }






    private void initialiseitems() {
        items = new ArrayList<>();
        //here you will have to pull data from server and insert it based on distance from donor in this arraylist
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));
        items.add(new profileData("NGO name", "23 km", "bunny bhai", "9154862562", "have been feeding people since '98. Aim that no one sleeps hungry"));

    }


}