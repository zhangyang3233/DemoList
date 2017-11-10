/*
 * Filename:    ImageHolderView.java
 * Description:
 * Copyright:   PPD NCB Copyright(c)2017
 * @author:     zhangyong02
 * @version:    1.0
 * Create at:   7/28/17 10:32 AM
 *
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 7/28/17 10:32 AM     zhangyong02     1.0       1.0 Version
 */

package com.example.zhangyang05.demolist.demo.widget.loopViewPger;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by zhangyu02 on 2015/12/16.
 */
public class ImageHolderView implements Holder<Banner> {
    public static final int[] colors = new int[]{0xffffcccc, 0xffffffcc, 0xffffccff, 0xffccccff};
    private TextView text;
    private int img_rid = -1;

    public ImageHolderView() {

    }

    public ImageHolderView(int rid) {
        img_rid = rid;
    }

    @Override
    public View createView(Context context) {
        text = new TextView(context);
        text.setGravity(Gravity.CENTER);
        return text;
    }

    @Override
    public void UpdateUI(final Context context, final int position, final Banner banner) {
        text.setClickable(true);
        text.setText("第 " + (position + 1) + " 个banner");
        text.setBackgroundColor(colors[position % colors.length]);
    }

}
