package com.example.tripist.controller.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tripist.R;
import com.example.tripist.controller.OnBoarding;

public class SettingsFragment  extends Fragment {
    Button show_onboarding_button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

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

