package com.example.location;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapIntent extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap map;
    private String LatLand;



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        String Lat = LatLand.split(",")[0];
        String Long = LatLand.split(",")[1];

        LatLng location = new LatLng(Double.parseDouble(Lat), Double.parseDouble(Long));
        map.addMarker(new MarkerOptions().position(location));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
    }
    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }

}