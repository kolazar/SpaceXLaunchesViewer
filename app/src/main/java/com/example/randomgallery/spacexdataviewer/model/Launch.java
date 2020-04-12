package com.example.randomgallery.spacexdataviewer.model;

import com.example.randomgallery.spacexdataviewer.model.db.LaunchDBEntity;
import com.example.randomgallery.spacexdataviewer.model.network.LaunchNetworkEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Launch {
    private final long flightNumber;
    private final String missionName;
    private final String launchDate;
    private final String details;

    public Launch(long flightNumber, String missionName, String launchDate, String details) {
        this.flightNumber = flightNumber;
        this.missionName = missionName;
        this.launchDate = launchDate;
        this.details = details;
    }

    public Launch(LaunchDBEntity entity){
        this(
            entity.getId(),
            entity.getMissionName(),
            entity.getLaunchDate(),
            entity.getDescription()
        );
    }

    public long getFlightNumber() {
        return flightNumber;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public String getDetails() {
        return details;
    }
}
