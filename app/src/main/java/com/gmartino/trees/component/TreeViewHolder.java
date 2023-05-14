package com.gmartino.trees.component;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmartino.trees.R;

public class TreeViewHolder extends RecyclerView.ViewHolder {

    private final TextView treeItemView;

    public TreeViewHolder(@NonNull View itemView) {
        super(itemView);
        treeItemView = itemView.findViewById(R.id.home_recycler_text_view);
    }

    public void bind(String text) {
        treeItemView.setText(text);
    }

    static TreeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TreeViewHolder(view);
    }

}
