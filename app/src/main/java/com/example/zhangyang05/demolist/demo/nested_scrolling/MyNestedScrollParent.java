package com.example.zhangyang05.demolist.demo.nested_scrolling;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by zhangyang131 on 2017/11/2.
 */

public class MyNestedScrollParent extends LinearLayout implements NestedScrollingParent {

    public MyNestedScrollParent(Context context) {
        super(context);
    }

    public MyNestedScrollParent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollParent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * React to a descendant view initiating a nestable scroll operation, claiming the
     * nested scroll operation if appropriate.
     *
     * 此方法将响应于子视图调用。
     * 视图层次结构中的每个父节点都将有机会通过响应此方法并请求嵌套滚动操作。
     *
     * 此方法可以背viewparent重写，用来表明是否支持嵌套滚动操作
     *
     * @param child Direct child of this ViewParent containing target
     * @param target View that initiated the nested scroll
     * @param axes Flags consisting of {@link ViewCompat#SCROLL_AXIS_HORIZONTAL},
     *                         {@link ViewCompat#SCROLL_AXIS_VERTICAL} or both
     * @return true if this ViewParent accepts the nested scroll operation
     */
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        return false;
    }


    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {

    }


    public void onStopNestedScroll(@NonNull View target) {
    }


    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {
    }


    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
    }


    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }


    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }


    public int getNestedScrollAxes() {
        return 0;
    }
}
