package com.example.tripist.controller.navigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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

import java.util.Locale;

public class SettingsFragment  extends Fragment {
    Button show_onboarding_button;
    Button button3;

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
                System.out.println("geri");


            }
        });
        return root;
    }
    //Show onboardingPages
    public void showOnboarding() {
        Intent intent = new Intent(getActivity(), OnBoarding.class);
        startActivity(intent);
    }




    private void showChangeLanguageDialog(final View v){
        //TODO: On Android 6.0.1, when you try to change the language, it automatically sets the language to English
        //TODO: On all other Android versions (7.1.1+) it is kind of buggy, setLocale must be configured properly
        final String[] listItems = {getString(R.string.application_language1), getString(R.string.application_language2)};
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        mBuilder.setTitle(getString(R.string.dil_secenegi));
        mBuilder.setSingleChoiceItems(listItems,-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    setLocale("tr");
                    getActivity().recreate();
                }
                else if(i == 1){
                    setLocale("de");
                    getActivity().recreate();
                }
                Intent n = new Intent(getActivity(),HomeFragment.class);
                v.getContext().startActivity(n);
                getActivity().finish();
            }
        });
        mBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Settings", getActivity().MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = getActivity().getSharedPreferences("Settings", getActivity().MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }



}

