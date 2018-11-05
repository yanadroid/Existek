package com.existek.existek.existek;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.existek.existek.existek.database.PhotosDao;
import com.existek.existek.existek.database.PhotosDatabase;
import com.existek.existek.existek.database.entity.Photo;

import java.util.List;

public class PhotosRepository {

    private PhotosDao photosDao;
    private LiveData<List<Photo>> allPhotos;

    PhotosRepository(Application application) {
        PhotosDatabase db = PhotosDatabase.getDatabase(application);
        photosDao = db.photosDao();
        allPhotos = photosDao.getAllPhotos();
    }

    LiveData<List<Photo>> getAllPhotos() {
        return allPhotos;
    }

    public void insert(Photo photo) {
        new InsertAsyncTask(photosDao).execute(photo);
    }

    private static class InsertAsyncTask extends AsyncTask<Photo, Void, Void> {

        private PhotosDao dao;

        InsertAsyncTask(PhotosDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final Photo... params) {
            dao.insert(params[0]);
            return null;
        }
    }
}
