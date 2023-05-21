package com.gmartino.trees.component;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.gmartino.trees.entity.UserTree;

public class GardenTreeListAdapter extends ListAdapter<UserTree, GardenTreeViewHolder> {

    private GardenTreeListAdapter.OnClickListener onClickListener;

    public GardenTreeListAdapter(@NonNull DiffUtil.ItemCallback<UserTree> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public GardenTreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GardenTreeViewHolder.create(parent);
    }


    @Override
    public void onBindViewHolder(@NonNull GardenTreeViewHolder holder, int position) {
        UserTree current = getItem(position);
        holder.bind(current.getNickname());

        holder.itemView.setOnClickListener(view -> {
            if (onClickListener != null) {
                onClickListener.onClick(position, current);
            }
        });

    }

    public void setOnClickListener(GardenTreeListAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, UserTree tree);
    }

    public static class TreeDiff extends DiffUtil.ItemCallback<UserTree> {

        @Override
        public boolean areItemsTheSame(@NonNull UserTree oldItem, @NonNull UserTree newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserTree oldItem, @NonNull UserTree newItem) {
            return oldItem.getNickname().equals(newItem.getNickname()) && oldItem.getAddedDate()
                    .equals(newItem.getAddedDate());
        }
    }

}
