package com.existek.existek.existek.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.existek.existek.existek.database.entity.Photo;

@Database(entities = {Photo.class}, version = 1, exportSchema = false)
public abstract class PhotosDatabase extends RoomDatabase {

    private static volatile PhotosDatabase instance;

    public static PhotosDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (PhotosDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                               PhotosDatabase.class, "photos_database")
                               .build();
                }
            }
        }
        return instance;
    }

    public abstract PhotosDao photosDao();
}
