package com.example.tripist.database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.View;

import com.example.tripist.R;

import java.util.Locale;

import static com.example.tripist.controller.navigation.SettingsFragment.appLanguage;

public class LocalizationHelper {

    // setting the application language
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
    // loading the selected language
    public static void loadLocale(Context context){
        SharedPreferences prefs = context.getSharedPreferences("Settings", context.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        System.out.println(language);
        setLocale(language,context);
    }
    //database column of data according to selected language
    public static int app_language(){
        if(appLanguage == "en"){
            return 1;
        }
        else{
            return 5;
        }
    }
    public static int MyLocApp_language(){
        if(appLanguage == "en"){
            return 1;
        }
        else{
            return 4;
        }
    }
    public static int FoodsApp_language(){
        if(appLanguage == "en"){
            return 1;
        }
        else{
            return 3;
        }
    }
    public static boolean EnLanguage(){

        if(appLanguage == "en"){
            return true;
        }
        else{
            return false;
        }

    }
        // dialog for change language
    public static void showChangeLanguageDialog(final View v, final Activity context) {
        //TODO: On Android 6.0.1, when you try to change the language, it automatically sets the language to English
        //TODO: On all other Android versions (7.1.1+) it is kind of buggy, setLocale must be configured properly
        String türkce = "Türkçe";
        String english = "English";
        final String[] listItems = {türkce,english };
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        mBuilder.setTitle(R.string.dil_secenegi);
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocale("tr", context);
                    context.recreate();

                    appLanguage = Locale.getDefault().getLanguage();
                    System.out.println(appLanguage);

                } else if (i == 1) {
                    setLocale("en", context);
                    context.recreate();

                    appLanguage = Locale.getDefault().getLanguage();
                    System.out.println(appLanguage);

                }

                dialogInterface.dismiss();
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

}