package com.lxr.property_animation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lanxingren on 2019-08-06.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }

    private Context mContext;
    private List<String> mDatas;

    public Adapter(List<String> datas) {
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String item = mDatas.get(i);
        viewHolder.tvItem.setText(item);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
