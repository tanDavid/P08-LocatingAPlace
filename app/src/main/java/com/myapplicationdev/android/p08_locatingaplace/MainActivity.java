package com.myapplicationdev.android.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnN, btnC, btnE;
    private GoogleMap map;

    String[] locations={"Full View" ,"North","Central","East"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                UiSettings ui = map.getUiSettings();

                ui.setZoomControlsEnabled(true);


                LatLng poi_SG = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG, 11));

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {

                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS Access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                LatLng poi_North = new LatLng(1.4406, 103.8010);
                Marker north = map.addMarker(new MarkerOptions().position(poi_North).title("North - HQ").snippet("Block 333, Admiralty Ave 3, 765654\nOperating hours: 10am-5pm\n" +
                        "Tel:65433456\n").icon(BitmapDescriptorFactory.fromResource(R.drawable.star2)));


                LatLng poi_Central = new LatLng(1.304833, 103.831833);
                Marker central = map.addMarker(new MarkerOptions().position(poi_Central).title("Central").snippet("Block 3A, Orchard Ave 3, 134542\nOperating hours: 11am - 8pm\nTel: 67788652").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


                LatLng poi_East = new LatLng(1.3496, 103.9568);
                Marker east = map.addMarker(new MarkerOptions().position(poi_East).title("East").snippet("Block 555, Tampines Ave 3, 287788\nOperating hours: 9am - 5pm\nTel: 67788652").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String markerTitle = marker.getTitle();
                        if(markerTitle.equalsIgnoreCase("North - HQ")){
                            Toast.makeText(getApplicationContext(),markerTitle,Toast.LENGTH_LONG).show();
                        }else if(markerTitle.equalsIgnoreCase("Central")){
                            Toast.makeText(getApplicationContext(),markerTitle,Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),markerTitle,Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });

            }
        });

        btnN = findViewById(R.id.btnNorth);
        btnC = findViewById(R.id.btnCentral);
        btnE = findViewById(R.id.btnEast);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,locations);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);


        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map != null) {
                    LatLng poi_North = new LatLng(1.4406, 103.8010);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North, 15));
                }
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map != null) {
                    LatLng poi_Central = new LatLng(1.304833, 103.831833);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central, 15));
                }
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map != null) {
                    LatLng poi_East = new LatLng(1.3496, 103.9568);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East, 15));
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(i == 0){
            if(map != null) {
                LatLng poi_SG = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG, 11));
            }
        }else if(i == 1){
            if(map != null) {
                LatLng poi_North = new LatLng(1.4406, 103.8010);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North, 15));
            }
        }else if(i == 2){
            if(map != null) {
                LatLng poi_Central = new LatLng(1.304833, 103.831833);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central, 15));
            }
        }else{
            if(map != null) {
                LatLng poi_East = new LatLng(1.3496, 103.9568);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East, 15));
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}