package com.example.randomgallery.spacexdataviewer.model.network;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpaceXAPI {

    @GET("v3/launches/past")
    Call<List<LaunchNetworkEntity>> getLaunches(@Query("start") Date fiveYearsFromToday,
                                                @Query("end") Date today);

    @GET("v3/launches/{{flight_number}}")
    Call<LaunchNetworkEntity>getLaunchById(@Path("flight_number") long id);
}
