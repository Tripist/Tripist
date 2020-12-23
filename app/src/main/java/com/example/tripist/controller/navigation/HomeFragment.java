package com.example.tripist.controller.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tripist.R;
import com.example.tripist.controller.categories.Bazaar_MarketsCategory;
import com.example.tripist.controller.categories.FoodsCategory;
import com.example.tripist.controller.categories.HistoricalPlacesCategory;
import com.example.tripist.controller.categories.Island_BeachesCategory;
import com.example.tripist.controller.categories.MuseumsCategory;
import com.example.tripist.controller.categories.ParksCategory;
import com.example.tripist.controller.categories.ReligionsCategory;
import com.example.tripist.controller.categories.SquaresCategory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

public class HomeFragment extends Fragment {
    FloatingActionButton historical_button, museum_button, religions_button, parks_button, squares_button;
    FloatingActionButton bazaar_markets_button, island_beaches_button, foods_button;
    ImageView weather_image;
    TextView weather_text;
    TextView notification_text;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        weather();
        // burası değişecek  buttona onClickMethodu tanımlanacak
        weather_image = root.findViewById(R.id.weather_image);
        weather_text = root.findViewById(R.id.weather_text);
        notification_text = root.findViewById(R.id.notification_text);

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
                showMuseums();
            }
        });
        religions_button = root.findViewById(R.id.religions_button);
        religions_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReligions();
            }
        });
        parks_button = root.findViewById(R.id.parks_button);
        parks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showParks();
            }
        });
        squares_button = root.findViewById(R.id.squares_button);
        squares_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSquares();
            }
        });
        bazaar_markets_button = root.findViewById(R.id.bazaar_markets_button);
        bazaar_markets_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBazaar_Markets();
            }
        });
        island_beaches_button = root.findViewById(R.id.island_beaches_button);
        island_beaches_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIsland_Beaches();
            }
        });
        foods_button = root.findViewById(R.id.foods_button);
        foods_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFoods();
            }
        });
        //setNotification_text();
        return root;
    }


    //Show categories
    public void showHistorical_Places() {
        Intent intent = new Intent(getActivity(), HistoricalPlacesCategory.class);
        startActivity(intent);
    }

    public void showMuseums() {
        Intent intent = new Intent(getActivity(), MuseumsCategory.class);
        startActivity(intent);
    }

    public void showReligions() {
        Intent intent = new Intent(getActivity(), ReligionsCategory.class);
        startActivity(intent);
    }

    public void showParks() {
        Intent intent = new Intent(getActivity(), ParksCategory.class);
        startActivity(intent);
    }

    public void showSquares() {
        Intent intent = new Intent(getActivity(), SquaresCategory.class);
        startActivity(intent);
    }

    public void showBazaar_Markets() {
        Intent intent = new Intent(getActivity(), Bazaar_MarketsCategory.class);
        startActivity(intent);
    }

    public void showIsland_Beaches() {
        Intent intent = new Intent(getActivity(), Island_BeachesCategory.class);
        startActivity(intent);
    }

    public void showFoods() {
        Intent intent = new Intent(getActivity(), FoodsCategory.class);
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


                        //    Double Derece = Double.parseDouble(temps);
                        setNotification_text(notification_text, Temperature, icons);
                        //bildirim mesajı ayarlamak icin
                        weather_text.setText(temps);
                        setWeather_image(weather_image, icons);

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
                switch (value) {
                    case "01d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "01n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01n));
                        break;
                    case "02d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "02n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02n));
                        break;
                    case "03d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "03n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03n));
                        break;
                    case "04d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "04n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04n));
                        break;
                    case "09d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "09n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09n));
                        break;
                    case "10d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "10n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10n));
                        break;
                    case "11d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "11n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11n));
                        break;
                    case "13d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    case "13n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13n));
                        break;
                    case "50d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d50d));
                        break;
                    case "50n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d50d));
                        break;
                    default:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));

                }

            }
        });
    }

    private void setNotification_text(final TextView notification_text, final Double Temperature, final String icons) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int currentHour = Calendar.getInstance().getTime().getHours() + 3;
                //   String temps = Math.round(Temperature) + " °C";

                 // HAVA ŞARTLARI KÖTÜYSE
                if (icons==""){
                    if(currentHour <= 10){
                        List<String> morning_bad = Arrays.asList(getResources().getStringArray(R.array.morning_bad));
                        Random random =new Random();
                        notification_text.setText(morning_bad.get(random.nextInt(morning_bad.size())));
                    }
                    else if (currentHour <= 16){
                        List<String> noon_bad = Arrays.asList(getResources().getStringArray(R.array.noon_bad));
                        Random random =new Random();
                        notification_text.setText(noon_bad.get(random.nextInt(noon_bad.size())));
                    }
                    else if (currentHour <= 20){
                        List<String> afternoon_bad = Arrays.asList(getResources().getStringArray(R.array.afternoon_bad));
                        Random random =new Random();
                        notification_text.setText(afternoon_bad.get(random.nextInt(afternoon_bad.size())));
                    }
                    else if (currentHour <= 24){
                        List<String> night = Arrays.asList(getResources().getStringArray(R.array.night));
                        Random random =new Random();
                        notification_text.setText(night.get(random.nextInt(night.size())));
                    }

                }
                //HAVA SARTLARI İYİYSE
                else{
                    if(currentHour <= 10){
                        List<String> morning_good = Arrays.asList(getResources().getStringArray(R.array.morning_good));
                        Random random =new Random();
                        notification_text.setText(morning_good.get(random.nextInt(morning_good.size())));
                    }
                    else if (currentHour <= 16){
                        List<String> noon_good = Arrays.asList(getResources().getStringArray(R.array.noon_good));
                        Random random =new Random();
                        notification_text.setText(noon_good.get(random.nextInt(noon_good.size())));
                    }
                    else if (currentHour <= 20){
                        List<String> afternoon_good = Arrays.asList(getResources().getStringArray(R.array.afternoon_good));
                        Random random =new Random();
                        notification_text.setText(afternoon_good.get(random.nextInt(afternoon_good.size())));
                    }
                    else if (currentHour <= 24){
                        List<String> night = Arrays.asList(getResources().getStringArray(R.array.night));
                        Random random =new Random();
                        notification_text.setText(night.get(random.nextInt(night.size())));
                    }

                }


            }
        });
    }
}






























