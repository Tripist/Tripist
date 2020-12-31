package com.example.tripist.controller.navigation;

import android.content.Intent;
import android.os.Bundle;
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

import static com.example.tripist.controller.WeatherController.weather;

public class HomeFragment extends Fragment {
    //Definition variables
    FloatingActionButton historical_button, museum_button, religions_button, parks_button, squares_button;
    FloatingActionButton bazaar_markets_button, island_beaches_button, foods_button;
    ImageView weather_image;
    TextView weather_text;
    TextView notification_text;

    //ui views of components
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
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
        return root;
    }

    @Override   //Presenting the weather and notification
    public void onStart() {
        super.onStart();
        weather(weather_text,weather_image,notification_text,getActivity());
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


}





























