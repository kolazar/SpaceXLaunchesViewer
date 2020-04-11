package com.example.randomgallery.spacexdataviewer;

import androidx.lifecycle.ViewModel;

import com.example.randomgallery.spacexdataviewer.model.SpaceXService;

public class BaseViewModel extends ViewModel {

    private SpaceXService spaceXService;

    public BaseViewModel(SpaceXService spaceXService) {
        this.spaceXService = spaceXService;
    }

    protected final SpaceXService getSpaceXService() {
        return spaceXService;
    }
}
