package com.example.zhangyang05.demolist.demo.expandablerecyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangyang05.demolist.R;

public class MyAdapter extends AbsExpandableAdapter<RecyclerView.ViewHolder> {


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int groupId, int itemId) {
        if (itemId == -1) {
            ((MyHolderGroup) holder).settext(groupId+"组");
        } else {
            ((MyHolderItem) holder).settext(groupId + "-" + itemId);
        }
    }

    @Override
    protected int getItemViewType(int groupId, int itemId) {
        if (itemId == -1) {
            return 0;
        }
        return 1;
    }

    @Override
    protected int getGroupCount() {
        return 3;
    }

    @Override
    protected int getItemCount(int groupId) {
        switch (groupId) {
            case 0:
                return 3;
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 5;
            default:
                break;
        }
        return 100;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        if(viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group, null, false);
            return new MyHolderGroup(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, false);
            return new MyHolderItem(view);
        }
    }
}
