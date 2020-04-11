package com.example.randomgallery.spacexdataviewer.model;

import com.annimon.stream.Stream;
import com.example.randomgallery.spacexdataviewer.logger.Logger;
import com.example.randomgallery.spacexdataviewer.model.network.LaunchNetworkEntity;
import com.example.randomgallery.spacexdataviewer.model.network.SpaceXAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SpaceXService {

    private SpaceXAPI spaceXAPI;
    private Logger logger;

    public SpaceXService(SpaceXAPI spaceXAPI, Logger logger) {
        this.spaceXAPI = spaceXAPI;
        this.logger = logger;
    }

    public Cancellable getLaunches(Callback<List<Launch>> callback){
        Call<List<LaunchNetworkEntity>> call = spaceXAPI.getLaunches();
        call.enqueue(new retrofit2.Callback<List<LaunchNetworkEntity>>() {
            @Override
            public void onResponse(Call<List<LaunchNetworkEntity>> call, Response<List<LaunchNetworkEntity>> response) {
                if(response.isSuccessful()){
                    List<LaunchNetworkEntity> entities = response.body();
                    callback.onResults(convertToLaunches(entities));
                }else {
                    callback.onError(new RuntimeException("Error!"));
                }
            }

            @Override
            public void onFailure(Call<List<LaunchNetworkEntity>> call, Throwable t) {
                callback.onError(new RuntimeException("Network error!"));
            }
        });
        return call::cancel;
    }

    private List<Launch> convertToLaunches(List<LaunchNetworkEntity> entities){
        return Stream.of(entities).map(Launch::new).toList();
    }
}
