
package com.example.ppddialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class PPDBaseDialog extends Dialog {
    private static final String TAG = PPDBaseDialog.class.getSimpleName();
    private static final float DEFAULT_WIDTH_SCALE = 0.72f;
    private static final float DEFAULT_ALPHA = 0.95f;
    private static final float DEFAULT_DIMAMOUNT = 0.6f;
    private float mWidthScale = DEFAULT_WIDTH_SCALE;
    private float mAlpha = DEFAULT_ALPHA;
    private float mDimamount = DEFAULT_DIMAMOUNT;
    private int mGravtity = Gravity.CENTER;
    private int mDialogWindowAnimStyle;

    View mRootView;
    Context mContext;

    public float getAlpha() {
        return mAlpha;
    }

    public void setAlpha(float alpha) {
        this.mAlpha = alpha;
    }

    public float getDimamount() {
        return mDimamount;
    }

    public void setDimamount(float dimamount) {
        this.mDimamount = dimamount;
    }

    public float getWidthScale() {
        return mWidthScale;
    }

    public void setWidthScale(float widthScale) {
        this.mWidthScale = widthScale;
    }

    public int getGravtity() {
        return mGravtity;
    }

    public void setGravtity(int gravtity) {
        this.mGravtity = gravtity;
    }

    public int getDialogWindowAnimStyle() {
        return mDialogWindowAnimStyle;
    }

    public void setDialogWindowAnimStyle(int dialogWindowAnimStyle) {
        this.mDialogWindowAnimStyle = dialogWindowAnimStyle;
    }

    public View getRootView() {
        return mRootView;
    }

    public PPDBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    public void setTitle(CharSequence text) {
        if (mRootView != null) {
            TextView titleView = (TextView) mRootView.findViewById(R.id.mTitleText);
            if (titleView != null) {
                titleView.setText(text);
            }
        }
    }

    @Override
    public void setTitle(@StringRes int res) {
        this.setTitle(mContext.getResources().getText(res));
    }

    public void setMessage(CharSequence text) {
        if (mRootView != null) {
            TextView messageText = (TextView) mRootView.findViewById(R.id.mMessageText);
            if (messageText != null) {
                messageText.setText(text);
            }
        }
    }

    public void setMessage(int res) {
        this.setMessage(mContext.getResources().getText(res));
    }

    public void setContentView(View rootView) {
        super.setContentView(rootView);
        this.mRootView = rootView;
    }

    public View getPositive() {
        if (mRootView != null) {
            View positive = mRootView.findViewWithTag(DialogInterface.BUTTON_POSITIVE);
            return positive;
        }
        return null;
    }

    public View getNegative() {
        if (mRootView != null) {
            View negative = mRootView.findViewWithTag(DialogInterface.BUTTON_NEGATIVE);
            return negative;
        }
        return null;
    }

    public View getNeutral() {
        if (mRootView != null) {
            View neutral = mRootView.findViewWithTag(DialogInterface.BUTTON_NEUTRAL);
            return neutral;
        }
        return null;
    }

    @Override
    public void show() {
        if (getContext() instanceof Activity && ((Activity) getContext()).isFinishing()) {
            Log.e(TAG, "activity is finished, dialog can't show");
            return;
        }
        super.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setWindowSize();
    }

    /**
     * 设置Dialog窗口的大小
     */
    private void setWindowSize() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager m = ((Activity) mContext).getWindowManager();
        m.getDefaultDisplay().getMetrics(dm);
        // 为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 1.0); //高度设置为屏幕的1.0
        p.width = (int) (dm.widthPixels * mWidthScale); // 宽度设置为屏幕占比
        p.alpha = mAlpha; // 设置本身透明度
        p.dimAmount = mDimamount; // 设置黑暗度
        Window window = getWindow();
        window.setGravity(mGravtity); // 设置dialog显示的位置
        if (mDialogWindowAnimStyle != 0) {
            window.setWindowAnimations(mDialogWindowAnimStyle); // 添加动画
        }
        window.setAttributes(p);
    }

}
