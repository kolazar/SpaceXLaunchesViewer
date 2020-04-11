package com.example.randomgallery.spacexdataviewer;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.randomgallery.spacexdataviewer.model.SpaceXService;

import java.lang.reflect.Constructor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    public static final String TAG = ViewModelFactory.class.getSimpleName();

    private SpaceXService spaceXService;

    public ViewModelFactory(SpaceXService spaceXService) {
        this.spaceXService = spaceXService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            Constructor<T> constructor = modelClass.getConstructor(SpaceXService.class);
            return constructor.newInstance(spaceXService);
        } catch(ReflectiveOperationException e){
            Log.e(TAG, "Error ", e);
            RuntimeException wrapper = new RuntimeException();
            wrapper.initCause(e);
            throw wrapper;
        }
    }
}
