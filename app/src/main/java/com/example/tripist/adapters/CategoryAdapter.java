package com.example.tripist.adapters;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripist.models.Places;
import com.example.tripist.R;

import java.util.ArrayList;

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
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                //  String term = googleText.getText().toString();
                String term = name;
                intent.putExtra(SearchManager.QUERY, term);
                v.getContext().startActivity(intent);
            }
        });



        cardviewPlaceHolder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String name = cardviewPlaceHolder.isim.getText().toString();
                    cardviewPlaceHolder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favoriteselect_24));
                    fav(name);
                }
                else {
                    String name = cardviewPlaceHolder.isim.getText().toString();
                    cardviewPlaceHolder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favorite_24));
                    unfav(name);
                }
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
        String name = item.getName();
        if(DataExists(name)== false){

            holder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favorite_24));
        }
        if(DataExists(name)== true) {

            holder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favoriteselect_24));
        }


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class CardviewPlaceHolder extends RecyclerView.ViewHolder {
        public CardView card;
        public TextView isim;
        public ImageView img;
        public ToggleButton fav;
        public ImageButton google;

        public CardviewPlaceHolder(View view) {
            super(view);
            card = view.findViewById(R.id.cardf);
            isim = view.findViewById(R.id.isimf);
            img = view.findViewById(R.id.imgf);
            fav = view.findViewById(R.id.favf);
            google = view.findViewById(R.id.googlef);

        }
    }
    public void fav(String name){
        database = context.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        String sql = "INSERT INTO  my_favourites (name) VALUES (?) ";
        database.execSQL(sql, new String[]{ name});
    }
    public void unfav(String name){
        database = context.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        String sql = "DELETE FROM my_favourites WHERE name = ?";
        database.execSQL(sql, new String[]{ name});
    }
    public  boolean DataExists(String fieldValue) {
        database = context.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        String Query = "Select * from my_favourites where name ='" + fieldValue + "'";
        Cursor cursor = database.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();

            return false;

        }
        cursor.close();

        return true;
    }
}







