package com.example.tripist.views.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tripist.R;
import com.example.tripist.viewmodels.SettingsViewModel;
import com.example.tripist.views.categories.HistoricalPlacesCategory;
import com.example.tripist.views.onboarding.OnBoarding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsFragment  extends Fragment {
    private SettingsViewModel settingsViewModel;
    Button show_onboarding_button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_settings);
        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        show_onboarding_button = root.findViewById(R.id.button2);
        ((View) show_onboarding_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOnboarding();
            }
        });
        return root;
    }
    //Show onboardingPages
    public void showOnboarding() {
        Intent intent = new Intent(getActivity(), OnBoarding.class);
        startActivity(intent);
    }
}

