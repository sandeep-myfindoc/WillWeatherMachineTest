package com.willyweathermachinetest.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.util.Log;
import android.view.View;

import com.willyweathermachinetest.R;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.willyweathermachinetest.view.playerList.PlayerListActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PlayerListActivityTest {
    @Rule
    public ActivityTestRule<PlayerListActivity> playerListActivityActivityTestRule = new ActivityTestRule<PlayerListActivity>(PlayerListActivity.class);
    private PlayerListActivity playerListActivity = null;
    Instrumentation.ActivityMonitor monitor = InstrumentationRegistry.getInstrumentation().addMonitor(PlayerDetailActivityTest.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        playerListActivity = playerListActivityActivityTestRule.getActivity();
    }
    /* 1) RecyclerView comes in to view
        2) wait to laod data to recylerview
        3) select 0 index item and navigate to Player Detail screen
     */
    @Test
    public void playerListRecyclerViewTest() {
        View view = playerListActivity.findViewById(R.id.rvPlayers);
        assertNotNull(view);
        while (getRecylerViewItemCount() <= 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        onView(ViewMatchers.withId(R.id.rvPlayers))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        pressBack();
        onView(ViewMatchers.withId(R.id.rvPlayers)).check(matches(isDisplayed()));
    }

    private int getRecylerViewItemCount() {
        RecyclerView recyclerView = (RecyclerView) playerListActivity.findViewById(R.id.rvPlayers);
        return recyclerView.getAdapter().getItemCount();
    }

    @After
    public void tearDown() throws Exception {
        playerListActivity = null;
    }
}