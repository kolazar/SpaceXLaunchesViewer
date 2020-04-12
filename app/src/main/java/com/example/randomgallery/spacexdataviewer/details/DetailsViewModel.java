package com.example.randomgallery.spacexdataviewer.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.randomgallery.spacexdataviewer.BaseViewModel;
import com.example.randomgallery.spacexdataviewer.model.Callback;
import com.example.randomgallery.spacexdataviewer.model.Cancellable;
import com.example.randomgallery.spacexdataviewer.model.Launch;
import com.example.randomgallery.spacexdataviewer.model.Result;
import com.example.randomgallery.spacexdataviewer.model.SpaceXService;

public class DetailsViewModel extends BaseViewModel {

    private MutableLiveData<Result<Launch>> launchLiveData = new MutableLiveData<>();

    {
        launchLiveData.setValue(Result.empty());
    }

    private Cancellable cancellable;

    @Override
    protected void onCleared() {
        super.onCleared();
        if(cancellable != null){
            cancellable.cancel();
        }
    }

    public DetailsViewModel(SpaceXService spaceXService) {
        super(spaceXService);
    }

    public void loadLaunchById(long id){
        launchLiveData.setValue(Result.loading());
        cancellable = getSpaceXService().getLaunchById(id, new Callback<Launch>() {
            @Override
            public void onError(Throwable error) {
                launchLiveData.postValue(Result.error(error));
            }

            @Override
            public void onResults(Launch data) {
                launchLiveData.postValue(Result.success(data));
            }
        });
    }

    public LiveData<Result<Launch>> getResults(){
        return launchLiveData;
    }
}
