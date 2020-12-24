package com.example.tripist.adapters;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripist.R;
import com.example.tripist.controller.categories.Bazaar_MarketsCategory;
import com.example.tripist.controller.navigation.HomeFragment;
import com.example.tripist.controller.navigation.MyFavouritesFragment;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.BazaarMarkets;
import com.example.tripist.models.MyFavourites;
import com.example.tripist.models.Parks;
import com.example.tripist.models.Places;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MyFavAdapter extends RecyclerView.Adapter<MyFavAdapter.CardviewPlaceHolder> {
    private ArrayList<Places> itemList;
    Context context;
    SQLiteDatabase database;
    DatabaseHelper databaseHelper;
    public MyFavAdapter(ArrayList<Places> placeList, Context context) {
        this.itemList = placeList;
        this.context = context;
    }

    @NonNull
    @Override
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

       /* cardviewPlaceHolder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String name = cardviewPlaceHolder.isim.getText().toString();
                //   itemList.remove(cardviewPlaceHolder.getAdapterPosition());
              //  new KategorieDao().unfav(databaseHelper,name);
                notifyDataSetChanged();
                final Snackbar snackbar = Snackbar
                        .make(v, "Favorilerden Çıkarılsın mı?", Snackbar.LENGTH_LONG)
                        .setAction("UNFAV", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new KategorieDao().unfav(databaseHelper,name);

                                itemList.remove(position);
                                notifyDataSetChanged();
                               // view.getContext().startActivity(new Intent(context, HomeFragment.class));
                                notifyDataSetChanged();
                                Snackbar snackbar1 = Snackbar.make(view, "Favorilerden Çıkarıldı!", Snackbar.LENGTH_SHORT);

                                View sbView = snackbar1.getView();
                                TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
                                textView.setTextColor(Color.YELLOW);
                                snackbar1.show();

                            }
                        });
                snackbar.setActionTextColor(Color.RED);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();

            }
        });
*/



        return new MyFavAdapter.CardviewPlaceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavAdapter.CardviewPlaceHolder holder, int position) {
        Places item = itemList.get(position);
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
        @Override
        public void onClick(View view) {
            final String name = isim.getText().toString();
            System.out.println(name);
            int position = (int) view.getTag();
           // Toast.makeText(view.getContext(),Integer.toString(position),Toast.LENGTH_SHORT).show();
            new KategorieDao().unfav(databaseHelper,name);
            itemList.remove(position);
            notifyDataSetChanged();

        }
    }






}
