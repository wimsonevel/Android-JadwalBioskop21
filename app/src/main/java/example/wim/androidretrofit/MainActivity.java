package example.wim.androidretrofit;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import example.wim.androidretrofit.adapter.CityListAdapter;
import example.wim.androidretrofit.listener.RecyclerViewItemClickListener;
import example.wim.androidretrofit.model.City;
import example.wim.androidretrofit.service.ApiService;
import example.wim.androidretrofit.util.DividerItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewItemClickListener {

    private RecyclerView rvCity;
    private SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager linearLayoutManager;
    private CityListAdapter cityListAdapter;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCity = (RecyclerView) findViewById(R.id.rv_city);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);

        linearLayoutManager = new LinearLayoutManager(this);
        cityListAdapter = new CityListAdapter(this);
        cityListAdapter.setRecyclerViewItemClickListener(this);

        rvCity.setLayoutManager(linearLayoutManager);
        rvCity.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvCity.setAdapter(cityListAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        loadData();
    }

    private void loadData(){
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });

        apiService = new ApiService();
        apiService.getCityList(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                City city = (City) response.body();

                if(city != null) {
                    if(city.getStatus().equals("success")) {
                        cityListAdapter.addAll(city.getData());
                    }else{
                        Toast.makeText(MainActivity.this, city.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    Log.i("STATUS", city.getStatus());
                }else{
                    Toast.makeText(MainActivity.this, "No Data!", Toast.LENGTH_LONG).show();
                }

                if (swipeRefreshLayout != null)
                    swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if(t instanceof SocketTimeoutException) {
                    Toast.makeText(MainActivity.this, "Request Timeout. Please try again!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Connection Error!", Toast.LENGTH_LONG).show();
                }
                Log.i("FAILURE", t.toString());

                if (swipeRefreshLayout != null)
                    swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void refreshData(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                cityListAdapter.clear();
                loadData();
            }
        });
    }

    @Override
    public void onItemClick(int position, View view) {
        MovieActivity.start(this, cityListAdapter.getItem(position));
    }
}
