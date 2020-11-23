package com.example.tripist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.tripist.maps.My_Locations;

import java.util.ArrayList;

public class MyLocationsPage extends FragmentActivity {
    SQLiteDatabase database;
    Adapter adapter;
    ArrayList<Places> placeList = new ArrayList<>();
    ListView listView;
    Button map_Button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_locations_page);
        listView = findViewById(R.id.listView);
        map_Button = findViewById(R.id.map_Button);

        getData();
    }
    public void getData() {
        adapter = new Adapter(this, placeList);
        try {
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

                Places yer = new Places(nameFromDatabase, latitude, longitude);

                System.out.println(yer.name);
                placeList.add(yer);

            }
            //veri değişikligini onayla
            adapter.notifyDataSetChanged();
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();


        }

        listView.setAdapter(adapter);

        //LISTViewe tıklandıgında nolacak
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyLocationsPage.this, My_Locations.class);
                intent.putExtra("info", "oldu");
                intent.putExtra("place", placeList.get(position));
                startActivity(intent);
            }
        });

    }

    public void showMyLocationsMap(View view){
        Intent intent = new Intent(this, My_Locations.class);
        intent.putExtra("info","new");
        startActivity(intent);
    }
}