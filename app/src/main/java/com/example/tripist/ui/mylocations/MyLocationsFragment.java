package com.example.tripist.ui.mylocations;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tripist.Adapter;
import com.example.tripist.Places;
import com.example.tripist.R;
import com.example.tripist.maps.My_Locations;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MyLocationsFragment extends Fragment {
    SQLiteDatabase database;
    Adapter adapter;
    ArrayList<Places> placeList = new ArrayList<>();

    ImageButton map_Buttonn;
    private MyLocationsViewModel myLocationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        getData();
        myLocationsViewModel =
                new ViewModelProvider(this).get(MyLocationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mylocations, container, false);
        ListView listVieww = root.findViewById(R.id.listVieww);
        map_Buttonn = (ImageButton) root.findViewById(R.id.map_Buttonn);
        map_Buttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMy_Locations();
            }
        });

        listVieww.setAdapter(adapter);

        //LISTViewe tıklandıgında nolacak
        listVieww.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), My_Locations.class);
                intent.putExtra("info", "oldu");
                intent.putExtra("place", placeList.get(position));
                startActivity(intent);
            }
        });

        return root;

    }


    public void getData() {
        adapter = new Adapter(getContext(),placeList);
        try {
            database = getActivity().openOrCreateDatabase("Places", MODE_PRIVATE, null);
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

                Places place = new Places(nameFromDatabase, latitude, longitude);



                placeList.add(place);



            }
            //veri değişikligini onayla
            adapter.notifyDataSetChanged();
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();


        }



    }
 public void showMy_Locations() {
        Intent intent = new Intent(getActivity(), My_Locations.class);
        intent.putExtra("info","new");
        startActivity(intent);
    }
}