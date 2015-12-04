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
import android.widget.TextView;

// Paul Epperson
public class LoginToClientTest extends ActivityInstrumentationTestCase2<LoginSignupActivity> {

    private static int TIMEOUT_IN_MS = 10000;
    private Instrumentation mInstrumentation;

    // LoginSignupActivity activity, members
    private LoginSignupActivity mLoginSignupActivity;
    private Button mLoginButton;
    private Button mSignupButton;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    public LoginToClientTest() {
        super(LoginSignupActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        // Set up instrumentation for monitoring
        mInstrumentation = getInstrumentation();

        // Set up LoginSignupActivity activity
        mLoginSignupActivity = getActivity();

        // Set up LoginSignupActivity members
        mLoginButton =
                (Button) mLoginSignupActivity.findViewById(R.id.login);
        mSignupButton =
                (Button) mLoginSignupActivity.findViewById(R.id.signup);
        mUsernameEditText =
                (EditText) mLoginSignupActivity.findViewById(R.id.client_username);
        mPasswordEditText =
                (EditText) mLoginSignupActivity.findViewById(R.id.password);
    }

    // Generally test preconditions
    public void testPreconditions() {
        testMembersExist();
        testMemberLayouts();
        testMemberContents();
    }

    // Verify Login Button behavior
    public void testZLoginButton() {

        // !! GIVEN USER IS AT THE LOGIN OR SIGN UP PAGE
        // !! AND AN EXISTING CLIENT USERNAME, PASSWORD IS ENTERED
        testPreconditions();

        // Set up Activity Monitor
        Instrumentation.ActivityMonitor yourClientWelcomeMonitor
                = mInstrumentation.addMonitor(ClientWelcome.class.getName(),
                    null, false);

        // Set up known existing client input username, password
        mLoginSignupActivity.runOnUiThread(new Runnable() {
            public void run() {
                mUsernameEditText.setText("testUsername");
                mPasswordEditText.setText("testPassword");
            }
        });
        mInstrumentation.waitForIdleSync();

        // !! WHEN USER CLICKS THE LOGIN BUTTON
        // Initiate behavior
        TouchUtils.clickView(this, mLoginButton);

        // !! THEN CLIENT WELCOME PAGE LOADS
        // Wait for ClientWelcome activity to load
        ClientWelcome receiverActivity = (ClientWelcome)yourClientWelcomeMonitor.
                waitForActivityWithTimeout(TIMEOUT_IN_MS);

        // Check ClientWelcome has loaded
        assertNotNull(receiverActivity);
        assertEquals(1, yourClientWelcomeMonitor.getHits());

        // Remove Monitor
        getInstrumentation().removeMonitor(yourClientWelcomeMonitor);

    }

    /* Not a good test ... Cannot be repeated
    // Verify Signup Button behavior
    public void testSignupButtonBehavior() {

        // Set up Activity Monitor
        Instrumentation.ActivityMonitor yourClientOrBarberMonitor
                = mInstrumentation.addMonitor(ClientOrBarber.class.getName(),
                    null, false);

        // Initiate behavior
        TouchUtils.clickView(this, mSignupButton);

        // Wait for ClientOrBarber activity to load
        ClientOrBarber receiverActivity
                = (ClientOrBarber)yourClientOrBarberMonitor.
                    waitForActivityWithTimeout(TIMEOUT_IN_MS);

        // Check ClientOrBarber has loaded
        assertNotNull(receiverActivity);
        assertEquals(1, yourClientOrBarberMonitor.getHits());

        // Remove Monitor
        getInstrumentation().removeMonitor(yourClientOrBarberMonitor);

    }
    */

    /* Not very useful tests
    // Verify Username EditText behavior
    public void testUsernameEditTextBehavior() {

    }

    // Verify Password EditText behavior
    public void testPasswordEditTextBehavior() {

    }
    */

    // Generally test existence of all members
    public void testMembersExist() {
        final View decorView = mLoginSignupActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, mSignupButton);
        ViewAsserts.assertOnScreen(decorView, mLoginButton);
        ViewAsserts.assertOnScreen(decorView, mUsernameEditText);
        ViewAsserts.assertOnScreen(decorView, mPasswordEditText);
    }

    // Generally test format of all members
    public void testMemberLayouts() {
        testSignupButtonLayout();
        testLoginButtonLayout();
        testUsernameEditTextLayout();
        testPasswordEditTextLayout();
    }

    public void testSignupButtonLayout() {
        final ViewGroup.LayoutParams layoutParams = mSignupButton.getLayoutParams();
        assertNotNull(layoutParams);
        // assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        // assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void testLoginButtonLayout() {
        final ViewGroup.LayoutParams layoutParams = mLoginButton.getLayoutParams();
        assertNotNull(layoutParams);
        // assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        // assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void testUsernameEditTextLayout() {
        final ViewGroup.LayoutParams layoutParams = mUsernameEditText.getLayoutParams();
        assertNotNull(layoutParams);
        // assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        // assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void testPasswordEditTextLayout() {
        final ViewGroup.LayoutParams layoutParams = mPasswordEditText.getLayoutParams();
        assertNotNull(layoutParams);
        // assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        // assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    // Generally test contents of all members
    public void testMemberContents() {
        testSignupButtonContents();
        testLoginButtonContents();
        testUsernameEditTextContents();
        testPasswordEditTextContents();
    }

    public void testSignupButtonContents() {
        // final String expected = mLoginSignupActivity.getString(R.string.SignupBtn);
        final String expected = "Register Today!";
        final String actual = mSignupButton.getText().toString();
        assertEquals(expected, actual);
    }

    public void testLoginButtonContents() {
        // final String expected = mLoginSignupActivity.getString(R.string.LoginBtn);
        final String expected = "Login";
        final String actual = mLoginButton.getText().toString();
        assertEquals(expected, actual);
    }

    public void testUsernameEditTextContents() {
        // final String expected = mLoginSignupActivity.getString(R.string.Username);
        final String expected = "";
        final String actual = mUsernameEditText.getText().toString();
        assertEquals(expected, actual);
    }

    public void testPasswordEditTextContents() {
        // final String expected = mLoginSignupActivity.getString(R.string.Password);
        final String expected = "";
        final String actual = mPasswordEditText.getText().toString();
        assertEquals(expected, actual);
    }

}