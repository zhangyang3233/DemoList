package com.example.zhangyang05.demolist.demo.widget.ppdprogress;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.zhangyang05.demolist.R;
import com.example.zhangyang05.demolist.base.BaseActivityCompat;

public class PPDProgressActivity extends BaseActivityCompat implements PPDProgressView.TextRulerFormatter, SeekBar.OnSeekBarChangeListener {
    PPDProgressView p,p2,p3,p4;
    SeekBar s;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppdprogress);
        p = (PPDProgressView) findViewById(R.id.progress_bar);
        p2 = (PPDProgressView) findViewById(R.id.progress_bar2);
        p3 = (PPDProgressView) findViewById(R.id.progress_bar3);
        p4 = (PPDProgressView) findViewById(R.id.progress_bar4);
        p.setTextRuler(this);
        p2.setTextRuler(this);
        p3.setTextRuler(this);
        p4.setTextRuler(this);
        text = (TextView) findViewById(R.id.text);
        s = (SeekBar) findViewById(R.id.seek_bar);
        s.setMax(1000);
        s.setOnSeekBarChangeListener(this);
    }

    @Override
    public String progressTextRuler(int progress, int max) {
        return "￥" + progress;
    }

    @Override
    public String leftTextRuler(int progress, int max) {
        return "￥0";
    }

    @Override
    public String rightTextRuler(int progress, int max) {
        return "￥" + max;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        p.setProgress(progress);
        p2.setProgress(progress);
        p3.setProgress(progress);
        p4.setProgress(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
