package com.gmartino.trees.component;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.gmartino.trees.R;
import com.gmartino.trees.entity.Tree;

import java.util.List;

public class GardenTreeViewHolder extends RecyclerView.ViewHolder {

    private final TextView treeItemView;

    public GardenTreeViewHolder(@NonNull View itemView) {
        super(itemView);
        treeItemView = itemView.findViewById(R.id.home_recycler_text_view);
    }

    public void bind(String text) {
        treeItemView.setText(text);
    }

    static GardenTreeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new GardenTreeViewHolder(view);
    }

}
