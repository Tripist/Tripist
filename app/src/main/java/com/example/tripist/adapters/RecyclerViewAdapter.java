package com.example.tripist.adapters;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripist.models.Places;
import com.example.tripist.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<Places> mList;
    Dialog myDialog;
    SQLiteDatabase database;

    public RecyclerViewAdapter(@NonNull Context mContext, ArrayList<Places> mList,SQLiteDatabase database) {
        this.mContext = mContext;
        this.mList = mList;
        this.database = database;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.carddesign_mylocation, parent, false);
       // v = LayoutInflater.from(mContext).inflate(R.layout.item_mylocation, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        //Dialog init


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

        holder.text_mylocation.setText(mList.get(position).name);
       // holder.item_textView.setText(mList.get(position).name);

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text_mylocation;
        public CardView item_mylocation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_mylocation = itemView.findViewById(R.id.item_mylocation);
            text_mylocation = itemView.findViewById(R.id.text_mylocation);
        }

      /*
        private final TextView item_textView;
        private final Button button;
        private LinearLayout item_mylocation;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_mylocation = itemView.findViewById(R.id.mylocation_item_id);
            item_textView = itemView.findViewById(R.id.item_textView);
            button = itemView.findViewById(R.id.item_button);



        }
        */

        }
        public void CallDialog(final int id,final String name){
            myDialog = new Dialog(mContext);
            myDialog.setContentView(R.layout.dialog_mylocations);
            Button dialog_update_button = myDialog.findViewById(R.id.item_update_button);
            Button dialog_delete_button = myDialog.findViewById(R.id.item_delete_button);
            final EditText edittext = myDialog.findViewById(R.id.update_editText);
            dialog_delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String sql = "DELETE FROM my_locations WHERE name = ?";
                    database.execSQL(sql, new String[]{ name});
                    System.out.println(name);
                    mList.remove(id);
                    myDialog.cancel();
                    notifyDataSetChanged();
                }
            });

            dialog_update_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //todo refresh
                    String newName = edittext.getText().toString().toUpperCase().trim();
                    if (newName.isEmpty()) {
                        edittext.setError("Name can't be blank");
                        edittext.requestFocus();
                        return;
                    }

                    String sql = "UPDATE my_locations \n" +
                            "SET name = ? \n" +
                            "WHERE name = ?;\n";

                    database.execSQL(sql, new String[]{newName,name});
                    refreshTable();
                    Toast.makeText(mContext, "Employee Updated", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                     myDialog.cancel();



                }
            });
            myDialog.show();
        }


        public void refreshTable(){
            mList.clear();
            Cursor cursor = database.rawQuery("SELECT * FROM my_locations",null);

            int nameIx = cursor.getColumnIndex("name");
            int latitudeIx = cursor.getColumnIndex("latitude");
            int longitudeIx = cursor.getColumnIndex("longitude");

            while (cursor.moveToNext()) {

                String nameFromDatabase = cursor.getString(nameIx);
                String latitudeFromDatabase = cursor.getString(latitudeIx);
                String longitudeFromDatabase = cursor.getString(longitudeIx);

                Double latitude = Double.parseDouble(latitudeFromDatabase);
                Double longitude = Double.parseDouble(longitudeFromDatabase);

                Places place = new Places(nameFromDatabase,latitude,longitude);


                mList.add(place);

            }
            notifyDataSetChanged();
            cursor.close();
        }





    }

