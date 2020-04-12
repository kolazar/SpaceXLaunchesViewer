package com.example.randomgallery.spacexdataviewer.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.randomgallery.spacexdataviewer.model.network.LaunchNetworkEntity;

import java.util.Date;

@Entity (tableName = "launches")
public class LaunchDBEntity {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "missionName")
    private String missionName;

    @ColumnInfo(name = "launchDate")
    private String launchDate;

    @ColumnInfo(name = "description")
    private String description;

    public LaunchDBEntity() {
    }

    public LaunchDBEntity(LaunchNetworkEntity entity){
        this.id = entity.getFlight_number();
        this.description = entity.getDetails();
        this.launchDate = entity.getLaunch_date_utc();
        this.missionName = entity.getMission_name();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
