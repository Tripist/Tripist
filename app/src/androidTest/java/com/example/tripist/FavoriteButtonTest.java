package com.example.tripist;

import android.os.SystemClock;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import com.example.tripist.navigation.BottomNavigationActivity;
import com.example.tripist.categories.Foods_Category;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteButtonTest {

    public ActivityTestRule<Foods_Category> mActivitiyTestRule = new ActivityTestRule<>(Foods_Category.class);

    public IntentsTestRule<BottomNavigationActivity> intentsTestRule = new IntentsTestRule<>(BottomNavigationActivity.class);


    @Rule
    public RuleChain chain = RuleChain
            .outerRule(mActivitiyTestRule)
            .around(intentsTestRule);

    @Test
    public void favoriteButtonTest() {

        onView(withId(R.id.foods_button)).perform(click());
        RecyclerView recyclerView = mActivitiyTestRule.getActivity().findViewById(R.id.food_rv);
        int itemcount = recyclerView.getAdapter().getItemCount();

        //added to my favourites
        Espresso.onView(withId(R.id.food_rv)).
                perform(RecyclerViewActions.actionOnItemAtPosition(3, MyViewAction.clickChildViewWithId(R.id.favf)));
        Espresso.pressBack();

        onView(withId(R.id.navigation_myfavourites)).perform(click());
        SystemClock.sleep(3000);

        //deleted from my favourites
        Espresso.onView(withId(R.id.fav_rv)).
                perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.favf)));

        SystemClock.sleep(1500);
    }

}
