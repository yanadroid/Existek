package com.existek.existek.existek;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.existek.existek.existek.database.entity.Photo;
import com.existek.existek.existek.databinding.ActivityMainBinding;
import com.existek.existek.existek.notification.NotificationsManager;
import com.existek.existek.existek.utils.Utils;
import com.mlsdev.rximagepicker.RxImagePicker;
import com.mlsdev.rximagepicker.Sources;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity implements PhotosAdapter.PhotoClickListener {

    private ActivityMainBinding binding;
    private PhotosViewModel viewModel;
    private PhotosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new PhotosViewModel(getApplication());
        binding.buttonGetCapture.setOnClickListener(v -> getCameraCapture());
        setRecycler();
        observePhotos();
        startReminder();
    }

    private void observePhotos() {
        viewModel.getAllPhotos().observe(this, photos -> adapter.setPhotos(photos));
    }

    private void startReminder() {
        NotificationsManager notificationsManager = new NotificationsManager(this);
        notificationsManager.startReminder();
    }

    private void getCameraCapture() {
        RxImagePicker.with(getFragmentManager())
                .requestImage(Sources.CAMERA)
                .map(uri -> handlePhotoUri(uri))
                .subscribe(path -> {
                    if (path != null) {
                        viewModel.insert(new Photo(path));
                    }
                });
    }

    private void setRecycler() {
        adapter = new PhotosAdapter(this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.setAdapter(adapter);
    }

    private String handlePhotoUri(Uri uri) throws URISyntaxException {
        return Utils.getRealPathFromURI(uri, this);
    }

    @Override
    public void onSharePhoto(String path) {
        Utils.shareFile(this, path);
    }
}
