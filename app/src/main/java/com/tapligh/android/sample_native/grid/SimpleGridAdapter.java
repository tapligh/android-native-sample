package com.tapligh.android.sample_native.grid;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tapligh.android.sample_native.R;

import java.util.ArrayList;
import java.util.List;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/Jan/29 11:04
 */
public class SimpleGridAdapter extends RecyclerView.Adapter<SimpleGridAdapter.SimpleViewHolder> {

    private List<String> items;

    public SimpleGridAdapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_grid_normal, parent, false);
        return new SimpleViewHolder(rootView);
    }

    @Override
    public long getItemId(int position) {
        return -System.identityHashCode(items.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder simpleViewHolder, int position) {
        simpleViewHolder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<String> data) {
        this.items.addAll(data);
        notifyDataSetChanged();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }

        void onBind(String value) {
            title.setText(value);
        }
    }


}
