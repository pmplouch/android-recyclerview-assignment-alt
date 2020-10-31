package com.ualr.recyclerviewassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

public class AdapterListBasic extends RecyclerView.Adapter
{
    private List<Inbox> mItems;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        static void onItemClick(View view, Inbox obj, int position) {

        }
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBasic(List<Inbox> items, Context context)
    {
        this.mItems = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lyInflater = LayoutInflater.from(parent.getContext());
        View itemView = lyInflater.inflate(R.layout.activity_list_multi_selection, parent, false);
        RecyclerView.ViewHolder vh = new InboxViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Inbox item = mItems.get(position);
        InboxViewHolder inboxViewHolder = (InboxViewHolder) holder;
        inboxViewHolder.message.setText(item.getMessage());
        if(item.isSelected())
        {
            inboxViewHolder.lyt_parent.setBackgroundColor(mContext.getResources().getColor(R.color.overlay_light_20));
        }
        else
        {
            inboxViewHolder.lyt_parent.setBackgroundColor(mContext.getResources().getColor(R.color.grey_5));
        }
    }

    public void toggleItemState(int position)
    {
        mItems.get(position).toggleSelection();
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount()
    {
        return mItems.size();
    }

    private class InboxViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView image;
        public TextView message;
        public View lyt_parent;

        public InboxViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            message = itemView.findViewById(R.id.message);
            lyt_parent = itemView.findViewById(R.id.lyt_parent);

            lyt_parent.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    OnItemClickListener.onItemClick(v, mItems.get(getLayoutPosition()), getLayoutPosition());

                }
            });
        }
    }
}

