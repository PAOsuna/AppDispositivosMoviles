package com.example.p12mapas;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Locale;

public class MapsActivity extends FragmentActivity
        implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerDragListener, OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private Marker markerPrueba, markerDrag, Infowindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap= googleMap;
        LatLng mexico=new LatLng(19.4326077,-99.13320799999997);
        mMap.addMarker(new MarkerOptions().position(mexico).draggable(true)
                .title("Mexico")
                .snippet("Mexico solicita personal").icon(BitmapDescriptorFactory.fromResource(
                        R.drawable.mexico)));
        LatLng valle = new LatLng(19.1950964,-100.13267250000001);
        mMap.addMarker(new MarkerOptions().position(valle).title("Valle del bravo")
                .snippet("Valle precioso")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        LatLng zacatecas = new LatLng(22.7708555,-102.5823426);
        mMap.addMarker(new MarkerOptions().position(zacatecas).title("Zacatecas")
                .snippet("Zacatecas se escribe con Z")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.zacatecas)));
        LatLng hermosillo = new LatLng(29.0766965,-111.008565);
        mMap.addMarker(new MarkerOptions().position(hermosillo)
                .title("Hermosillo").snippet("illo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hermosillo)));
        //Prueba
        LatLng prueba=new LatLng(19.0412967,-98.2061995999999);
        markerPrueba=googleMap.addMarker(new MarkerOptions()
                .position(prueba).title("Prueba"));

        //Morelos
        LatLng morelos= new LatLng(18.681049,-99.10134979999998);
        markerDrag=googleMap.addMarker(new MarkerOptions()
                .position(morelos)
                .title("Morelos").draggable(true));
        //Toluca
        LatLng toluca=new LatLng(19.2826098,-99.65566530000001);
        Infowindow=googleMap.addMarker(new MarkerOptions().position(toluca).title("Toluca").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addPolyline(new PolylineOptions()
                .add(mexico,valle,toluca)
                .width(5)
                .color(Color.RED));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexico,7));
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(Infowindow)){
            TolucaFragment.newInstance(marker.getTitle(),
                    getString(R.string.TolucaInfo))
                    .show(getSupportFragmentManager(),null);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String lat, lng;
        lat=Double.toString(marker.getPosition().latitude);
        lng=Double.toString(marker.getPosition().longitude);
        if (marker.equals(markerPrueba)){
            Toast.makeText(this,lat+", "+lng,Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(markerDrag)){
            Toast.makeText(this,"Start",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(markerDrag)){
            String newTitle=String.format(Locale.getDefault(),
                    getString(R.string.marked_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);
            setTitle(newTitle);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(markerDrag)){
            Toast.makeText(this,"Finish",Toast.LENGTH_LONG).show();
            setTitle(R.string.sitios);
        }
    }
}