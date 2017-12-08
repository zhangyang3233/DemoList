package com.example.zhangyang05.demolist.demo.dialog;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.zhangyang05.demolist.R;

public class MaxHeightRecyclerView extends RecyclerView {
    int maxHeight = -1;

    public MaxHeightRecyclerView(Context context) {
        super(context);
    }

    public MaxHeightRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MaxHeightRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightScrollView);
            maxHeight = styledAttrs.getDimensionPixelSize(R.styleable.MaxHeightRecyclerView_maxHeight, -1);
            styledAttrs.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        if (maxHeight != -1 && !isInEditMode()) {
            heightSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthSpec, heightSpec);
    }
}
