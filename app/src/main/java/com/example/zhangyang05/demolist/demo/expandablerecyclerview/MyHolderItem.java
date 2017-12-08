package com.example.zhangyang05.demolist.demo.expandablerecyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zhangyang05.demolist.R;

public class MyHolderItem extends RecyclerView.ViewHolder {
    TextView textView;

    public MyHolderItem(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.mTextView);
    }

    public void settext(String text){
        if(textView != null){
            textView.setText(text);
        }
    }
}
