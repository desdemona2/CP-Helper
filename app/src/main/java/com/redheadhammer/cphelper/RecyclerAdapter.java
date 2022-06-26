package com.redheadhammer.cphelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private final ArrayList<String> items;
    public RecyclerAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,
                                                                    parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.text.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.representation);
        }
    }
}
