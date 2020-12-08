package com.example.tripist.adapters;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripist.controller.SplashScreen;
import com.example.tripist.controller.categories.HistoricalPlacesCategory;
import com.example.tripist.controller.maps.Island_Beachs;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.models.Places;
import com.example.tripist.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CardviewPlaceHolder> {
    private ArrayList<Places> itemList;
    Context context;
    SQLiteDatabase database;

    public CategoryAdapter(ArrayList<Places> placeList, Context context) {
        this.itemList = placeList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardviewPlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design, parent, false);

        final CardviewPlaceHolder cardviewPlaceHolder = new CardviewPlaceHolder(itemView);
        cardviewPlaceHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cardviewPlaceHolder.isim.getText().toString();

                System.out.println(name);
            }

        });
        cardviewPlaceHolder.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cardviewPlaceHolder.isim.getText().toString();
                System.out.println(name);
            }
        });
        cardviewPlaceHolder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cardviewPlaceHolder.isim.getText().toString();

               fav(name);
            }
        });

        return new CardviewPlaceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardviewPlaceHolder holder, int position) {
        Places item = itemList.get(position);
        holder.isim.setText(item.getName());
        holder.img.setImageResource(context.getResources()
                .getIdentifier(item.getImage(), "drawable", context.getPackageName()));


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class CardviewPlaceHolder extends RecyclerView.ViewHolder {
        public CardView card;
        public TextView isim;
        public ImageView img;
        public ImageButton fav;
        public ImageButton google;

        public CardviewPlaceHolder(View view) {
            super(view);
            card = view.findViewById(R.id.card);
            isim = view.findViewById(R.id.isim);
            img = view.findViewById(R.id.img);
            fav = view.findViewById(R.id.fav);
            google = view.findViewById(R.id.google);

        }
    }
    public void fav(String name){
        database = context.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        String sql = "INSERT INTO  deneme (name) VALUES (?) ";
        database.execSQL(sql, new String[]{ name});
    }
}







