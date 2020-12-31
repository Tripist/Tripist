package com.example.tripist.controller.navigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
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
import com.example.tripist.controller.SplashScreen;
import com.example.tripist.database.LocalizationHelper;

import java.util.Locale;

import static com.example.tripist.database.LocalizationHelper.showChangeLanguageDialog;

public class SettingsFragment  extends Fragment {
    //Definition variables
    Button show_onboarding_button;
    Button button3;
    LocalizationHelper localizationHelper;
    public static String appLanguage = Locale.getDefault().getLanguage();

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
                showChangeLanguageDialog(v,getActivity());
            }
        });

        localizationHelper = new LocalizationHelper();
        return root;
    }

    //Show onboardingPages
    public void showOnboarding() {
        Intent intent = new Intent(getActivity(), OnBoarding.class);
        startActivity(intent);
    }

}

