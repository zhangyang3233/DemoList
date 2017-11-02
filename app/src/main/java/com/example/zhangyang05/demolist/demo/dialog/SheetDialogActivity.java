package com.example.zhangyang05.demolist.demo.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhangyang05.demolist.R;
import com.example.zhangyang05.demolist.base.BaseActivityCompat;

public class SheetDialogActivity extends BaseActivityCompat implements View.OnClickListener {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_dialog);
        setupView();
    }

    private void setupView() {
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showDialog();
    }

    void showDialog() {
        new SheetDialog(this).show();
    }

}
