package com.yo.shishkoam.restclienttest.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.yo.shishkoam.restclienttest.App;
import com.yo.shishkoam.restclienttest.Const;
import com.yo.shishkoam.restclienttest.R;
import com.yo.shishkoam.restclienttest.adapters.HistoryAdapter;
import com.yo.shishkoam.restclienttest.api.models.HistoryModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 21.02.2017
 */
public class HistoryActivity extends AppCompatActivity implements Const {

    private ListView historyList;
    private View progressView;
    private int currentPage = 1;
    private int pageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyList = (ListView) findViewById(R.id.history_list);
        progressView = findViewById(R.id.history_progress);

        String token = getIntent().getExtras().getString(TOKEN);

        initNavigation(token);
        requestHistory(token, currentPage);
    }

    private void initNavigation(String token) {
        FloatingActionButton nextFab = (FloatingActionButton) findViewById(R.id.fab_next);
        nextFab.setOnClickListener(view -> {
            if (currentPage < pageCount) {
                currentPage++;
                requestHistory(token, currentPage);
            } else {
                Toast.makeText(HistoryActivity.this, R.string.this_is_last_page, Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton prevFab = (FloatingActionButton) findViewById(R.id.fab_prev);
        prevFab.setOnClickListener(view -> {
            if (currentPage > 1) {
                currentPage--;
                requestHistory(token, currentPage);
            } else {
                Toast.makeText(HistoryActivity.this, R.string.this_is_first_page, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestHistory(String token, int pageNumber) {
        showProgress(true);
        //empty strings says that we request history for whole period
        App.getApi().getHistory(token, "", "", pageNumber, PER_PAGES).enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {
                if (response.code() == SUCCESS_CODE && response.body() != null) {
                    HistoryAdapter adapter = new HistoryAdapter(HistoryActivity.this, response.body().getItems());
                    historyList.setAdapter(adapter);
                    pageCount = response.body().getNumberOfPages();
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
