package com.example.tripist;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripist.ui.mylocations.MyLocationsFragment;
import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<Places> mList;
    Dialog myDialog;
    SQLiteDatabase database;

    public RecyclerViewAdapter(@NonNull Context mContext, ArrayList<Places> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_mylocation, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        //Dialog ini


        vHolder.item_mylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Test Click" + String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                int position = vHolder.getAdapterPosition();
                String name = mList.get(position).name;
                CallDialog(vHolder.getAdapterPosition(),name);
            }
        });


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.item_textView.setText(mList.get(position).name);

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView item_textView;
        private final Button button;
        private LinearLayout item_mylocation;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_mylocation = itemView.findViewById(R.id.mylocation_item_id);
            item_textView = itemView.findViewById(R.id.item_textView);
            button = itemView.findViewById(R.id.item_button);



        }

        }
        public void CallDialog(final int id,final String name){
            myDialog = new Dialog(mContext);
            myDialog.setContentView(R.layout.dialog_mylocations);
            Button dialog_update_button = myDialog.findViewById(R.id.item_update_button);
            Button dialog_delete_button = myDialog.findViewById(R.id.item_delete_button);

            dialog_delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo delete
                    System.out.println(name);
                    mList.remove(id);
                    notifyDataSetChanged();
                }
            });

            dialog_update_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo update
                    System.out.println(name);
                    notifyDataSetChanged();

                }
            });
            myDialog.show();
        }





    }

