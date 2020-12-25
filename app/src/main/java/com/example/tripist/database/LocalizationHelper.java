package com.example.tripist.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class LocalizationHelper {

    public static void setLocale(String lang, Context activity){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getResources().updateConfiguration(config, activity.getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = activity.getSharedPreferences("Settings", activity.MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

    }

    public static void loadLocale(Context context){
        SharedPreferences prefs = context.getSharedPreferences("Settings", context.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        System.out.println(language);
        setLocale(language,context);
    }
}
