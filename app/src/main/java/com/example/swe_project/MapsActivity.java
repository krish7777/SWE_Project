package com.example.swe_project;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng reqLocation;

    public static final String mapReply = "mapReply";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                Log.d("Result Map",latLng.toString());
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.draggable(true);
                markerOptions.position(latLng);

                markerOptions.title(latLng.latitude+ " : " + latLng.longitude);

                mMap.clear();
                reqLocation=latLng;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                mMap.addMarker(markerOptions);

            }
        });
    }

    public void searchLocation(View view) {

        EditText locationSearch = (EditText) findViewById(R.id.location_search_text);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;
        if(location != null || !location.equals("")){
            Geocoder geocoder= new Geocoder(this);
            try {
                addressList=geocoder.getFromLocationName(location,1);
            }catch (IOException e){
                e.printStackTrace();
            }
            if(addressList.size()!=0)
            {
                Address address=addressList.get(0);
                Log.d("Result address",address.toString());
                if(address.hasLatitude() && address.hasLongitude()){
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    reqLocation=latLng;
                    mMap.addMarker(new MarkerOptions().position(latLng).title(address.getFeatureName()));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                }else{
                    Toast toast=Toast.makeText(getApplicationContext(),"No such place.Please try again",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            else
            {
                Log.d("Result err","nothing dude");
                Toast toast= Toast.makeText(getApplicationContext(),"No such place.Please try again",Toast.LENGTH_LONG);
                toast.show();
            }

        }


    }

    public void confirmLocation(View view){
        if(reqLocation==null){
            Toast toast=Toast.makeText(getApplicationContext(),"Please pick any location on the map",Toast.LENGTH_LONG);
            toast.show();
        }else{
            Intent replyIntent = new Intent();
            replyIntent.putExtra(mapReply,reqLocation);
            setResult(RESULT_OK,replyIntent);
            finish();
        }

    }
}