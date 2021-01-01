package com.example.tripist.maps;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Categories;
import com.example.tripist.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Squares_Map extends FragmentActivity implements OnMapReadyCallback {
    //Definition Variables
    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    DatabaseHelper databaseHelper;

    @Override  //First Creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_squares);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String squares = "squares";
        new KategorieDao().addMarker(databaseHelper,mMap,squares);
        new KategorieDao().add_MyLocMarker(databaseHelper,mMap);

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        if (info.matches("new")) {
            //Location Permission
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    SharedPreferences sharedPreferences = Squares_Map.this.getSharedPreferences("com.example.tripist", MODE_PRIVATE);
                    boolean trackBoolean = sharedPreferences.getBoolean("trackBoolean", false);

                    if (trackBoolean == false) {
                        LatLng user_location = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_location, 15));
                        sharedPreferences.edit().putBoolean("trackBoolean", true).apply();

                    }

                }
            };

            //Check Location Permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                Location last_location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (last_location != null) {
                    LatLng user_last_location = new LatLng(last_location.getLatitude(), last_location.getLongitude());
                    String latitudeString = String.valueOf(user_last_location.latitude);
                    String longitudeString = String.valueOf(user_last_location.longitude);

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_last_location, 15));

                }
            }

        }
    }
    //checking according to permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (requestCode == 100) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                    Intent intent = getIntent();
                    String info = intent.getStringExtra("info");

                    if (info.matches("new")) {
                        Location last_location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                        if (last_location != null) {

                            LatLng user_last_location = new LatLng(last_location.getLatitude(), last_location.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_last_location, 15));

                        } else {
                            Categories place = (Categories) intent.getSerializableExtra("place");
                            LatLng latLng = new LatLng(place.latitude, place.longitude);
                            String place_Name = place.name;
                            mMap.addMarker(new MarkerOptions().position(latLng).title(place_Name));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                        }

                    }
                }
            }
        }
    }
}

