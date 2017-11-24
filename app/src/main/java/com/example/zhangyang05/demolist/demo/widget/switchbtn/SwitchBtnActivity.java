package com.example.zhangyang05.demolist.demo.widget.switchbtn;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import com.example.zhangyang05.demolist.R;
import com.example.zhangyang05.demolist.base.BaseActivityCompat;

public class SwitchBtnActivity extends BaseActivityCompat {
    SwitchButton mSwitchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_btn);
        mSwitchButton = findViewById(R.id.mSwitchButton);
        mSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("isChecked: ", ""+isChecked);
            }
        });
    }
}
