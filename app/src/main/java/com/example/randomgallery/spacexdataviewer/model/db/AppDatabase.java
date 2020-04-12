package com.example.randomgallery.spacexdataviewer.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {LaunchDBEntity.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract LaunchDao getLaunchDao();
}
