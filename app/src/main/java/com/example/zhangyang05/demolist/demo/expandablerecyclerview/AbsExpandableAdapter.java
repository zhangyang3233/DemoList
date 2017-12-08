package com.example.zhangyang05.demolist.demo.expandablerecyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;

public abstract class AbsExpandableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private boolean allIsExpanded = true;
    HashMap<Integer, Boolean> map = new HashMap<>();

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final int[] p = getIndexByPosition(position);
        onBindViewHolder(holder, p[0], p[1]);
        if (p[1] == -1) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int groupIndex = getGroupIndex(p[0]);
                    if (!map.containsKey(p[0])) {
                        map.put(p[0], allIsExpanded);
                    }
                    boolean isExpanded = map.get(p[0]);
                    int itemCount = getItemCount(p[0]);
                    if (itemCount != 0) {
                        if (isExpanded) {
                            notifyItemRangeRemoved(groupIndex + 1, itemCount);
                        } else {
                            notifyItemRangeInserted(groupIndex + 1, itemCount);
                        }
                    }
                    map.put(p[0], !isExpanded);
                }
            });
        } else {
            holder.itemView.setOnClickListener(null);
        }
    }

    private int getGroupIndex(int groupId) {
        int position = 0;
        for (int i = 0; i < groupId; i++) {
            position += isExplanded(i) ? getItemCount(i) : 0;
        }
        position += groupId;
        return position;
    }


    public abstract void onBindViewHolder(VH holder, int groupId, int itemId);

    @Override
    public int getItemCount() {
        int count = 0;
        int groupCount = getGroupCount();
        for (int i = 0; i < groupCount; i++) {
            if (isExplanded(i)) {
                count += getItemCount(i);
            }
        }
        count += groupCount;
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int[] p = getIndexByPosition(position);
        return getItemViewType(p[0], p[1]);
    }

    /**
     * 根据 potision 计算出它属于第几组, 第几个 item
     *
     * @param position [0] 组  [1]item
     * @return
     */
    private int[] getIndexByPosition(int position) {
        int[] p = new int[]{-1, -1};
        name:
        for (int i = 0, index = 0; i < getGroupCount(); i++, index++) {
            if (index == position) {
                p = new int[]{i, -1};
                break;
            }
            if (!isExplanded(i)) {
                continue;
            } else {
                for (int j = 0; j < getItemCount(i); j++) {
                    index++;
                    if (index == position) {
                        p = new int[]{i, j};
                        break name;
                    }
                }
            }
        }
        return p;
    }

    protected abstract int getItemViewType(int groupId, int itemId);

    protected abstract int getGroupCount();

    protected boolean isExplanded(int groupId) {
        if (map.containsKey(groupId)) {
            return map.get(groupId);
        }
        return allIsExpanded;
    }

    protected abstract int getItemCount(int groupId);
}
