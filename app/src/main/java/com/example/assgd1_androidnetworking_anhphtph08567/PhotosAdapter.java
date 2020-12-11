package com.example.assgd1_androidnetworking_anhphtph08567;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosHolder> {
    List<Photos> photosList;
    Context context;

    public PhotosAdapter(List<Photos> photosList, Context context) {
        this.photosList = photosList;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photos, parent,false);
        PhotosHolder photosHolder = new PhotosHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("title",photosHolder.tvTitle.getText());
                intent.putExtra("photo", photosHolder.photo);
                intent.putExtra("id", photosHolder.id);
                context.startActivity(intent);


            }
        });
        return photosHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosHolder holder, int position) {
        Photos photos = photosList.get(position);

        holder.tvTitle.setText(photos.getTitle());
        holder.photo = photos.getPhoto();
        holder.id = photos.getId();
//        holder.imgPhoto.setMaxHeight(Integer.parseInt(photos.height_z));
//        holder.imgPhoto.setMaxWidth(Integer.parseInt(photos.width_z));
//        holder.imgPhoto.getWidth();
//        holder.imgPhoto.getHeight();
        Picasso.get().load(photos.getPhoto()).into(holder.imgPhoto);



    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public class PhotosHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imgPhoto;
        String photo,width_z,height_z,id;

        public PhotosHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgPhoto = itemView.findViewById(R.id.imgPhotos);
        }
    }

}
