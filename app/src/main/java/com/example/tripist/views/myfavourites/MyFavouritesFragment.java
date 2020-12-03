package com.example.tripist.views.myfavourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.tripist.R;
import com.example.tripist.viewmodels.MyFavouritesViewModel;

public class MyFavouritesFragment extends Fragment {

    private MyFavouritesViewModel myFavouritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        myFavouritesViewModel =
                new ViewModelProvider(this).get(MyFavouritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myfavourites, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        myFavouritesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}