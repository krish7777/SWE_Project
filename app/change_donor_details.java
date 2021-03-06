package com.example.swe_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.util.HashMap;

public class donor_registration_page extends AppCompatActivity {

    private EditText emailView;
    private EditText passwordView;
    private EditText nameView;
    private EditText contactNumberView;
    private TextView coordinatesView;
    public static final int LOCATION_REQUEST = 1;
    LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration_page);
        emailView = findViewById(R.id.donor_reg_email);
        passwordView = findViewById(R.id.donor_reg_password);
        nameView = findViewById(R.id.donor_reg_name);
        contactNumberView = findViewById(R.id.donor_reg_contactNumber);
        coordinatesView= findViewById(R.id.coordinates);

    }

    @Override
    protected void onStart() {
        super.onStart();
        String role =AuthChecker.authChecker(this);
        if(role.length()!=0){
            Log.d("Res","haha already exist going");
            Intent intent ;
            if(role.equals("Donor")){
                intent= new Intent(donor_registration_page.this, DonorActivity.class);
            }else{
                intent= new Intent(donor_registration_page.this, OrgActivity.class);
            }

            startActivity(intent);
        }
    }

    public boolean validateCredentials(String email, String password, String name, String contactNumber,LatLng location) {
        if(name.length() ==0)
            return false;
        if (password.length() == 0)
            return false;
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() == false)
            return false;
        if (Patterns.PHONE.matcher(contactNumber).matches() == false)
            return false;
        if(location==null)
            return false;
        return true;
    }

    public void registerDonor(View view) {

        String url = getString(R.string.url)+"/auth/register/donor";
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        String name = nameView.getText().toString();
        String contactNumber = contactNumberView.getText().toString();
        if (validateCredentials(email, password,name, contactNumber,location) == true) {

            final HashMap<String, String> params = new HashMap<String, String>();
            params.put("email", email);
            params.put("password", password);
            params.put("name", name);
            params.put("contactNumber", contactNumber);
            params.put("latitude",Double.toString(location.latitude));
            params.put("longitude",Double.toString(location.longitude));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Response", response.toString());
                        Intent intent = new Intent(donor_registration_page.this,donor_login_page.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("Response", "error");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Response error", String.valueOf(error));
                    Toast.makeText(getApplicationContext(), "Error in Registration. Please Try Again", Toast.LENGTH_SHORT).show();
                }
            });

            VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        } else {
            Toast.makeText(getApplicationContext(), "Please fill all fields correctly!", Toast.LENGTH_SHORT).show();
        }
    }


    public void getLocation(View view) {
        Intent intent= new Intent(this,MapsActivity.class);
        startActivityForResult(intent, LOCATION_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOCATION_REQUEST){
            if(resultCode == RESULT_OK){
                Log.d("Result","got something");
                LatLng reqLocation= data.getExtras().getParcelable(MapsActivity.mapReply);
                Log.d("Result",reqLocation.toString());
                location = reqLocation;
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),"Please pick a location",Toast.LENGTH_LONG);
                if(location==null)
                    toast.show();
            }
            if(location!=null){
                coordinatesView.setText("("+location.latitude+" , "+location.longitude+")");
            }
        }
    }

}