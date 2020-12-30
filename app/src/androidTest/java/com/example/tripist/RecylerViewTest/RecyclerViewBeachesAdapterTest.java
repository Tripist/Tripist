package com.example.tripist.RecylerViewTest;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import com.example.tripist.R;
import com.example.tripist.adapters.MuseumsAdapter;
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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewBeachesAdapterTest {


    public ActivityTestRule<Island_BeachesCategory> mActivitiyTestRule = new ActivityTestRule<>(Island_BeachesCategory.class);

    public IntentsTestRule<BottomNav> intentsTestRule = new IntentsTestRule<>(BottomNav.class);

    @Rule
    public RuleChain chain = RuleChain
            .outerRule(mActivitiyTestRule)
            .around(intentsTestRule);

    @Test
    public void reyclerScrollandItemTestBeaches(){
        //it checks, during scrolling; whether error occurs or not
        //Beaches

        onView(withId(R.id.island_beaches_button)).perform(click());
        RecyclerView recyclerView = mActivitiyTestRule.getActivity().findViewById(R.id.island_rv);
        int itemcount = recyclerView.getAdapter().getItemCount();
        Espresso.onView(withId(R.id.island_rv)).perform(RecyclerViewActions.scrollToPosition(itemcount-1));
        Espresso.onView(withId(R.id.island_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(itemcount-1,click()));
    }

}

