package com.example.randomgallery.spacexdataviewer.logger;

import android.util.Log;

public class AndroidLogger implements Logger {

    private static final String TAG = AndroidLogger.class.getSimpleName();

    @Override
    public void e(Throwable e) {
        Log.e(TAG, "Error!",e );
    }


}
