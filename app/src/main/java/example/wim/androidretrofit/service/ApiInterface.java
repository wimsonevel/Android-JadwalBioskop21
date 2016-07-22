package example.wim.androidretrofit.service;

import example.wim.androidretrofit.model.City;
import example.wim.androidretrofit.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Wim on 7/19/16.
 */
public interface ApiInterface {

    @GET("api/jadwal-bioskop")
    Call<City> getCity();

    @GET("api/jadwal-bioskop")
    Call<Movie> getMovie(
            @Query("id") String id);

}