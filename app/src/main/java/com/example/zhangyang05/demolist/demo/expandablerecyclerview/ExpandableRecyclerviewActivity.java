package com.example.zhangyang05.demolist.demo.expandablerecyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zhangyang05.demolist.R;
import com.example.zhangyang05.demolist.base.BaseActivityCompat;

public class ExpandableRecyclerviewActivity extends BaseActivityCompat {
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_recyclerview);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter());
    }
}
