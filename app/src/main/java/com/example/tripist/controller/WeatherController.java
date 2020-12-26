package com.example.tripist.controller;

import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.tripist.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherController {

    public static void weather(final TextView weather_text,final ImageView weather_image,final TextView notification_text,final FragmentActivity activity)  {


        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=Istanbul&appid=e9f28400958342fd868a8a775923208a&units=metric")
                // .url("https://api.openweathermap.org/data/2.5/weather?lat="+ userLatitude + "&lon=" +userLongitude + "&appid=e9f28400958342fd868a8a775923208a&units")
                .get()
                .build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Response response = client.newCall(request).execute();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                    String responseData = response.body().string();
                    try {
                        JSONObject json = new JSONObject(responseData);
                        JSONArray array = json.getJSONArray("weather");
                        JSONObject object = array.getJSONObject(0);

                        String description = object.getString("description");
                        String icons = object.getString("icon");

                        JSONObject temp1 = json.getJSONObject("main");
                        Double Temperature = temp1.getDouble("temp");


                        String temps = Math.round(Temperature) + " °C";


                        //    Double Derece = Double.parseDouble(temps);
                     //   setNotification_text(notification_text, Temperature, icons,activity);
                        //bildirim mesajı ayarlamak icin
                        weather_text.setText(temps);
                        setWeather_image(weather_image, icons,activity);
                        setNotification_text(notification_text, Temperature, icons,activity);
                        System.out.println(icons + Temperature);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void setWeather_image(final ImageView imageView, final String value,final FragmentActivity context) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                switch (value) {
                    case "01d":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "01n":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d01n));
                        break;
                    case "02d":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "02n":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d02n));
                        break;
                    case "03d":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "03n":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d03n));
                        break;
                    case "04d":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "04n":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d04n));
                        break;
                    case "09d":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "09n":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d09n));
                        break;
                    case "10d":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "10n":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d10n));
                        break;
                    case "11d":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "11n":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d11n));
                        break;
                    case "13d":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d13d));
                        break;
                    case "13n":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d13n));
                        break;
                    case "50d":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d50d));
                        break;
                    case "50n":
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d50d));
                        break;
                    default:
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.d01d));

                }

            }
        });
    }

    public static void setNotification_text(final TextView notification_text, final Double Temperature, final String icons,final FragmentActivity activity) {
       activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                int currentHour = Calendar.getInstance().getTime().getHours() ;
                switch (icons) {
                    case "01d":
                    case "01n":
                    case "02d":
                    case "02n":
                    case "03d":
                    case "03n":
                        if(currentHour <= 10){
                            List<String> morning_good = Arrays.asList(activity.getResources().getStringArray(R.array.morning_good));
                            Random random =new Random();
                            notification_text.setText(morning_good.get(random.nextInt(morning_good.size())));
                        }
                        else if (currentHour <= 16){
                            List<String> noon_good = Arrays.asList(activity.getResources().getStringArray(R.array.noon_good));
                            Random random =new Random();
                            notification_text.setText(noon_good.get(random.nextInt(noon_good.size())));
                        }
                        else if (currentHour <= 20){
                            List<String> afternoon_good = Arrays.asList(activity.getResources().getStringArray(R.array.afternoon_good));
                            Random random =new Random();
                            notification_text.setText(afternoon_good.get(random.nextInt(afternoon_good.size())));
                        }
                        else if (currentHour <= 24){
                            List<String> night = Arrays.asList(activity.getResources().getStringArray(R.array.night));
                            Random random =new Random();
                            notification_text.setText(night.get(random.nextInt(night.size())));
                        }
                        break;
                    case "09d":
                    case "09n":
                    case "10d":
                    case "10n":
                    case "11d":
                    case "11n":

                        if(currentHour <= 10){
                            List<String> morning_good = Arrays.asList(activity.getResources().getStringArray(R.array.morning_rain));
                            Random random =new Random();
                            notification_text.setText(morning_good.get(random.nextInt(morning_good.size())));
                        }
                        else if (currentHour <= 16){
                            List<String> noon_good = Arrays.asList(activity.getResources().getStringArray(R.array.noon_rain));
                            Random random =new Random();
                            notification_text.setText(noon_good.get(random.nextInt(noon_good.size())));
                        }
                        else if (currentHour <= 20){
                            List<String> afternoon_good = Arrays.asList(activity.getResources().getStringArray(R.array.afternoon_rain));
                            Random random =new Random();
                            notification_text.setText(afternoon_good.get(random.nextInt(afternoon_good.size())));
                        }
                        else if (currentHour <= 24){
                            List<String> night = Arrays.asList(activity.getResources().getStringArray(R.array.night));
                            Random random =new Random();
                            notification_text.setText(night.get(random.nextInt(night.size())));
                        }
                        break;
                    case "04n":
                    case "04d":
                    case "13d":
                    case "13n":
                    case "50d":
                    case "50n":
                        if(currentHour <= 10){
                            List<String> morning_bad = Arrays.asList(activity.getResources().getStringArray(R.array.morning_bad));
                            Random random =new Random();
                            notification_text.setText(morning_bad.get(random.nextInt(morning_bad.size())));
                        }
                        else if (currentHour <= 16){
                            List<String> noon_bad = Arrays.asList(activity.getResources().getStringArray(R.array.noon_bad));
                            Random random =new Random();
                            notification_text.setText(noon_bad.get(random.nextInt(noon_bad.size())));
                        }
                        else if (currentHour <= 20){
                            List<String> afternoon_bad = Arrays.asList(activity.getResources().getStringArray(R.array.afternoon_bad));
                            Random random =new Random();
                            notification_text.setText(afternoon_bad.get(random.nextInt(afternoon_bad.size())));
                        }
                        else if (currentHour <= 24){
                            List<String> night = Arrays.asList(activity.getResources().getStringArray(R.array.night));
                            Random random =new Random();
                            notification_text.setText(night.get(random.nextInt(night.size())));
                        }
                        break;




                }
            }
        });
    }
}
