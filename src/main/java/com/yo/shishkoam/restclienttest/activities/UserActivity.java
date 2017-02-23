package com.yo.shishkoam.restclienttest.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yo.shishkoam.restclienttest.App;
import com.yo.shishkoam.restclienttest.Consts;
import com.yo.shishkoam.restclienttest.R;
import com.yo.shishkoam.restclienttest.api.models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 21.02.2017
 */
public class UserActivity extends AppCompatActivity implements Consts {

    private View progressView;
    private View contentView;
    private TextView nameTextView;
    private ProgressBar profileProgressBar;
    private TextView profileProgressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        progressView = findViewById(R.id.progress);
        contentView = findViewById(R.id.content);
        nameTextView = (TextView) findViewById(R.id.name);
        profileProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        profileProgressTextView = (TextView) findViewById(R.id.progress_text);

        View exitButton = findViewById(R.id.exit);
        exitButton.setOnClickListener(v -> onBackPressed());
        View settings = findViewById(R.id.settings);
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        View historyButton = findViewById(R.id.payment_history);
        String token = getIntent().getExtras().getString(TOKEN);
        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, HistoryActivity.class);
            intent.putExtra(TOKEN, token);
            startActivity(intent);
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> onBackPressed());

        showProgress(true);
        requestUser(token);
    }

    private void requestUser(String token) {
        App.getApi().getUser(token).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.code() == 200 && response.body() != null) {
                    String firstName = response.body().getFirstName();
                    if (firstName != null) {
                        nameTextView.setText(firstName);
                    }
                    Object email = response.body().getEmail();
                    String lastName = response.body().getLastName();
                    Object locale = response.body().getLocale();
                    String phone = response.body().getPhone();
                    setupUserProgressToUI(firstName, email, lastName, locale, phone);
                } else {
                    Toast.makeText(UserActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                showProgress(false);
                Toast.makeText(UserActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUserProgressToUI(String firstName, Object email, String lastName, Object locale, String phone) {
        int progress = 0;
        if (firstName != null && !firstName.equals("")) {
            progress++;
        }
        if (email != null) {
            progress++;
        }
        if (lastName != null && !lastName.equals("")) {
            progress++;
        }
        if (locale != null) {
            progress++;
        }
        if (phone != null && !phone.equals("")) {
            progress++;
        }
        int result = progress * 100 / 5;
        profileProgressBar.setProgress(result);
        profileProgressTextView.setText(getString(R.string.profile_progress_value, result));
    }

    /**
     * Shows the progress UI and hides the user form.
     */
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        contentView.setVisibility(show ? View.GONE : View.VISIBLE);
        contentView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                contentView.setVisibility(show ? View.GONE : View.VISIBLE);
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
