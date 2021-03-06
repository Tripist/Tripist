package com.example.tripist.adapters;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

import com.example.tripist.R;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Categories;

import java.util.ArrayList;

public class ReligionsAdapter extends RecyclerView.Adapter<com.example.tripist.adapters.ReligionsAdapter.CardviewPlaceHolder> {
    //Definition adapter variables
    Context context;
    DatabaseHelper databaseHelper;
    private ArrayList<Categories> itemList;

    // Constructor
    public ReligionsAdapter(ArrayList<Categories> placeList, Context context) {
        this.itemList = placeList;
        this.context = context;
    }

    @NonNull
    @Override   //First Creation
    public com.example.tripist.adapters.ReligionsAdapter.CardviewPlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design, parent, false);


        final com.example.tripist.adapters.ReligionsAdapter.CardviewPlaceHolder cardviewPlaceHolder = new com.example.tripist.adapters.ReligionsAdapter.CardviewPlaceHolder(itemView);
        databaseHelper = new DatabaseHelper(context);
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
                    //add favorites
                    String name = cardviewPlaceHolder.isim.getText().toString();
                    cardviewPlaceHolder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favoriteselect_24));
                    String religions = "religions";
                    new KategorieDao().fav(databaseHelper, name, religions);
                }
                //remove favorites
                else {
                    String name = cardviewPlaceHolder.isim.getText().toString();
                    cardviewPlaceHolder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favorite_24));
                    new KategorieDao().unfav(databaseHelper, name);
                }
            }
        });


        return new com.example.tripist.adapters.ReligionsAdapter.CardviewPlaceHolder(itemView);
    }

    //Binding data to UI
    @Override
    public void onBindViewHolder(@NonNull com.example.tripist.adapters.ReligionsAdapter.CardviewPlaceHolder holder, int position) {
        Categories item = itemList.get(position);
        holder.isim.setText(item.getName());
        holder.img.setImageResource(context.getResources()
                .getIdentifier(item.getImage(), "drawable", context.getPackageName()));
        String name = item.getName();
        if (new KategorieDao().DataExists(databaseHelper, name) == false) {

            holder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favorite_24));
        }
        if (new KategorieDao().DataExists(databaseHelper, name) == true) {

            holder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favoriteselect_24));
        }


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    //Card view Properties
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
}
