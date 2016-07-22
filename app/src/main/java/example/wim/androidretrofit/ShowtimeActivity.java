package example.wim.androidretrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    public static void start(Context context, MovieData movieData) {
        Intent intent = new Intent(context, ShowtimeActivity.class);
        intent.putExtra(ShowtimeActivity.class.getSimpleName(), movieData);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtime);

        movieData = getIntent().getParcelableExtra(ShowtimeActivity.class.getSimpleName());

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
}
