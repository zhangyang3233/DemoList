package com.example.zhangyang05.demolist.demo.widget.loopViewPger;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.zhangyang05.demolist.R;

import java.util.ArrayList;
import java.util.List;

public class LooperActivity extends AppCompatActivity {
    private static final long TIME_OFFSET = 2500;
    private static final String TAG = LooperActivity.class.getSimpleName();

    private ConvenientBanner<Banner> convenientBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);
        convenientBanner = findViewById(R.id.banner);
        setupData(createBanner());
    }

    private List<Banner> createBanner() {
        List<Banner> banners = new ArrayList<>();
        for(int i=0;i<3;i++){
            banners.add(new Banner());
        }
        return banners;
    }

    private void setupData(final List<Banner> banners) {
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolderView(R.drawable.news_register_banner);
            }
        }, banners).setPageIndicator(new int[] { R.drawable.banner_default, R.drawable.banner_selector })
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        convenientBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "position"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        convenientBanner.startTurning(TIME_OFFSET);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        convenientBanner.stopTurning();
        super.onStop();
    }
}
