package com.example.zhangyang05.demolist.demo.widget.autoResizeTextView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhangyang05.demolist.R;
import com.example.zhangyang05.demolist.base.BaseActivityCompat;

public class AutoResizeActivity extends BaseActivityCompat {
    TextView textView;
    EditText edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_resize);
        textView = findViewById(R.id.textView);
        edit_text = findViewById(R.id.edit_text);
        edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                textView.setText(editable.toString());
            }
        });
    }
}
