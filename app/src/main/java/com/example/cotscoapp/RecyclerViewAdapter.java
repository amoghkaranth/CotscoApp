package com.example.cotscoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cotscoapp.Models.ImageDisplayModel;
import com.squareup.picasso.Picasso;

/**
 * Adapter used to generate Recycler View to display images and associated text
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ImageDisplayModel[] mImageDisplayModelsList;
    private LayoutInflater mLayoutInflater;
    private OnItemListener onItemListener;

    public RecyclerViewAdapter(Context context, ImageDisplayModel[] ImageDisplayModelsList, OnItemListener onItemListener) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mImageDisplayModelsList = ImageDisplayModelsList;
        this.onItemListener = onItemListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recycler_view_grid_layout, parent, false);
        return new ViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Using Picasso Library to load images
        Picasso.get().load(mImageDisplayModelsList[position].getUrl()).into(holder.imageView);
        holder.imageTextView.setText(mImageDisplayModelsList[position].getMessage());
    }

    @Override
    public int getItemCount() {
        return mImageDisplayModelsList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView imageTextView;
        OnItemListener onItemListener;

        public ViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            imageTextView = itemView.findViewById(R.id.image_text_view);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }

}
