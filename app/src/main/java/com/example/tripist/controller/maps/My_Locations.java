package com.example.tripist.controller.maps;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class My_Locations extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    SQLiteDatabase database;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__locations);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        add_marker();

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        if (info.matches("new")) {
            //KULLANICIDAN KONUM İZNİ
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    SharedPreferences sharedPreferences = My_Locations.this.getSharedPreferences("com.example.tripist", MODE_PRIVATE);
                    boolean trackBoolean = sharedPreferences.getBoolean("trackBoolean", false);

                    if (trackBoolean == false) {
                        LatLng user_location = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_location, 15));
                        sharedPreferences.edit().putBoolean("trackBoolean", true).apply();
                    }

                }
            };

            //kulanıcı izni kontrol etmek
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

        } else {
            //once  KAYDEDİLENEN DATALAR SQLİTE intent data
            //kontrol et
            Places place = (Places) intent.getSerializableExtra("place");
            LatLng latLng = new LatLng(place.latitude, place.longitude);
            String place_Name = place.name;
            mMap.addMarker(new MarkerOptions().position(latLng).title(place_Name));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }

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
                            //sqlite data
                            // mMap.clear();
                            //?
                            Places place = (Places) intent.getSerializableExtra("place");
                            LatLng latLng = new LatLng(place.latitude, place.longitude);
                            String place_Name = place.name;
                            mMap.addMarker(new MarkerOptions().position(latLng).title(place_Name));

                        }

                    }
                }
            }
        }
    }

    // uzun basıldığında adres ekleme
    @Override
    public void onMapLongClick(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String address = "";

        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (addressList != null && addressList.size() > 0) {
                if (addressList.get(0).getThoroughfare() != null) {
                    address += addressList.get(0).getThoroughfare();

                    if (addressList.get(0).getSubThoroughfare() != null) {
                        address += "";
                        address += addressList.get(0).getSubThoroughfare();
                    }


                }
            } else {
                // adres alamazsa default
                address = "new place";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



     // mMap.addMarker(new MarkerOptions().title(address).position(latLng));



        final Double latitude = latLng.latitude;
        final Double longitude = latLng.longitude;


        final Places place = new Places(address, latitude, longitude);


        AlertDialog.Builder builder = new AlertDialog.Builder(My_Locations.this);
        builder.setTitle(place.name);
        builder.setCancelable(false);
        builder.setItems(new CharSequence[]
                        {"My Hotel", "My Home", "My Airport", "Other"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String a = place.name;
                        Double b = place.latitude;
                        Double c = place.longitude;
                        switch (which) {
                            case 0:
                                //  Toast.makeText(getApplicationContext(), "My Hotel", Toast.LENGTH_SHORT).show();
                                String myhotel = "MY HOTEL";
                                if(DataExists(myhotel)== false){

                                    add_myhotel(myhotel, b, c);
                                }
                                break;
                            case 1:
                                // Toast.makeText(getApplicationContext(), "My blabla", Toast.LENGTH_SHORT).show();
                                String myhome = "MY HOME";
                                if(DataExists(myhome)== false){
                                    add_myblabla(myhome, b, c);
                                }
                                break;
                            case 2:
                                //  Toast.makeText(getApplicationContext(), "My Airport", Toast.LENGTH_SHORT).show();
                                String myairport = "MY AİRPORT";
                                if(DataExists(myairport)== false){
                                    add_myairport(myairport, b, c);
                                }

                                break;
                            case 3:
                                //  Toast.makeText(getApplicationContext(), "Other", Toast.LENGTH_SHORT).show();
                                add_others(a, b, c);
                                break;
                        }

                    }
                });

        builder.create().show();
    }

    public void add_others(final String name, final Double latitude, final Double longitude) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(My_Locations.this);
        alert.setTitle(name);

        alert.setView(edittext);

        alert.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                LatLng latLng = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().title(name).position(latLng));
                String UserLocationInput = edittext.getText().toString();
                try {
                    database = My_Locations.this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
                    String toCompile = "INSERT INTO my_locations (name,latitude,longitude) VALUES (?,?,?)";

                    SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
                    sqLiteStatement.bindString(1, UserLocationInput.toUpperCase());
                    sqLiteStatement.bindString(2, String.valueOf(latitude));
                    sqLiteStatement.bindString(3, String.valueOf(longitude));
                    sqLiteStatement.execute();

                    Toast.makeText(getApplicationContext(), "SAVED", Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });


        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "CLOSED", Toast.LENGTH_LONG).show();
            }
        });

        alert.show();
    }

    public void add_myhotel(final String name, final Double latitude, final Double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().title(name).position(latLng));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(name);
        alert.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                try {
                    database = My_Locations.this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
                    String toCompile = "INSERT INTO my_locations (name,latitude,longitude) VALUES (?,?,?)";

                    SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
                    sqLiteStatement.bindString(1, name);
                    sqLiteStatement.bindString(2, String.valueOf(latitude));
                    sqLiteStatement.bindString(3, String.valueOf(longitude));
                    sqLiteStatement.execute();

                    Toast.makeText(getApplicationContext(), "SAVED", Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });


        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "CLOSED", Toast.LENGTH_LONG).show();
            }
        });

        alert.show();
    }

    public void add_myblabla(final String name, final Double latitude, final Double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().title(name).position(latLng));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(name);
        alert.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                try {
                    database = My_Locations.this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
                    String toCompile = "INSERT INTO my_locations (name,latitude,longitude) VALUES (?,?,?)";

                    SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
                    sqLiteStatement.bindString(1, name);
                    sqLiteStatement.bindString(2, String.valueOf(latitude));
                    sqLiteStatement.bindString(3, String.valueOf(longitude));
                    sqLiteStatement.execute();

                    Toast.makeText(getApplicationContext(), "SAVED", Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });


        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "CLOSED", Toast.LENGTH_LONG).show();
            }
        });

        alert.show();
    }

    public void add_myairport(final String name, final Double latitude, final Double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().title(name).position(latLng));

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(name);
        alert.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {


                try {
                    database = My_Locations.this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
                    String toCompile = "INSERT INTO my_locations (name,latitude,longitude) VALUES (?,?,?)";

                    SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
                    sqLiteStatement.bindString(1, name);
                    sqLiteStatement.bindString(2, String.valueOf(latitude));
                    sqLiteStatement.bindString(3, String.valueOf(longitude));
                    sqLiteStatement.execute();

                    Toast.makeText(getApplicationContext(), "SAVED", Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });


        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "CLOSED", Toast.LENGTH_LONG).show();
            }
        });

        alert.show();
    }

    //kayıtlı konumları eklemek icin
    public void add_marker() {
        try {
            mMap.clear();
            database = this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM my_locations", null);

            int nameIX = cursor.getColumnIndex("name");
            int latitudeIX = cursor.getColumnIndex("latitude");
            int longitudeIX = cursor.getColumnIndex("longitude");

            while (cursor.moveToNext()) {
                String nameFromDatabase = cursor.getString(nameIX);
                String latitudeFromDatabase = cursor.getString(latitudeIX);
                String longitudeFromDatabase = cursor.getString(longitudeIX);

                Double latitude = Double.parseDouble(latitudeFromDatabase);
                Double longitude = Double.parseDouble(longitudeFromDatabase);
                LatLng latLng = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title(nameFromDatabase));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  boolean DataExists(String fieldValue) {
        database = this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        String Query = "Select * from my_locations where name ='" + fieldValue + "'";
        Cursor cursor = database.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();

            return false;

        }
        cursor.close();
        Toast.makeText(getApplicationContext(), fieldValue + " zaten mevcut", Toast.LENGTH_SHORT).show();
        return true;
    }



}







