package com.example.randomgallery.spacexdataviewer.model.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpaceXAPI {

    @GET("v3/launches/past")
    Call<List<LaunchNetworkEntity>> getLaunches();
}
