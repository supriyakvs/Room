package com.example.room;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Root;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);


    //method1 (test if on button click, new activity is launched or not)
    @Test
    public void activityLaunch1(){
        onView(withId(R.id.add)).perform(click());
        intended(hasComponent(SendMsg.class.getName()));
    }

    //method2
    @Test
    public void activityLaunch2()
    {
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.phone_num)).check(matches(isDisplayed()));

    }


    // Test if recycler view is visible
    @Test
    public void testSampleRecyclerVisible() {
        onView(ViewMatchers.withId(R.id.recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(main.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    //test if click on recycler view works
    @Test
    public void testCaseForRecyclerClick() {
        onView(ViewMatchers.withId(R.id.recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(main.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    // test to scroll till end of screen
    @Test
    public void testCaseForRecyclerScroll() {

        // Get total item of RecyclerView
        RecyclerView recyclerView = main.getActivity().findViewById(R.id.recycler_view);
        int itemCount = recyclerView.getAdapter().getItemCount();

        // Scroll to end of page with position
        onView(ViewMatchers.withId(R.id.recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(main.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

    //test swipe to delete
    @Test
    public void swipeLeftToDelete(){
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("8527343337")),swipeLeft()));
        onView(withText("Msg deleted")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

}

