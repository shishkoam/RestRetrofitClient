package com.yo.shishkoam.restclienttest.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yo.shishkoam.restclienttest.App;
import com.yo.shishkoam.restclienttest.Consts;
import com.yo.shishkoam.restclienttest.R;
import com.yo.shishkoam.restclienttest.api.models.TokenModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 * Created by User on 21.02.2017
 */
public class LoginActivity extends AppCompatActivity implements Consts {

    private EditText phoneView;
    private EditText passwordView;
    private View progressView;
    private View loginFormView;
    private View terminalsFab;
    private View infoFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        phoneView = (EditText) findViewById(R.id.phone);
        terminalsFab = findViewById(R.id.terminal_fab);
        infoFab = findViewById(R.id.info_fab);
        passwordView = (EditText) findViewById(R.id.password);
        passwordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(view -> attemptLogin());

        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.progress);

        Button registrationButton = (Button) findViewById(R.id.registration);
        registrationButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        phoneView.setError(null);
        passwordView.setError(null);

        // Store values at the time of the login attempt.
        String phone = phoneView.getText().toString();
        String password = passwordView.getText().toString();
        phone = phone.replaceAll("[\\-\\+ ]", "");

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(phone)) {
            phoneView.setError(getString(R.string.error_field_required));
            focusView = phoneView;
            cancel = true;
        } else if (!validatePhoneNumber(phone)) {
            phoneView.setError(getString(R.string.error_invalid_email));
            focusView = phoneView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            requestToken(phone, password);
        }
    }

    private void requestToken(String email, String password) {
        showProgress(true);
        App.getApi().getToken(PASSWORD, email, password).enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                if (response.code() == 200) {
                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    intent.putExtra(TOKEN, response.body().getTokenType() + " " + response.body().getAccessToken());
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, R.string.wrong_password, Toast.LENGTH_SHORT).show();
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t) {
                showProgress(false);
                Toast.makeText(LoginActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static boolean validatePhoneNumber(String phoneNo) {
        return phoneNo.matches("\\d{12}");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        terminalsFab.setVisibility(show ? View.GONE : View.VISIBLE);
        infoFab.setVisibility(show ? View.GONE : View.VISIBLE);

        loginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                terminalsFab.setVisibility(show ? View.GONE : View.VISIBLE);
                infoFab.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }
}

