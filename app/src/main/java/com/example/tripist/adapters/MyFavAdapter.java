package com.example.tripist.adapters;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripist.R;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Categories;

import java.util.ArrayList;

public class MyFavAdapter extends RecyclerView.Adapter<MyFavAdapter.CardviewPlaceHolder> {
    //Definition adapter variables
    private ArrayList<Categories> itemList;
    Context context;
    SQLiteDatabase database;
    DatabaseHelper databaseHelper;

    // Constructor
    public MyFavAdapter(ArrayList<Categories> placeList, Context context) {
        this.itemList = placeList;
        this.context = context;
    }

    @NonNull
    @Override    //First Creation
    public MyFavAdapter.CardviewPlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carddesign_myfav, parent, false);

        final MyFavAdapter.CardviewPlaceHolder cardviewPlaceHolder = new MyFavAdapter.CardviewPlaceHolder(itemView);
        databaseHelper = new DatabaseHelper(context);
        cardviewPlaceHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
        cardviewPlaceHolder.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cardviewPlaceHolder.isim.getText().toString();
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                //  String term = googleText.getText().toString();
                String term = name;
                System.out.println(term);
                intent.putExtra(SearchManager.QUERY, term);
                v.getContext().startActivity(intent);
            }
        });

        return new MyFavAdapter.CardviewPlaceHolder(itemView);
    }

    @Override   //Binding data to UI
    public void onBindViewHolder(@NonNull MyFavAdapter.CardviewPlaceHolder holder, int position) {
        Categories item = itemList.get(position);
        holder.isim.setText(item.getName());
        holder.img.setImageResource(context.getResources()
                .getIdentifier(item.getImage(), "drawable", context.getPackageName()));
        holder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favoriteselect_24));
        holder.fav.setTag(position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    //Card view Properties
    public class CardviewPlaceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView card;
        public TextView isim;
        public ImageView img;
        public ToggleButton fav;
        public ImageButton google;

        public CardviewPlaceHolder(View view) {
            super(view);

            card = (CardView)view.findViewById(R.id.cardf);
            isim = view.findViewById(R.id.isimf);
            img = view.findViewById(R.id.imgf);
            fav = view.findViewById(R.id.favf);
            google = view.findViewById(R.id.googlef);
           fav.setOnClickListener(this);
        }
        @Override   //remove
        public void onClick(View view) {
            final String name = isim.getText().toString();
            int position = (int) view.getTag();
           // Toast.makeText(view.getContext(),Integer.toString(position),Toast.LENGTH_SHORT).show();
            new KategorieDao().unfav(databaseHelper,name);
            itemList.remove(position);
            notifyDataSetChanged();

        }
    }






}
