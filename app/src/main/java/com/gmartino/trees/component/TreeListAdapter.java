package com.gmartino.trees.component;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.gmartino.trees.entity.Tree;

public class TreeListAdapter extends ListAdapter<Tree, TreeViewHolder> {

    private OnClickListener onClickListener;

    public TreeListAdapter(@NonNull DiffUtil.ItemCallback<Tree> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TreeViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TreeViewHolder holder, int position) {
        Tree current = getItem(position);
        holder.bind(current.getName());

        holder.itemView.setOnClickListener(view -> {
            if (onClickListener != null) {
                onClickListener.onClick(position, current);
            }
        });
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Tree tree);
    }

    public static class TreeDiff extends DiffUtil.ItemCallback<Tree> {

        @Override
        public boolean areItemsTheSame(@NonNull Tree oldItem, @NonNull Tree newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Tree oldItem, @NonNull Tree newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

}
