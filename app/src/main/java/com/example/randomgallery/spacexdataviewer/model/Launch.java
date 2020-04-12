package com.example.randomgallery.spacexdataviewer.model;

import com.example.randomgallery.spacexdataviewer.model.network.LaunchNetworkEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Launch {
    private final long flightNumber;
    private final String missionName;
    private final Date launchDate;
    private final String details;

    public Launch(long flightNumber, String missionName, Date launchDate, String details) {
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
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateAsString = df.format(launchDate);
        return dateAsString;
    }

    public String getDetails() {
        return details;
    }
}
