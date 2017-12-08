package com.example.zhangyang05.demolist.demo.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ppddialog.PPDBaseDialog;
import com.example.ppddialog.PPDDialogBuilder;
import com.example.zhangyang05.demolist.R;
import com.example.zhangyang05.demolist.base.BaseActivityCompat;
import com.example.zhangyang05.demolist.demo.expandablerecyclerview.MyAdapter;

public class PPDDialogActivity extends BaseActivityCompat implements DialogInterface.OnClickListener {
    CheckBox checkbox_custom;
    CheckBox checkBox_title;
    CheckBox checkBox_msg;
    CheckBox checkBox_btn1;
    CheckBox checkBox_btn2;
    CheckBox checkBox_btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppddialog);
        Button button = findViewById(R.id.button);
        checkbox_custom = findViewById(R.id.checkbox_custom);
        checkBox_title = findViewById(R.id.checkbox_title);
        checkBox_msg = findViewById(R.id.checkbox_msg);
        checkBox_btn1 = findViewById(R.id.checkbox_btn1);
        checkBox_btn2 = findViewById(R.id.checkbox_btn2);
        checkBox_btn3 = findViewById(R.id.checkbox_btn3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show3();
            }
        });
    }


    private void show1() {
        PPDDialogBuilder builder = new PPDDialogBuilder(this);
        builder.setTitle("质保转款").setMessage(R.string.tip_adsf).setPositive("我知道了", null);
        builder.create().show();
    }

    private void show2() {
        PPDDialogBuilder builder = new PPDDialogBuilder(this);
        builder.setTitle("服务费").setMessage("拍拍贷为借款人提供服务，向借款人收取的费用。").setPositive("我知道了", null);
        builder.create().show();
    }

    private void show3() {
        PPDDialogBuilder builder = new PPDDialogBuilder(this);
        builder.setTitle("还款计划").setCustomMessageLayoutRes(R.layout.recyclerview_layout).setPositive("确认", null);
        PPDBaseDialog dialog = builder.create();
        View root = dialog.getRootView();
        LinearLayout container = root.findViewById(R.id.content_container);
        container.setPadding(container.getPaddingLeft(), container.getPaddingTop(), container.getPaddingRight(),0);
        RecyclerView recyclerView =  root.findViewById(R.id.mRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter());
        dialog.show();
    }


    private void show() {
        PPDDialogBuilder builder = new PPDDialogBuilder(this);
        if (checkBox_title.isChecked()) {
            builder.setTitle("title");
            if (checkbox_custom.isChecked()) {
                builder.setTitleColor(0xffff0000);
                builder.setTitleSize(50);
                builder.setTitleBold(false);
            }
        }
        if (checkBox_msg.isChecked()) {
            builder.setMessage("messagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessagemessage");
            if (checkbox_custom.isChecked()) {
                builder.setMessageBold(true);
                builder.setMessageColor(0xff00ffff);
                builder.setMessageSize(10);
            } else {
//                builder.setCustomMessageLayoutRes(R.layout.dialog_common_auto_repay);
                builder.setMessage("asdjhfakjhdsfkahdsflkjahdsflkjahdsflkjhadlfjhaldskjfhalkjdsfhalkjdshfakjhdsfkajhdsfakjhdsflkajhdsfkjahsdf");
            }
        }
        if (checkBox_btn1.isChecked()) {
            builder.setPositive("不开启，继续借款", this);
            if (checkbox_custom.isChecked()) {
                builder.setPositiveBold(false);
                builder.setPositiveButtonColor(0xff00f066);
                builder.setPositiveButtonSize(18);
            }
        }
        if (checkBox_btn2.isChecked()) {
            builder.setNegative("Negative", this);
            if (checkbox_custom.isChecked()) {
                builder.setNegativeBold(false);
                builder.setNegativeButtonColor(0xffffff00);
                builder.setNegativeButtonSize(16);
            }
        }
        if (checkBox_btn3.isChecked()) {
            builder.setNeutral("Neutral", this);
            if (checkbox_custom.isChecked()) {
                builder.setNeutralBold(false);
                builder.setNeutralButtonColor(0xff569978);
                builder.setNeutralButtonSize(20);
            }
        }
        builder.create().show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(this, "which:" + which, Toast.LENGTH_SHORT).show();
    }
}
