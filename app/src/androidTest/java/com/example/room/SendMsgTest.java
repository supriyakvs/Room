package com.example.room;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class SendMsgTest {
    @Rule
    //public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);
    public final ActivityTestRule<SendMsg> main = new ActivityTestRule<>(SendMsg.class);

    //Invalid contact number
    @Test
    {
        onView(withId(R.id.phone_num)).inRoot(new ToastMatcher()).perform(typeText("852734333")).check(matches(withText("Please enter valid phone number")));
    }


    //Blank message
    @Test
    {
        onView(withId(R.id.msg)).inRoot(new ToastMatcher()).perform(typeText("")).check(matches(withText("Please enter valid details")));
    }

    //Check position of Edit text box
    @Test
    {
        Espresso.onView(withId(R.id.phone_num)) .check(isCompletelyAbove(withId(R.id.msg)));
        Espresso.onView(withId(R.id.msg)) .check(isCompletelyAbove(withId(R.id.send)));

    }


}