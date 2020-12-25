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

public class SettingsFragment  extends Fragment {
    Button show_onboarding_button;
    Button button3;
    LocalizationHelper localizationHelper;
    public static String appLanguage = Locale.getDefault().getLanguage();

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

        button3 = root.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog(v);


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


    private void showChangeLanguageDialog(final View v) {
        //TODO: On Android 6.0.1, when you try to change the language, it automatically sets the language to English
        //TODO: On all other Android versions (7.1.1+) it is kind of buggy, setLocale must be configured properly
        final String[] listItems = {getString(R.string.application_language1), getString(R.string.application_language2)};
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        mBuilder.setTitle(getString(R.string.dil_secenegi));
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    localizationHelper.setLocale("tr", getContext());
                    getActivity().recreate();

                    appLanguage = Locale.getDefault().getLanguage();
                    System.out.println(appLanguage);

                } else if (i == 1) {
                    localizationHelper.setLocale("en", getContext());
                    getActivity().recreate();

                    appLanguage = Locale.getDefault().getLanguage();
                    System.out.println(appLanguage);

                }
                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


}

