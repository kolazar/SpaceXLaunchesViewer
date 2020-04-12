package com.example.randomgallery.spacexdataviewer.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface LaunchDao {

    @Query("SELECT * FROM launches")
    List<LaunchDBEntity> getLaunches();

    @Query("SELECT * FROM launches WHERE id = :id")
    LaunchDBEntity getById(long id);

    @Insert
    void insertLaunches(List<LaunchDBEntity> launches);

    @Delete
    void deleteLaunches(List<LaunchDBEntity> launches);

    @Transaction
    default void updateLaunches(List<LaunchDBEntity> entities){
        deleteLaunches(entities);
        insertLaunches(entities);
    }
}
