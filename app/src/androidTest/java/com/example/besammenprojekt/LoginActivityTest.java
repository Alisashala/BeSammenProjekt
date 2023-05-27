package com.example.besammenprojekt;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.besammenprojekt.Onboarding.LoginActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


// LargeTest = Angiver, at dette er en stor test, der involverer flere komponenter.
@LargeTest
//Angiver, at testene skal køres med AndroidJUnit4 testkøreren.
@RunWith(AndroidJUnit4.class)

public class LoginActivityTest {

    // Rule =  En regel, der bruges til at konfigurere og udføre testscenarier for LoginActivity.
    @Rule

    // ActivityScenarioRule<LoginActivity> =  En regel, der håndterer testscenarier for LoginActivity.
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    // test = Angiver, at metoden er en testmetode.
    @Test
    public void loginActivityTest() {


 // Test af Email-felt
        // Viewinteraction =  Interaktion med UI-elementer ved hjælp af Espresso's API.
        // OnView = Finder en visning ved hjælp af matchere fra Espresso.
        ViewInteraction appCompatEditText = onView(

                // allOf =matcher fra Espresso, der bruges til at kombinere flere matchere til at
                    // identificere en visning på en mere præcis måde.
                // withId(R.id.et_mail) = Matcher ID'et for visningen og sikrer, at det matcher
                    // det angivne ID "et_email".
                allOf(withId(R.id.et_email),
                        // ChildAtPosition = matcher visningen som en child visning i en bestemt
                        // position
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        // isDisplayed = Matcher om visningen er synlig for brugeren på skærmen.
                        isDisplayed()));
        // udfører handlinger på den fundne EditText-visning.
        //  Udfører handlingen med at erstatte teksten i EditText med "nadine123@gmail.com" og lukker
        //  det virtuelle tastatur. Dette simulerer brugerinteraktionen med at indtaste og afslutte
        //  tekstindtastningen i feltet.
        appCompatEditText.perform(replaceText("nadine123@gmail.com"), closeSoftKeyboard());

 // Test af password-feltet
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.et_password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("123456"), closeSoftKeyboard());


// Test af login knap
        // definerer en ViewInteraction-variabel og initialiserer den med en Espresso-udtalelse.
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));

        // udfører en klikhandling på den matchede visning og simulerer en brugerinteraktion.
        materialButton.perform(click());

// Test af logud knap
        ViewInteraction button = onView(
                allOf(withId(R.id.logOut),
                isDisplayed()));
    }


    // Denne del af koden definerer en Matcher, der bruges til at finde og matche child på en
    // bestemt position i forhold til dets parent. Matcheren kontrollerer, om den givne visning er
    // child af en ViewGroup, der matcher parent, og om child er på den angivne position.
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
