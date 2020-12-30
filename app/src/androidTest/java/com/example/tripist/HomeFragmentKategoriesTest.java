package com.example.tripist;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import com.example.tripist.R;
import com.example.tripist.controller.BottomNav;
import com.example.tripist.controller.OnBoarding;
import com.example.tripist.controller.SplashScreen;
import com.example.tripist.controller.categories.Bazaar_MarketsCategory;
import com.example.tripist.controller.categories.FoodsCategory;
import com.example.tripist.controller.categories.HistoricalPlacesCategory;
import com.example.tripist.controller.categories.Island_BeachesCategory;
import com.example.tripist.controller.categories.MuseumsCategory;
import com.example.tripist.controller.categories.ParksCategory;
import com.example.tripist.controller.categories.ReligionsCategory;
import com.example.tripist.controller.categories.SquaresCategory;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HomeFragmentKategoriesTest {

    @Rule
    public IntentsTestRule<BottomNav> intentsTestRule = new IntentsTestRule<>(BottomNav.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void TestOpenMuseums() {
        onView(withId(R.id.museum_button)).perform(click());
        intended(hasComponent(MuseumsCategory.class.getName()));
    }
    @Test
    public void TestOpenHistorialPlaces() {
        onView(withId(R.id.historical_button)).perform(click());
        intended(hasComponent(HistoricalPlacesCategory.class.getName()));
    }
    @Test
    public void TestOpenReligions() {
        onView(withId(R.id.religions_button)).perform(click());
        intended(hasComponent(ReligionsCategory.class.getName()));
    }
    @Test
    public void TestOpenFoods() {
        onView(withId(R.id.foods_button)).perform(click());
        intended(hasComponent(FoodsCategory.class.getName()));
    }
    @Test
    public void TestOpenBazaarMarkets() {
        onView(withId(R.id.bazaar_markets_button)).perform(click());
        intended(hasComponent(Bazaar_MarketsCategory.class.getName()));
    }
    @Test
    public void TestOpenParks() {
        onView(withId(R.id.parks_button)).perform(click());
        intended(hasComponent(ParksCategory.class.getName()));
    }
    @Test
    public void TestOpenSquares() {
        onView(withId(R.id.squares_button)).perform(click());
        intended(hasComponent(SquaresCategory.class.getName()));
    }
    @Test
    public void TestOpenIslandsAndBeaches() {
        onView(withId(R.id.island_beaches_button)).perform(click());
        intended(hasComponent(Island_BeachesCategory.class.getName()));
    }


}