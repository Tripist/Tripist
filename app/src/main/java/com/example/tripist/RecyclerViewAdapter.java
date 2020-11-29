package com.example.tripist;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    List<Places> mList;
    Dialog myDialog;
    DatabaseAccessObject dao = new DatabaseAccessObject();


    public RecyclerViewAdapter(Context mContext, List<Places> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_mylocation,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);

       //Dialog ini
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_mylocations);
        Button dialog_update_button = myDialog.findViewById(R.id.item_update_button);
        Button dialog_delete_button = myDialog.findViewById(R.id.item_delete_button);

        dialog_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = vHolder.getAdapterPosition();
                dao.deleteFromMylocations(id);
                mList.remove(getItemId(id));
                notifyDataSetChanged();
            }
        });

        vHolder.item_mylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Test Click" + String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                myDialog.show();
            }
        });

        return vHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mList.get(position).name);

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private Button button;
        private LinearLayout item_mylocation;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_mylocation = itemView.findViewById(R.id.mylocation_item_id);
            tv_name = itemView.findViewById(R.id.item_textView);
            button = itemView.findViewById(R.id.item_button);
        }


    }
}
