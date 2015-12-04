package com.example.seraphshroud.huber;

import android.app.Application;
import android.app.Instrumentation;
import android.support.annotation.UiThread;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

// Paul Epperson
public class FindBarberTest extends ActivityInstrumentationTestCase2<FindBarber> {

    private static int TIMEOUT_IN_MS = 10000;
    private Instrumentation mInstrumentation;

    // LoginSignupActivity activity, members
    private FindBarber mFindBarberActivity;
    private Spinner mPriceDropdownSpinner;
    private Spinner mDayDropdownSpinner;
    private Spinner mTimeDropdownSpinner;
    private Button mSearchButton;

    public FindBarberTest() {
        super(FindBarber.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        // Set up instrumentation for monitoring
        mInstrumentation = getInstrumentation();

        // Set up LoginSignupActivity activity
        mFindBarberActivity = getActivity();

        // Set up LoginSignupActivity members
        mPriceDropdownSpinner =
                (Spinner) mFindBarberActivity.findViewById(R.id.price_range);
        mDayDropdownSpinner =
                (Spinner) mFindBarberActivity.findViewById(R.id.day_range);
        mTimeDropdownSpinner =
                (Spinner) mFindBarberActivity.findViewById(R.id.time_range);
        mSearchButton =
                (Button) mFindBarberActivity.findViewById(R.id.searchbtn);
    }

    // Verify Find Barber behavior
    public void testBarberSearch() {

        // !! GIVEN USER IS AT THE FIND BARBER PAGE
        // !! AND A PRICE IS SELECTED
        // !! AND A DAY IS SELECTED
        // !! AND A TIME IS SELECTED
        final View decorView = mFindBarberActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, mDayDropdownSpinner);
        ViewAsserts.assertOnScreen(decorView, mPriceDropdownSpinner);
        ViewAsserts.assertOnScreen(decorView, mTimeDropdownSpinner);
        ViewAsserts.assertOnScreen(decorView, mSearchButton);

        // Set up Activity Monitor
        Instrumentation.ActivityMonitor yourBarberSearchResultsMonitor
                = mInstrumentation.addMonitor(BarberSearchResults.class.getName(),
                null, false);

        // Set up known existing barber input username, password
        mFindBarberActivity.runOnUiThread(new Runnable() {
            public void run() {
                mTimeDropdownSpinner.setSelection(0, true);
                mDayDropdownSpinner.setSelection(0, true);
                mPriceDropdownSpinner.setSelection(0, true);
            }
        });
        mInstrumentation.waitForIdleSync();

        // !! WHEN USER CLICKS THE SEARCH BUTTON
        // Initiate behavior
        TouchUtils.clickView(this, mSearchButton);

        // !! THEN BARBER SEARCH RESULTS PAGE LOADS
        // Wait for BarberWelcome activity to load
        BarberSearchResults receiverActivity = (BarberSearchResults)yourBarberSearchResultsMonitor.
                waitForActivityWithTimeout(TIMEOUT_IN_MS);

        // Check BarberWelcome has loaded
        assertNotNull(receiverActivity);
        assertEquals(1, yourBarberSearchResultsMonitor.getHits());

        // Remove Monitor
        getInstrumentation().removeMonitor(yourBarberSearchResultsMonitor);

    }

}