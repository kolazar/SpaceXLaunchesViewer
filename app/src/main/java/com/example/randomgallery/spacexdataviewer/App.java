package com.example.randomgallery.spacexdataviewer;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.randomgallery.spacexdataviewer.logger.AndroidLogger;
import com.example.randomgallery.spacexdataviewer.logger.Logger;
import com.example.randomgallery.spacexdataviewer.model.SpaceXService;
import com.example.randomgallery.spacexdataviewer.model.db.AppDatabase;
import com.example.randomgallery.spacexdataviewer.model.db.LaunchDao;
import com.example.randomgallery.spacexdataviewer.model.network.SpaceXAPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String BASE_URL = "https://api.spacexdata.com/";

    private ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate() {
        super.onCreate();

        Logger logger = new AndroidLogger();

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        //TODO: change the converter factory according to the assignment

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpaceXAPI spaceXAPI = retrofit.create(SpaceXAPI.class);

        ExecutorService executorService = Executors.newCachedThreadPool();

        AppDatabase appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database.db")
                .build();

        LaunchDao launchDao = appDatabase.getLaunchDao();

        SpaceXService spaceXService = new SpaceXService(spaceXAPI,launchDao, executorService,logger);
        viewModelFactory = new ViewModelFactory(spaceXService);
    }

    public ViewModelProvider.Factory getViewModelFactory(){
        return viewModelFactory;
    }
}
