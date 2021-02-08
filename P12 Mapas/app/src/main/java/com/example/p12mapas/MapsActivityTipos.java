package com.example.p12mapas;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityTipos extends FragmentActivity implements OnMapReadyCallback {
    private Button btnHibrido, btnNormal, btnSatelital, btnTerreno;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_tipos);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnHibrido=(Button)findViewById(R.id.btn_hibrido);
        btnNormal=(Button)findViewById(R.id.btn_normal);
        btnSatelital=(Button)findViewById(R.id.btn_satelital);
        btnTerreno=(Button)findViewById(R.id.btn_terreno);
    }

    public void cambiarHibrido(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
    public void  cambiarNormal(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    public void cambiarSatelital (View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
    public void cambiarTerreno(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}