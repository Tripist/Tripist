package com.example.tripist;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.SystemClock;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import com.example.tripist.controller.BottomNav;
import com.example.tripist.controller.categories.FoodsCategory;
import com.example.tripist.controller.categories.MuseumsCategory;
import com.example.tripist.controller.categories.ParksCategory;
import com.example.tripist.database.DatabaseHelper;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteButtonTest {

    public ActivityTestRule<FoodsCategory> mActivitiyTestRule = new ActivityTestRule<>(FoodsCategory.class);

    public IntentsTestRule<BottomNav> intentsTestRule = new IntentsTestRule<>(BottomNav.class);


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
