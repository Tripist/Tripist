package com.example.tripist.ui.home;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationActions;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tripist.MainActivity;
import com.example.tripist.MyLocationsPage;
import com.example.tripist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLOutput;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    FloatingActionButton historical_button;
    FloatingActionButton museum_button;
    ImageView weather_image;
    TextView weather_text;

    MainActivity mainActivity;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        weather();
        // burası değişecek  buttona onClickMethodu tanımlanacak
        weather_image = root.findViewById(R.id.weather_image);
        weather_text = root.findViewById(R.id.weather_text);
        historical_button = (FloatingActionButton) root.findViewById(R.id.historical_button);
        historical_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHistorical_Places();
            }
        });
        museum_button = (FloatingActionButton) root.findViewById(R.id.museum_button);
        museum_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHistorical_Places();
            }
        });

        return root;
    }

    public void showHistorical_Places() {
        Intent intent = new Intent(getActivity(), MyLocationsPage.class);
        startActivity(intent);
    }

    public void weather() {


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
                        //  Double Derece = Double.parseDouble(temps);
                        weather_text.setText(temps);
                        setWeather_image(weather_image,icons);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setWeather_image(final ImageView imageView, final String value) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (value){
                    case "01d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "01n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01n));
                        break;
                    case "02d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "02n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02n));
                        break;
                    case "03d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "03n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03n));
                        break;
                    case "04d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "04n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04n));
                        break;
                    case "09d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "09n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09n));
                        break;
                    case "10d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "10n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10n));
                        break;
                    case "11d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "11n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11n));
                        break;
                    case "13d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    case "13n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13n));
                        break;
                    case "50d":  imageView.setImageDrawable(getResources().getDrawable(R.drawable.d50d));
                        break;
                    case "50n":  imageView.setImageDrawable(getResources().getDrawable(R.drawable.d50d));
                        break;
                    default:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));

                }

            }
        });
    }
}






























