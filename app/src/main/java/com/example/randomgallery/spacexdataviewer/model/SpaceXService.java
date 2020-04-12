package com.example.randomgallery.spacexdataviewer.model;

import com.annimon.stream.Stream;
import com.example.randomgallery.spacexdataviewer.logger.Logger;
import com.example.randomgallery.spacexdataviewer.model.network.LaunchNetworkEntity;
import com.example.randomgallery.spacexdataviewer.model.network.SpaceXAPI;

import java.util.Calendar;
import java.util.Date;
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

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR,-5);
        Date fiveYearsFromToday = cal.getTime();

        Call<List<LaunchNetworkEntity>> call = spaceXAPI.getLaunches(fiveYearsFromToday,today);
        call.enqueue(new retrofit2.Callback<List<LaunchNetworkEntity>>() {
            @Override
            public void onResponse(Call<List<LaunchNetworkEntity>> call, Response<List<LaunchNetworkEntity>> response) {
                if(response.isSuccessful()){
                    List<LaunchNetworkEntity> entities = response.body();
                    callback.onResults(convertToLaunches(entities));
                }else {
                    RuntimeException exception = new RuntimeException("Error!");
                    logger.e(exception);
                    callback.onError(exception);
                }
            }

            @Override
            public void onFailure(Call<List<LaunchNetworkEntity>> call, Throwable t) {
                logger.e(t);
                callback.onError(t);
            }
        });
        return call::cancel;
    }

    public Cancellable getLaunchById(long id, Callback<Launch> callback){
        Call<LaunchNetworkEntity> call = spaceXAPI.getLaunchById(id);
        call.enqueue(new retrofit2.Callback<LaunchNetworkEntity>() {
            @Override
            public void onResponse(Call<LaunchNetworkEntity> call, Response<LaunchNetworkEntity> response) {
                if(response.isSuccessful()){
                    callback.onResults(new Launch(response.body()));
                }else{
                    RuntimeException exception = new RuntimeException("Error!");
                    logger.e(exception);
                    callback.onError(exception);
                }
            }

            @Override
            public void onFailure(Call<LaunchNetworkEntity> call, Throwable t) {
                logger.e(t);
                callback.onError(t);
            }
        });
        return call::cancel;
    }

    private List<Launch> convertToLaunches(List<LaunchNetworkEntity> entities){
        return Stream.of(entities).map(Launch::new).toList();
    }
}
