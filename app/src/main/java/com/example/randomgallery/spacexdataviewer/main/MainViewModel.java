package com.example.randomgallery.spacexdataviewer.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.randomgallery.spacexdataviewer.model.Launch;
import com.example.randomgallery.spacexdataviewer.model.Result;

import java.util.List;

import static com.example.randomgallery.spacexdataviewer.model.Result.Status.EMPTY;
import static com.example.randomgallery.spacexdataviewer.model.Result.Status.ERROR;
import static com.example.randomgallery.spacexdataviewer.model.Result.Status.LOADING;
import static com.example.randomgallery.spacexdataviewer.model.Result.Status.SUCCESS;


public class MainViewModel extends ViewModel {

    private Result<List<Launch>> launchesResult = Result.empty();
    private MutableLiveData<ViewState> stateLiveData = new MutableLiveData<>();

    {
        updateViewState(Result.loading());
    }

    public LiveData<ViewState> getViewState() {
        return stateLiveData;
    }

    public void getLaunches(){

    }

    private void updateViewState(Result<List<Launch>> launchesResult){
        ViewState state = new ViewState();
        state.launches = launchesResult.getData();
        state.showEmptyHint = launchesResult.getStatus() == EMPTY;
        state.showError = launchesResult.getStatus() == ERROR;
        state.showList = launchesResult.getStatus() == SUCCESS;
        state.showProgress = launchesResult.getStatus() == LOADING;
        state.enableGetButton = launchesResult.getStatus() != LOADING;
        stateLiveData.setValue(state);
    }

    static class ViewState {
        private boolean showEmptyHint;
        private boolean showList;
        private boolean showError;
        private boolean showProgress;
        private boolean enableGetButton;
        private List<Launch> launches;

        public boolean isEnableGetButton() {
            return enableGetButton;
        }

        public boolean isShowEmptyHint() {
            return showEmptyHint;
        }

        public boolean isShowList() {
            return showList;
        }

        public boolean isShowError() {
            return showError;
        }

        public boolean isShowProgress() {
            return showProgress;
        }

        public List<Launch> getLaunches() {
            return launches;
        }
    }

}
