package com.example.cotscoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cotscoapp.Models.ImageDisplayModel;
import com.squareup.picasso.Picasso;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ImageDisplayModel[] mData;
    private LayoutInflater mInflater;
    private OnItemListener onItemListener;

    public RecyclerViewAdapter(Context context, ImageDisplayModel[] data, OnItemListener onItemListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.onItemListener = onItemListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_grid_layout, parent, false);

        return new ViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(mData[position].getUrl()).into(holder.imageView);
        holder.imageTextView.setText(mData[position].getMessage());
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView imageTextView;
        LinearLayout layout;
        OnItemListener onItemListener;

        public ViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);
            layout = itemView.findViewById(R.id.grid_layout_item);
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
