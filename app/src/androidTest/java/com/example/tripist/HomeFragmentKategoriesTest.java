package com.example.tripist;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.example.tripist.navigation.BottomNavigationActivity;
import com.example.tripist.categories.BazaarMarkets_Category;
import com.example.tripist.categories.Foods_Category;
import com.example.tripist.categories.HistoricalPlaces_Category;
import com.example.tripist.categories.IslandBeaches_Category;
import com.example.tripist.categories.Museums_Category;
import com.example.tripist.categories.Parks_Category;
import com.example.tripist.categories.Religions_Category;
import com.example.tripist.categories.Squares_Category;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class HomeFragmentKategoriesTest {

    @Rule
    public IntentsTestRule<BottomNavigationActivity> intentsTestRule = new IntentsTestRule<>(BottomNavigationActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void TestOpenMuseums() {
        onView(withId(R.id.museum_button)).perform(click());
        intended(hasComponent(Museums_Category.class.getName()));
    }
    @Test
    public void TestOpenHistorialPlaces() {
        onView(withId(R.id.historical_button)).perform(click());
        intended(hasComponent(HistoricalPlaces_Category.class.getName()));
    }
    @Test
    public void TestOpenReligions() {
        onView(withId(R.id.religions_button)).perform(click());
        intended(hasComponent(Religions_Category.class.getName()));
    }
    @Test
    public void TestOpenFoods() {
        onView(withId(R.id.foods_button)).perform(click());
        intended(hasComponent(Foods_Category.class.getName()));
    }
    @Test
    public void TestOpenBazaarMarkets() {
        onView(withId(R.id.bazaar_markets_button)).perform(click());
        intended(hasComponent(BazaarMarkets_Category.class.getName()));
    }
    @Test
    public void TestOpenParks() {
        onView(withId(R.id.parks_button)).perform(click());
        intended(hasComponent(Parks_Category.class.getName()));
    }
    @Test
    public void TestOpenSquares() {
        onView(withId(R.id.squares_button)).perform(click());
        intended(hasComponent(Squares_Category.class.getName()));
    }
    @Test
    public void TestOpenIslandsAndBeaches() {
        onView(withId(R.id.island_beaches_button)).perform(click());
        intended(hasComponent(IslandBeaches_Category.class.getName()));
    }


}