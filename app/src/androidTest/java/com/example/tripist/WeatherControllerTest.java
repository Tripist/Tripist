package com.example.tripist;

import android.content.res.Resources;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.example.tripist.navigation.BottomNavigationActivity;

import junit.framework.AssertionFailedError;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class WeatherControllerTest {

    @Rule
    public IntentsTestRule<BottomNavigationActivity> intentsTestRule = new IntentsTestRule<>(BottomNavigationActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testWeather() {
        //Checking the weather action is displayed or not
        onView(withId(R.id.weather_image)).check(matches(isDisplayed()));
        onView(withId(R.id.weather_text)).check(matches(isDisplayed()));
    }

}
