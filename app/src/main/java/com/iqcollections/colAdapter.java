package com.iqcollections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class colAdapter extends RecyclerView.Adapter<colAdapter.ViewHolder> {
    Context context;
    List<readCollections> lstCollection;
    private final rvCollections rvcoll;

    public colAdapter(Context context, List<readCollections> lstCollection, rvCollections rvcoll) {
        this.context = context;
        this.lstCollection = lstCollection;
        this.rvcoll = rvcoll;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.displaycoll, parent, false);
        return new ViewHolder(v, rvcoll);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        readCollections col = lstCollection.get(position);
        holder.txtView.setText(col.getColName());//setting collection name

        String imageURI = null;
        imageURI = col.getColImgUrl();
        Picasso.get().load(imageURI).into(holder.imgView);//placing image into recycler view
    }

    @Override
    public int getItemCount() {
        return lstCollection.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView txtView;

        public ViewHolder(@NonNull View itemView, rvCollections rvcoll) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgRecycle);
            txtView = itemView.findViewById(R.id.txtRecycle);//setting items and texts
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rvcoll != null) {
                        int pos = getAdapterPosition();//setting adapter postion
                        if (pos != RecyclerView.NO_POSITION) {
                            rvcoll.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

}
