package com.example.tripist.RecylerViewTest;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import com.example.tripist.R;
import com.example.tripist.navigation.BottomNavigationActivity;
import com.example.tripist.categories.Parks_Category;

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
public class RecyclerViewParksMapAdapterTest {


    public ActivityTestRule<Parks_Category> mActivitiyTestRule = new ActivityTestRule<>(Parks_Category.class);

    public IntentsTestRule<BottomNavigationActivity> intentsTestRule = new IntentsTestRule<>(BottomNavigationActivity.class);

    @Rule
    public RuleChain chain = RuleChain
            .outerRule(mActivitiyTestRule)
            .around(intentsTestRule);

    @Test
    public void recyclerScrollandItemTestParks(){
        //it checks, during scrolling; whether error occurs or not
        //Parks_Map

        onView(withId(R.id.parks_button)).perform(click());
        RecyclerView recyclerView = mActivitiyTestRule.getActivity().findViewById(R.id.park_rv);
        int itemcount = recyclerView.getAdapter().getItemCount();
        Espresso.onView(withId(R.id.park_rv)).perform(RecyclerViewActions.scrollToPosition(itemcount-1));
        Espresso.onView(withId(R.id.park_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(itemcount-1,click()));
    }

}