package com.example.map_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
{
    private GoogleMap mMap;
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        text = findViewById(R.id.text);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void Click (View view) {
        String addr = text.getText().toString();
        if(!addr.equals("")){
            List<Address> addlist = null;
            Geocoder geo = new Geocoder(this);
            try {
                addlist = geo.getFromLocationName(addr,5);
            }
            catch (IOException e) {
                Toast.makeText(this, "Ничего не найдено", Toast.LENGTH_SHORT);
            }
            mMap.clear();
            for (int i = 0; i < addlist.size(); i++) {
                Address someaddr = addlist.get(i);
                LatLng latLng = new LatLng(someaddr.getLatitude(), someaddr.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng));
                if(i == 0)
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            }
        }
    }
}

