package com.example.zhangyang05.demolist.demo.nested_scrolling;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangyang05.demolist.R;

/**
 * Created by zhangyang131 on 2017/11/2.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.text.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.text);
        }
    }
}
