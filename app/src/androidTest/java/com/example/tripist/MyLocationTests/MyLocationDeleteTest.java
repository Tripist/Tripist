package com.example.tripist.MyLocationTests;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;

import com.example.tripist.R;
import com.example.tripist.navigation.BottomNavigationActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MyLocationDeleteTest {
    @Rule
    public IntentsTestRule<BottomNavigationActivity> intentsTestRule = new IntentsTestRule<>(BottomNavigationActivity.class);

    @Test
    public void MyLocationDeleteTest(){
        onView(ViewMatchers.withId(R.id.navigation_mylocation)).perform(click());
        onView(withId(R.id.mymap_fab)).perform(click());
        SystemClock.sleep(4000);
        onView(withId(R.id.map)).perform(longClick());
        SystemClock.sleep(1000);
        onView(withText("My Hotel"))
                .inRoot(isDialog()) // <---
                .check(matches(isDisplayed()))
                .perform(click());
        SystemClock.sleep(2000);
        onView(withText("Yes"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        SystemClock.sleep(2000);
        //a new location added
        Espresso.pressBack();
        Espresso.pressBack();
        SystemClock.sleep(1500);
        onView(withId(R.id.navigation_mylocation)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.imageView3)).perform(click());
        SystemClock.sleep(1000);


        onView(withId(R.id.item_delete_button)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.deleteYes_button)).perform(click());
        SystemClock.sleep(2000);
        //location deleted
    }
}
