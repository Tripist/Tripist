package com.example.tripist.navigation;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.tripist.R;
import com.example.tripist.adapters.MyFavAdapter;
import com.example.tripist.maps.MyFavourites_Map;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Categories;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyFavouritesFragment extends Fragment {
    //Definition variables
    SQLiteDatabase database;
    RecyclerView fav_rv;
    ArrayList<Categories> categoriesArrayList;
    DatabaseHelper databaseHelper;
    FloatingActionButton favtomap_fab;

    //ui views of components
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_myfavourites, container, false);

        fav_rv = root.findViewById(R.id.fav_rv);
        favtomap_fab = root.findViewById(R.id.favtomap_fab);
        MyFavAdapter myFavAdapter = new MyFavAdapter(categoriesArrayList,getContext());
        fav_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        fav_rv.setAdapter(myFavAdapter);
        myFavAdapter.notifyDataSetChanged();
        favtomap_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMy_Favourites();
            }
        });

        return root;
    }

    @Override   //First Creation
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper =new DatabaseHelper(getContext());
        categoriesArrayList = new KategorieDao().MyFavouritesList(databaseHelper);

    }

    //call the function to get data from the database
    public void getData(){
        categoriesArrayList.clear();
        categoriesArrayList = new KategorieDao().MyFavouritesList(databaseHelper);
        MyFavAdapter adapter = new MyFavAdapter(categoriesArrayList,getActivity());
        fav_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter.notifyDataSetChanged();
        fav_rv.setAdapter(adapter);
    }

    //open the map
    public void showMy_Favourites() {
        Intent intent = new Intent(getActivity(), MyFavourites_Map.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }
    @Override   //Initialization
    public void onStart() {
        getData();
        super.onStart();
    }

}
