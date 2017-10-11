package com.example.zhangyang05.demolist.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;


public class BaseActivityCompat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reSetTitle(this);
    }

    public static void reSetTitle(Activity activity) {
        String st =  activity.getTitle().toString();
        if (!TextUtils.isEmpty(st)) {
            String[] sp = st.split("/");
            if (sp.length > 0) {
                activity.setTitle(sp[sp.length - 1]);
            }
        }
    }
}