package example.wim.androidretrofit.service;

import java.util.concurrent.TimeUnit;

import example.wim.androidretrofit.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wim on 7/19/16.
 */
public class ApiService {

    private ApiInterface apiInterface;

    public ApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(configureTimeouts())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    private OkHttpClient configureTimeouts() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }

    public void getCityList(Callback callback){
        apiInterface.getCity().enqueue(callback);
    }

    public void getMovieList(String id, Callback callback){
        apiInterface.getMovie(id).enqueue(callback);
    }

}
