package com.example.zhangyang05.demolist.demo.widget.loopViewPger;

import android.view.View;
import android.view.ViewGroup;

import com.example.zhangyang05.demolist.R;

import java.util.List;

/**
 * Created by Sai on 15/7/29.
 */
public class CBPageAdapter<T> extends RecyclingPagerAdapter{
    protected List<T> mDatas;
    protected CBViewHolderCreator holderCreator;
    private OnItemClickListener onItemClickListener;

    public CBPageAdapter(CBViewHolderCreator holderCreator, List<T> datas) {
        this.holderCreator = holderCreator;
        this.mDatas = datas;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        Holder holder = null;
        if (view == null) {
            holder = (Holder) holderCreator.createHolder();
            view = holder.createView(container.getContext());
            view.setTag(R.id.cb_item_tag,holder);
        } else {
            holder = (Holder<T>) view.getTag(R.id.cb_item_tag);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
        if(mDatas!=null&&!mDatas.isEmpty())
        holder.UpdateUI(container.getContext(), position, mDatas.get(position));
        return view;
    }

    @Override
    public int getCount() {
        if(mDatas==null)return 0;
        return mDatas.size();
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
