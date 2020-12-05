package com.example.tripist.controller;

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

public class OnBoarding extends AppCompatActivity {
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
        letsGetStarted = findViewById(R.id.next_button);
        next = findViewById(R.id.next_btn);
        skip = findViewById(R.id.skip_button);

        //Call Adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);


        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), BottomNav.class));
        finish();
    }
    public void letsGetStarted(View view){
        startActivity(new Intent(getApplicationContext(), BottomNav.class));
    }
    public void next(View view){
        viewPager.setCurrentItem(currentPos + 1);
    }

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

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

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

    private class BottomNav {
    }
}