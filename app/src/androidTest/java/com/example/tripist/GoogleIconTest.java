package com.example.tripist;

import android.os.SystemClock;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import com.example.tripist.navigation.BottomNavigationActivity;
import com.example.tripist.categories.Museums_Category;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class GoogleIconTest {
    public ActivityTestRule<Museums_Category> mActivitiyTestRule = new ActivityTestRule<>(Museums_Category.class);

    public IntentsTestRule<BottomNavigationActivity> intentsTestRule = new IntentsTestRule<>(BottomNavigationActivity.class);


    @Rule
    public RuleChain chain = RuleChain
            .outerRule(mActivitiyTestRule)
            .around(intentsTestRule);

    @Test
    public void googleIconTest() {
        //one Item selected from Museum Category, and Google icon has clicked.

        onView(withId(R.id.museum_button)).perform(click());
        RecyclerView recyclerView = mActivitiyTestRule.getActivity().findViewById(R.id.museum_rv);
        int itemcount = recyclerView.getAdapter().getItemCount();
        Espresso.onView(withId(R.id.museum_rv)).
                perform(RecyclerViewActions.actionOnItemAtPosition(5, MyViewAction.clickChildViewWithId(R.id.googlef)));
        SystemClock.sleep(3000);
    }
}
