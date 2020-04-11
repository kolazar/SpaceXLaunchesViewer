package com.example.randomgallery.spacexdataviewer.model;

import com.example.randomgallery.spacexdataviewer.model.network.LaunchNetworkEntity;

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

    public Launch(LaunchNetworkEntity entity){
        this(
            entity.getFlight_number(),
            entity.getMission_name(),
            entity.getLaunch_date_utc(),
            entity.getDetails()
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
