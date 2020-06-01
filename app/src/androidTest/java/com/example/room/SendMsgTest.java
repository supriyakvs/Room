//package com.example.room;
//
//import androidx.test.espresso.Espresso;
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
//import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.junit.Assert.*;
//@RunWith(AndroidJUnit4.class)
//public class SendMsgTest {
//    @Rule
//    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);
//    //public final ActivityTestRule<SendMsg> main = new ActivityTestRule<>(SendMsg.class);
//
//   //valid message
//    @Test {
//       onView(withId(R.id.phone_num)).perform(typeText("8527343337"));
//       onView(withId(R.id.msg)).perform(typeText("Hello world"));
//       onView(withId(R.id.send)).perform(click());
//       onView(withText("Message Sent")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
//   }
//
//
//    //Invalid contact number,some msg,press send.
//    @Test
//    {
//        onView(withId(R.id.phone_num)).perform(typeText("852734"));
//        onView(withId(R.id.msg)).perform(typeText("Hello"));
//        onView(withId(R.id.send)).perform(click());
//        onView(withText("Please enter valid phone number")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
//       // .inRoot(new ToastMatcher()).check(matches(withText("Please enter valid phone number")));
//    }
//
//
//    //Blank message
//    @Test
//    {
//        onView(withId(R.id.msg)).inRoot(new ToastMatcher()).perform(typeText("")).check(matches(withText("Please enter valid details")));
//    }
//
//    //Check position of Edit text box
//    @Test
//    {
//        Espresso.onView(withId(R.id.phone_num)) .check(isCompletelyAbove(withId(R.id.msg)));
//        Espresso.onView(withId(R.id.msg)) .check(isCompletelyAbove(withId(R.id.send)));
//
//    }
//
//
//}