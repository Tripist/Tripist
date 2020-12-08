package com.example.tripist.controller.navigation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.tripist.R;
import com.example.tripist.adapters.CategoryAdapter;
import com.example.tripist.models.Places;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MyFavouritesFragment extends Fragment {
    SQLiteDatabase database;
    RecyclerView fav_rv;
    ArrayList<Places> placesArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_myfavourites, container, false);



        fav_rv = root.findViewById(R.id.fav_rv);
        CategoryAdapter categoryAdapter = new CategoryAdapter(placesArrayList,getContext());
        fav_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        fav_rv.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        return root;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        placesArrayList = new ArrayList<>();

        try {
            database = getActivity().openOrCreateDatabase("Places", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM deneme ", null);

            int nameIX = cursor.getColumnIndex("name");


            while (cursor.moveToNext()) {
                String nameFromDatabase = cursor.getString(nameIX);
                String a = "ayasofya";
                Places place = new Places(nameFromDatabase,a);


                placesArrayList.add(place);

            }

            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    public void getData(){
        placesArrayList.clear();
        CategoryAdapter adapter = new CategoryAdapter(placesArrayList,getActivity());
        fav_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        fav_rv.setAdapter(adapter);
        try {
            database = getActivity().openOrCreateDatabase("Places", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM deneme", null);

            int nameIX = cursor.getColumnIndex("name");


            while (cursor.moveToNext()) {
                String nameFromDatabase = cursor.getString(nameIX);
                String a = "ayasofya";

                Places place = new Places(nameFromDatabase,a);


                placesArrayList.add(place);


            }

            adapter.notifyDataSetChanged();
            cursor.close();
        }


        catch (Exception e) {
            e.printStackTrace();


        }

        fav_rv.setAdapter(adapter);
    }
}