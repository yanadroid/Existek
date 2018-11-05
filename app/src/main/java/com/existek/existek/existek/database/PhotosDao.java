package com.existek.existek.existek.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.existek.existek.existek.database.entity.Photo;

import java.util.List;

@Dao
public interface PhotosDao {

    @Insert
    void insert(Photo word);

    @Query("SELECT * from photos_table")
    LiveData<List<Photo>> getAllPhotos();
}
