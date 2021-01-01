package com.example.tripist.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tripist.R;
import com.example.tripist.adapters.SliderAdapter;
import com.example.tripist.navigation.BottomNavigationActivity;

public class OnBoarding extends AppCompatActivity {
    //Variables defined
    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted;
    Button next;
    Button skip;
    Animation animation;
    int currentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        getSupportActionBar().hide();

        //Hooks
        viewPager =  findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = (Button) findViewById(R.id.next_button);
        next = (Button)findViewById(R.id.next_btn);
        skip = (Button) findViewById(R.id.skip_button);

        //Call Adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);


        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }
    //Go to homepage
    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), BottomNavigationActivity.class));
        finish();
    }
    //Go to homepage at the end of Onboarding pages
    public void letsGetStarted(View view){
        startActivity(new Intent(getApplicationContext(), BottomNavigationActivity.class));
    }
    //Navigate in Onboardinng pages
    public void next(View view){
        viewPager.setCurrentItem(currentPos + 1);
    }
    //indicates which page it is on
    private void addDots(int position){

        dots = new TextView[5];
        dotsLayout.removeAllViews();

        for(int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(66);

            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorDots));
        }

    }
    //Sliding pages
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        //Visibility of buttons
        @Override
        public void onPageSelected(int position) {
        addDots(position);
        currentPos = position;
        if(position == 0){
            letsGetStarted.setVisibility(View.INVISIBLE);
            next.setVisibility(View.VISIBLE);
            skip.setVisibility(View.VISIBLE);
        }
        else if(position == 1){
            letsGetStarted.setVisibility(View.INVISIBLE);
            next.setVisibility(View.VISIBLE);
            skip.setVisibility(View.VISIBLE);
        }
        else if(position == 2){
            letsGetStarted.setVisibility(View.INVISIBLE);
            next.setVisibility(View.VISIBLE);
            skip.setVisibility(View.VISIBLE);
        }
        else if(position == 3){
            letsGetStarted.setVisibility(View.INVISIBLE);
            next.setVisibility(View.VISIBLE);
            skip.setVisibility(View.VISIBLE);
        }
        else{
            animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_animation);
            letsGetStarted.setVisibility(View.VISIBLE);
            next.setVisibility(View.INVISIBLE);
            skip.setVisibility(View.INVISIBLE);
            letsGetStarted.setAnimation(animation);
        }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}