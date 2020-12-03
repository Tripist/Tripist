package com.example.tripist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripist.models.Places;
import com.example.tripist.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CardviewPlaceHolder>{
    private List<Places> itemList;
    Context context;

    public CategoryAdapter(List<Places> placeList, Context context) {
        this.itemList = placeList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardviewPlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design,parent,false);

        return new CardviewPlaceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardviewPlaceHolder holder, int position) {
     Places item = itemList.get(position);
     holder.isim.setText(item.getName());
     holder.img.setImageResource(context.getResources()
             .getIdentifier(item.getImage(),"drawable",context.getPackageName()));


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

        public CardviewPlaceHolder(View view) {
            super(view);
            card = view.findViewById(R.id.card);
            isim = view.findViewById(R.id.isim);
            img = view.findViewById(R.id.img);
            fav = view.findViewById(R.id.fav);


        }
    }
}

