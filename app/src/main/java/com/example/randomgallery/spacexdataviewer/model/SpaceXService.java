package com.example.randomgallery.spacexdataviewer.model;

import com.annimon.stream.Stream;
import com.example.randomgallery.spacexdataviewer.logger.Logger;
import com.example.randomgallery.spacexdataviewer.model.db.LaunchDBEntity;
import com.example.randomgallery.spacexdataviewer.model.db.LaunchDao;
import com.example.randomgallery.spacexdataviewer.model.network.LaunchNetworkEntity;
import com.example.randomgallery.spacexdataviewer.model.network.SpaceXAPI;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import retrofit2.Response;

public class SpaceXService {

    private ExecutorService executorService;
    private LaunchDao launchDao;
    private SpaceXAPI spaceXAPI;
    private Logger logger;

    public SpaceXService(SpaceXAPI spaceXAPI, LaunchDao launchDao,ExecutorService executorService,
                         Logger logger) {
        this.spaceXAPI = spaceXAPI;
        this.launchDao = launchDao;
        this.executorService = executorService;
        this.logger = logger;
    }

    public Cancellable getLaunches(Callback<List<Launch>> callback){

        Future<?> future = executorService.submit(()->{

                List<LaunchDBEntity> entities = launchDao.getLaunches();
                List<Launch> launches = convertToLaunches(entities);
                callback.onResults(launches);

                Calendar cal = Calendar.getInstance();
                Date today = cal.getTime();
                cal.add(Calendar.YEAR, -5);
                Date fiveYearsFromToday = cal.getTime();

            Response<List<LaunchNetworkEntity>> response = null;
            try {
                response = spaceXAPI.getLaunches(fiveYearsFromToday,
                        today).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(response.isSuccessful()){
                    List<LaunchDBEntity> newDbLaunches = networkToDbEntities(response.body());
                    launchDao.updateLaunches(newDbLaunches);
                    callback.onResults(convertToLaunches(newDbLaunches));
                } else{
                    if(!launches.isEmpty()){
                        RuntimeException exception = new RuntimeException("Something happened");
                        logger.e(exception);
                        callback.onError(exception);
                    }
                }

//            catch (Exception e){
//                logger.e(e);
//                callback.onError(e);
//            }
        });

        return new FutureCancellable(future);
    }

    public Cancellable getLaunchById(long id, Callback<Launch> callback){
        Future<?> future = executorService.submit(()->{
            try{
                LaunchDBEntity dbEntity = launchDao.getById(id);
                Launch launch = new Launch(dbEntity);
                callback.onResults(launch);
            }catch(Exception e){
                logger.e(e);
                callback.onError(e);
            }
        });
        return new FutureCancellable(future);
    }

    private List<Launch> convertToLaunches(List<LaunchDBEntity> entities){
        return Stream.of(entities).map(Launch::new).toList();
    }

    private List<LaunchDBEntity> networkToDbEntities(List<LaunchNetworkEntity> entities){
        return Stream.of(entities)
                .map(networkEntity -> new LaunchDBEntity(networkEntity))
                .toList();
    }

    static class FutureCancellable implements Cancellable{
        private Future<?> future;

        public FutureCancellable(Future<?> future) {
            this.future = future;
        }

        @Override
        public void cancel() {
        future.cancel(true);
        }
    }
}
