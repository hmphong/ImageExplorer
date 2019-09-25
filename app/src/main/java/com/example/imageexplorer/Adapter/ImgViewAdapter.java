package com.example.imageexplorer.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.imageexplorer.Model.ImgStorage;
import com.example.imageexplorer.R;

import java.util.ArrayList;

public class ImgViewAdapter extends RecyclerView.Adapter<ImgViewAdapter.ItemHolder> {
    Context context;
    ArrayList<ImgStorage> storages;
    public static int index = 0;

    public ImgViewAdapter(Context context, ArrayList<ImgStorage> storages) {
        this.context = context;
        this.storages = storages;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_image,null);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder,final int position) {


        Glide.with(context)
                .load("file://" + storages.get(position).getPath())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.imgAll);




    }

    @Override
    public int getItemCount() {
        return storages.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        ImageView imgAll;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgAll = itemView.findViewById(R.id.imgAll);
        }
    }
}
