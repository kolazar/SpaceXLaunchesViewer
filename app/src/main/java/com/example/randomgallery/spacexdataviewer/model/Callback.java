package com.example.randomgallery.spacexdataviewer.model;

public interface Callback<T> {

    void onError(Throwable error);

    void onResults(T data);

}
