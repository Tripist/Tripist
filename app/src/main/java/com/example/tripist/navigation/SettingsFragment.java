package com.example.tripist.navigation;

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
import com.example.tripist.controller.LocalizationController;
import com.example.tripist.intro.OnBoarding;

import java.util.Locale;

import static com.example.tripist.controller.LocalizationController.showChangeLanguageDialog;

public class SettingsFragment extends Fragment {
    //Definition variables
    public static String appLanguage = Locale.getDefault().getLanguage();
    Button show_onboarding_button;
    Button button3;
    LocalizationController localizationController;

    @Nullable
    @Override   //ui views of components
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        show_onboarding_button = root.findViewById(R.id.button2);
        ((View) show_onboarding_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOnboarding();
            }
        });

        button3 = root.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog(v, getActivity());
            }
        });

        localizationController = new LocalizationController();
        return root;
    }

    //Show onboardingPages
    public void showOnboarding() {
        Intent intent = new Intent(getActivity(), OnBoarding.class);
        startActivity(intent);
    }

}

