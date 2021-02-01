package com.willyweathermachinetest.test;
import android.app.Activity;
import android.view.View;
import androidx.test.rule.ActivityTestRule;
import com.willyweathermachinetest.view.playerDetail.PlayerDetailActivity;
import com.willyweathermachinetest.R;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerDetailActivityTest {
    @Rule
    public ActivityTestRule<PlayerDetailActivity> mActivityTestRule = new ActivityTestRule<PlayerDetailActivity>(PlayerDetailActivity.class);
    private PlayerDetailActivity playerDetailActivity = null;
    @Before
    public void setUp() throws Exception {
        playerDetailActivity = mActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        View view = playerDetailActivity.findViewById(R.id.labelName);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        playerDetailActivity = null;
    }
}