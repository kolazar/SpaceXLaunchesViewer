package com.example.randomgallery.spacexdataviewer.model;

public class Launch {
    private final int flightNumber;
    private final String missionName;
    private final String launchDate;
    private final String details;

    public Launch(int flightNumber, String missionName, String launchDate, String details) {
        this.flightNumber = flightNumber;
        this.missionName = missionName;
        this.launchDate = launchDate;
        this.details = details;
    }

    public int getFlightNumber() {
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
