package com.example.zhangyang05.demolist.demo.widget.scratch_out_view;

import android.os.Bundle;
import android.widget.Button;

import com.example.zhangyang05.demolist.R;
import com.example.zhangyang05.demolist.base.BaseActivityCompat;


public class ScratchOutActivity extends BaseActivityCompat {
    ScratchOutView mScratchOutView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_out);
//        btn = (Button) findViewById(R.id.btn);
//        mScratchOutView = (ScratchOutView) findViewById(R.id.scratch_out_view);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mScratchOutView.resetView();
//            }
//        });
    }
}
