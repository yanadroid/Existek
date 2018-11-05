package com.existek.existek.existek;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.existek.existek.existek.database.entity.Photo;
import com.existek.existek.existek.databinding.ItemPhotoBinding;

import java.util.LinkedList;
import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    private PhotoClickListener clickListener;
    private List<Photo> items;

    public PhotosAdapter(PhotoClickListener listener) {
        items = new LinkedList<>();
        this.clickListener = listener;
    }

    public void setPhotos(List<Photo> photos) {
        this.items = photos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemPhotoBinding binding = ItemPhotoBinding.inflate(inflater, viewGroup, false);
        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder photoViewHolder, int i) {
          photoViewHolder.bind(items.get(i));
          photoViewHolder.binding.getRoot().setOnClickListener(view -> { clickListener.onSharePhoto(items.get(i).getRes());});
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {

         private ItemPhotoBinding binding;

         public PhotoViewHolder(@NonNull ItemPhotoBinding binding) {
             super(binding.getRoot());
             this.binding = binding;
         }

         public void bind(Photo photo) {
             Glide.with(binding.getRoot().getContext())
                     .load(photo.getRes())
                     .transition(DrawableTransitionOptions.withCrossFade())
                     .into(binding.ivPhoto);
         }
    }

    public interface PhotoClickListener {
        void onSharePhoto(String path);
    }
}
