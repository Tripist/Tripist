package com.example.tripist;

import android.os.SystemClock;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.example.tripist.navigation.BottomNavigationActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class MyLocationBearbeitenTest {

    @Rule
    public IntentsTestRule<BottomNavigationActivity> intentsTestRule = new IntentsTestRule<>(BottomNavigationActivity.class);

    @Test
    public void MyLocationBearbeitenTest() {

        onView(withId(R.id.navigation_mylocation)).perform(click());
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
                .inRoot(isDialog()) // <---
                .check(matches(isDisplayed()))
                .perform(click());
        SystemClock.sleep(2000);
        Espresso.pressBack();
        Espresso.pressBack();
        SystemClock.sleep(1500);
        onView(withId(R.id.navigation_mylocation)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.imageView3)).perform(click());
        SystemClock.sleep(1000);
        //new location added

        onView(withId(R.id.item_update_button)).perform(click());
        onView(withId(R.id.editTextTextPersonName2)).perform(typeText("My Home"));
        SystemClock.sleep(1000);
        onView(withId(R.id.editYes_button)).perform(click());
        SystemClock.sleep(1500);
        //location edited



    }

    public static ViewAction clickXY(final int x, final int y){
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {

                        final int[] screenPos = new int[2];
                        view.getLocationOnScreen(screenPos);

                        final float screenX = screenPos[0] + x;
                        final float screenY = screenPos[1] + y;
                        float[] coordinates = {screenX, screenY};

                        return coordinates;
                    }
                },
                Press.FINGER);
    }
}
