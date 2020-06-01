//package com.example.room;
//
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.rule.ActivityTestRule;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.junit.Assert.*;
//@@RunWith(AndroidJUnit4.class)
//public class IndividualChatActivityTest {
//
//    @Rule
//    //public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);
//    public final ActivityTestRule<IndividualChatActivity> main = new ActivityTestRule<>(IndividualChatActivity.class);
//
//    @Test
//    {
//        onView(withId(R.id.send)).inRoot(new ToastMatcher()).perform(click()).check(matches(withText("Message Sent")));
//    }
//
//
//
//}