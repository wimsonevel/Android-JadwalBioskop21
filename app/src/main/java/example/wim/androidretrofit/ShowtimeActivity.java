package example.wim.androidretrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;

import example.wim.androidretrofit.adapter.ShowtimeListAdapter;
import example.wim.androidretrofit.model.MovieData;

/**
 * Created by Wim on 7/21/16.
 */
public class ShowtimeActivity extends AppCompatActivity {

    private RecyclerView rvShowtime;

    private LinearLayoutManager linearLayoutManager;
    private ShowtimeListAdapter showtimeListAdapter;

    private MovieData movieData;
    private String date;

    public static void start(Context context, MovieData movieData, String date) {
        Intent intent = new Intent(context, ShowtimeActivity.class);
        intent.putExtra(ShowtimeActivity.class.getSimpleName() + ".movie", movieData);
        intent.putExtra(ShowtimeActivity.class.getSimpleName() + ".date", date);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtime);

        movieData = getIntent().getParcelableExtra(ShowtimeActivity.class.getSimpleName() + ".movie");
        date = getIntent().getStringExtra(ShowtimeActivity.class.getSimpleName() + ".date");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml(movieData.getMovie()));
        actionBar.setSubtitle(date);
        actionBar.setDisplayHomeAsUpEnabled(true);

        rvShowtime = (RecyclerView) findViewById(R.id.rv_showtime);

        linearLayoutManager = new LinearLayoutManager(this);
        showtimeListAdapter = new ShowtimeListAdapter(this);

        rvShowtime.setLayoutManager(linearLayoutManager);
        rvShowtime.setAdapter(showtimeListAdapter);

        showtimeListAdapter.addAll(movieData.getJadwal());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
