package com.yo.shishkoam.restclienttest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.yo.shishkoam.restclienttest.api.models.HistoryModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 21.02.2017
 */
public class HistoryActivity extends AppCompatActivity {

    private ListView historyList;
    private View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyList = (ListView) findViewById(R.id.history_list);
        progressView = findViewById(R.id.history_progress);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        String token = getIntent().getExtras().getString("token");

        //run request
        showProgress(true);
        requestHistory(token, 1);
    }

    private void requestHistory(String token, int pageNumber) {
        //empty strings says that we request history for whole period
        App.getApi().getHistory(token, "", "", pageNumber, 10).enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {
                if (response.code() == 200 && response.body() != null) {
                    HistoryAdapter adapter = new HistoryAdapter(HistoryActivity.this, response.body().getItems());
                    historyList.setAdapter(adapter);
                } else {
                    Toast.makeText(HistoryActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call<HistoryModel> call, Throwable t) {
                showProgress(false);
                Toast.makeText(HistoryActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Shows the progress UI and hides the content form.
     */
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        historyList.setVisibility(show ? View.GONE : View.VISIBLE);
        historyList.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                historyList.setVisibility(show ? View.GONE : View.VISIBLE);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
