package com.existek.existek.existek;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.existek.existek.existek.database.entity.Photo;

import java.util.List;

public class PhotosViewModel extends ViewModel {

    private PhotosRepository repository;
    private LiveData<List<Photo>> allPhotos;

    public PhotosViewModel(Application application) {
        repository = new PhotosRepository(application);
        allPhotos = repository.getAllPhotos();
    }

    LiveData<List<Photo>> getAllPhotos() {
        return allPhotos;
    }

    public void insert(@NonNull Photo photo) {
        repository.insert(photo);
    }
}
