package com.user.songratingsystem;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.user.songratingsystem.activities.LoginActivity;
import com.user.songratingsystem.activities.SongDetailActivity;
import com.user.songratingsystem.adapter.UserSongsAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class deleteUI {
    @Rule
    public ActivityTestRule<SongDetailActivity> deleteUITest = new ActivityTestRule<>(SongDetailActivity.class);
    @Test
    public void loginTestIT() throws Exception {
        onView(withId(R.id.sCancelBtn)).perform(click());
    }
}
