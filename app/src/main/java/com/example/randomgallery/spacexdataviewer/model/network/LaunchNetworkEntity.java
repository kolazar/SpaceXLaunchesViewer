package com.example.randomgallery.spacexdataviewer.model.network;

import java.util.Date;

public class LaunchNetworkEntity {
    private  long flight_number;
    private  String mission_name;
    private  String launch_date_utc;
    private  String details;

    public long getFlight_number() {
        return flight_number;
    }

    public String getMission_name() {
        return mission_name;
    }

    public String getLaunch_date_utc() {
        return launch_date_utc;
    }

    public String getDetails() {
        return details;
    }
}
