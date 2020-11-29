package com.example.tripist.ui.mylocations;

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

public class MyLocationsFragment extends Fragment {

    private MyLocationsViewModel myLocationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        myLocationsViewModel =
                new ViewModelProvider(this).get(MyLocationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mylocations, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        myLocationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}