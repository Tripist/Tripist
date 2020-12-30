package com.example.tripist.adapters;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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

import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Places;
import com.example.tripist.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    //Definition adapter variables
    Context mContext;
    ArrayList<Places> mList;
    Dialog myDialog, dialog_edit, delete_alert;
    SQLiteDatabase database;
    DatabaseHelper databaseHelper;

    // Constructor
    public RecyclerViewAdapter(@NonNull Context mContext, ArrayList<Places> mList, SQLiteDatabase database) {
        this.mContext = mContext;
        this.mList = mList;
        this.database = database;
    }


    @NonNull
    @Override //First Creation
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.carddesign_mylocation, parent, false);
        // v = LayoutInflater.from(mContext).inflate(R.layout.item_mylocation, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);
        //Dialog init
        databaseHelper = new DatabaseHelper(mContext);


        vHolder.item_mylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Test Click" + String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                int position = vHolder.getAdapterPosition();
                String name = mList.get(position).name;
                CallDialog(vHolder.getAdapterPosition(), name);

            }
        });


        return vHolder;
    }

    @Override //Binding data to UI
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.text_mylocation.setText(mList.get(position).name);
        // holder.item_textView.setText(mList.get(position).name);

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    //Card view Properties
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text_mylocation;
        public CardView item_mylocation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_mylocation = itemView.findViewById(R.id.item_mylocation);
            text_mylocation = itemView.findViewById(R.id.text_mylocation);
        }


    }

    //Dialog init
    public void CallDialog(final int id, final String name) {
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_mylocations);
        TextView dialog_loc_textview = myDialog.findViewById(R.id.konum_textview);
        dialog_loc_textview.setText(name);
        Button dialog_update_button = myDialog.findViewById(R.id.item_update_button);
        Button dialog_delete_button = myDialog.findViewById(R.id.item_delete_button);
        Button dialog_cancel_button = myDialog.findViewById(R.id.item_cancel_button);

        dialog_edit = new Dialog(mContext);
        dialog_edit.setContentView(R.layout.dialog_edit);
        final Button editCancel_button = dialog_edit.findViewById(R.id.editCancel_button);
        final Button editYes_button = dialog_edit.findViewById(R.id.editYes_button);
        delete_alert = new Dialog(mContext);
        delete_alert.setContentView(R.layout.delete_alert);
        final EditText edittext = dialog_edit.findViewById(R.id.editTextTextPersonName2);
        final Button deleteCancel_button = delete_alert.findViewById(R.id.deleteCancel_button);
        final Button deleteYes_button = delete_alert.findViewById(R.id.deleteYes_button);


        dialog_cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.cancel();
            }
        });

        dialog_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.cancel();
                delete_alert.show();
                deleteYes_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new KategorieDao().deletePlace(databaseHelper, name);
                        System.out.println(name);
                        mList.remove(id);
                        delete_alert.cancel();
                        notifyDataSetChanged();
                    }
                });
                deleteCancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete_alert.cancel();
                    }
                });
            }
        });

        dialog_update_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myDialog.cancel();
                dialog_edit.show();
                editCancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_edit.cancel();
                    }
                });
                editYes_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //todo refresh
                        String newName = edittext.getText().toString().trim();
                        System.out.println(newName);
                        if (newName.isEmpty()) {
                            // TODO set error get text hatası
                            edittext.setError("ERROR");
                            edittext.requestFocus();
                            return;
                        }

                        new KategorieDao().updatePlace(databaseHelper, name, newName);
                        new KategorieDao().refreshTable(databaseHelper, mList);
//
                        Toast.makeText(mContext, R.string.update_toast, Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        dialog_edit.cancel();
                    }
                });


            }
        });
        myDialog.show();
    }


}

