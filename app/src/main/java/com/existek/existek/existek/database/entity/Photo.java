package com.existek.existek.existek.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "photos_table")
public class Photo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "res")
    private String res;

    public Photo(@NonNull  String res) {
        this.res = res;
    }

    public String getRes() {
        return res;
    }

    public void setRes(@NonNull String res) {
        this.res = res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
