package com.user.songratingsystem;

import android.content.Context;
import android.util.Log;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.user.songratingsystem.activities.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginUITest = new ActivityTestRule<>(LoginActivity.class);
    @Test
    public void loginTestIT() throws Exception {
        onView(withId(R.id.name)).perform(typeText("sudeep123"),closeSoftKeyboard());
        onView(withId(R.id.pass)).perform(typeText("sudeep123"),closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());


    }
}
