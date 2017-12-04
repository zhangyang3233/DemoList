
package com.example.ppddialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PPDDialogBuilder implements IDialogBuilder {
    private static final int THREE = 3;
    private static final int MESSAGE_MIN_HEIGHT_NO_TITLE_SP = 50;
    private Context mContext;
    private DialogParams p;
    private View mRootView;
    private LayoutInflater inflate;
    private ArrayList<DialogBtnCfg> btnCfgs = new ArrayList<>();
    private int mStyle;
    private boolean mIsCancelable = true;
    private boolean mCanceledOnTouchOutside = true;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private DialogInterface.OnKeyListener mOnKeyListener;

    public PPDDialogBuilder(Context context) {
        this(context, R.style.PPDDialog);
    }

    public PPDDialogBuilder(Context context, int style) {
        this.mContext = context;
        this.mStyle = style;
        this.inflate = LayoutInflater.from(context);
        this.p = new DialogParams(context);
    }

    @Override
    public IDialogBuilder setTitleSize(int unit, float size) {
        p.mTitleTextSize = TypedValue.applyDimension(unit, size, mContext.getResources().getDisplayMetrics());
        return this;
    }

    @Override
    public IDialogBuilder setTitleSize(float size) {
        p.mTitleTextSize = size;
        return this;
    }

    @Override
    public IDialogBuilder setTitle(CharSequence text) {
        p.mTitleText = text;
        return this;
    }

    @Override
    public IDialogBuilder setTitle(int res) {
        p.mTitleText = p.mContext.getText(res);
        return this;
    }

    @Override
    public IDialogBuilder setTitleBold(boolean isBold) {
        p.mTitleIsBold = isBold ? DialogParams.IS_BOLD : DialogParams.IS_NOT_BOLD;
        return this;
    }

    @Override
    public IDialogBuilder setTitleColor(int color) {
        p.mTitleTextColor = color;
        return this;
    }

    @Override
    public IDialogBuilder setCustomTitle(View customTitleView) {
        p.mCustomTitleView = customTitleView;
        return this;
    }

    @Override
    public IDialogBuilder setMessage(CharSequence text) {
        p.mMessageText = text;
        return this;
    }

    @Override
    public IDialogBuilder setMessage(int res) {
        p.mMessageText = p.mContext.getText(res);
        return this;
    }

    @Override
    public IDialogBuilder setMessageBold(boolean isBold) {
        p.mMessageIsBold = isBold ? DialogParams.IS_BOLD : DialogParams.IS_NOT_BOLD;
        return this;
    }

    @Override
    public IDialogBuilder setMessageColor(int color) {
        p.mMessageButtonTextColor = color;
        return this;
    }

    @Override
    public IDialogBuilder setMessageSize(int unit, float size) {
        p.mMessageButtonTextSize = TypedValue.applyDimension(unit, size,
                mContext.getResources().getDisplayMetrics());
        return this;
    }

    @Override
    public IDialogBuilder setCustomTitleLayoutRes(int customTitleLayoutRes) {
        p.mCustomTitleLayoutRes = customTitleLayoutRes;
        return this;
    }

    @Override
    public IDialogBuilder setCustomMessageLayoutRes(int customMessageLayoutRes) {
        p.mCustomMessageLayoutRes = customMessageLayoutRes;
        return this;
    }

    // public IDialogBuilder setCustomLayoutRes(int customLayoutRes) {
    // p.mCustomLayoutRes = customLayoutRes;return this;
    // }

    @Override
    public IDialogBuilder setMessageSize(float size) {
        p.mMessageButtonTextSize = size;
        return this;
    }

    @Override
    public IDialogBuilder setCustomMessageView(View customMessageView) {
        p.mCustomMessage = customMessageView;
        return this;
    }

    @Override
    public IDialogBuilder setPositive(CharSequence text, DialogInterface.OnClickListener listener) {
        p.mPositiveButtonText = text;
        if (listener == null) {
            p.mPositiveButtonListener = p.mDefaultButtonListener;
        } else {
            p.mPositiveButtonListener = listener;
        }

        return this;
    }

    @Override
    public IDialogBuilder setPositive(int textRes, DialogInterface.OnClickListener listener) {
        return setPositive(p.mContext.getResources().getText(textRes), listener);
    }

    @Override
    public IDialogBuilder setPositiveButtonColor(int color) {
        p.mPositiveButtonTextColor = color;
        return this;
    }

    @Override
    public IDialogBuilder setPositiveButtonSize(int unit, int size) {
        p.mPositiveButtonTextSize = TypedValue.applyDimension(unit, size,
                mContext.getResources().getDisplayMetrics());
        return this;
    }

    @Override
    public IDialogBuilder setPositiveButtonSize(int size) {
        p.mPositiveButtonTextSize = size;
        return this;
    }

    @Override
    public IDialogBuilder setPositiveBold(boolean isBold) {
        p.mPositiveIsBold = isBold ? DialogParams.IS_BOLD : DialogParams.IS_NOT_BOLD;
        return this;
    }

    @Override
    public IDialogBuilder setNegative(CharSequence text, DialogInterface.OnClickListener listener) {
        p.mNegativeButtonText = text;
        if (listener == null) {
            p.mNegativeButtonListener = p.mDefaultButtonListener;
        } else {
            p.mNegativeButtonListener = listener;
        }
        return this;
    }

    @Override
    public IDialogBuilder setNegative(int textRes, DialogInterface.OnClickListener listener) {
        return setNegative(p.mContext.getResources().getText(textRes), listener);
    }

    @Override
    public IDialogBuilder setNegativeButtonColor(int color) {
        p.mNegativeButtonTextColor = color;
        return this;
    }

    @Override
    public IDialogBuilder setNegativeButtonSize(int unit, int size) {
        p.mNegativeButtonTextSize = TypedValue.applyDimension(unit, size,
                mContext.getResources().getDisplayMetrics());
        return this;
    }

    @Override
    public IDialogBuilder setNegativeButtonSize(int size) {
        p.mNegativeButtonTextSize = size;
        return this;
    }

    @Override
    public IDialogBuilder setNegativeBold(boolean isBold) {
        p.mNegativeIsBold = isBold ? DialogParams.IS_BOLD : DialogParams.IS_NOT_BOLD;
        return this;
    }

    @Override
    public IDialogBuilder setNeutral(CharSequence text, DialogInterface.OnClickListener listener) {
        p.mNeutralButtonText = text;
        if (listener == null) {
            p.mNeutralButtonListener = p.mDefaultButtonListener;
        } else {
            p.mNeutralButtonListener = listener;
        }
        return this;
    }

    @Override
    public IDialogBuilder setNeutral(int textRes, DialogInterface.OnClickListener listener) {
        return setNeutral(p.mContext.getResources().getText(textRes), listener);
    }

    @Override
    public IDialogBuilder setNeutralButtonColor(int color) {
        p.mNeutralButtonTextColor = color;
        return this;
    }

    @Override
    public IDialogBuilder setNeutralButtonSize(int unit, int size) {
        p.mNeutralButtonTextSize = TypedValue.applyDimension(unit, size,
                mContext.getResources().getDisplayMetrics());
        return this;
    }

    @Override
    public IDialogBuilder setNeutralButtonSize(int size) {
        p.mNeutralButtonTextSize = size;
        return this;
    }

    @Override
    public IDialogBuilder setNeutralBold(boolean isBold) {
        p.mNeutralIsBold = isBold ? DialogParams.IS_BOLD : DialogParams.IS_NOT_BOLD;
        return this;
    }

    @Override
    public IDialogBuilder setCancelable(boolean cancelable) {
        mIsCancelable = cancelable;
        return this;
    }

    @Override
    public IDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        mOnCancelListener = onCancelListener;
        return this;
    }

    @Override
    public IDialogBuilder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
        return this;
    }

    @Override
    public IDialogBuilder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        mOnKeyListener = onKeyListener;
        return this;
    }

    @Override
    public IDialogBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.mCanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    private boolean hasTitle() {
        if (p.mCustomTitleLayoutRes == DialogParams.INVALID && p.mCustomTitleView == null
                && TextUtils.isEmpty(p.mTitleText)) {
            return false;
        }
        return true;
    }

    private boolean hasMessage() {
        if (p.mCustomMessageLayoutRes == DialogParams.INVALID && p.mCustomMessage == null
                && TextUtils.isEmpty(p.mMessageText)) {
            return false;
        }
        return true;
    }

    private void addBtnCfg(DialogBtnCfg cfg) {
        if (cfg != null) {
            btnCfgs.add(cfg);
        }
    }

    private ArrayList<DialogBtnCfg> getBtnList() {
        btnCfgs.clear();
        addBtnCfg(DialogBtnCfg.getNeutralCfg(p));
        addBtnCfg(DialogBtnCfg.getNegativeCfg(p));
        addBtnCfg(DialogBtnCfg.getPositiveCfg(p));
        return btnCfgs;
    }

    private void setBtn(TextView singleView, Dialog dialog) {
        btnCfgs.get(0).set(singleView, dialog);
    }

    private void setBtn(TextView leftView, TextView rightView, Dialog dialog) {
        btnCfgs.get(0).set(leftView, dialog);
        btnCfgs.get(1).set(rightView, dialog);
    }

    private void setBtn(TextView leftView, TextView centerView, TextView rightView, Dialog dialog) {
        btnCfgs.get(0).set(leftView, dialog);
        btnCfgs.get(1).set(centerView, dialog);
        btnCfgs.get(2).set(rightView, dialog);
    }

    @Override
    public PPDBaseDialog create() {
        PPDBaseDialog dialog = new PPDBaseDialog(mContext, mStyle);
        mRootView = inflate.inflate(R.layout.ppd_dialog_base_layout, null);
        if (p.mCustomView != null) {
            ViewGroup contentContainer = (ViewGroup) mRootView.findViewById(R.id.content_container);
            contentContainer.removeAllViews();
            contentContainer.addView(p.mCustomView);
        } else {
            ViewGroup titleContainer = (ViewGroup) mRootView.findViewById(R.id.title_container);
            if (hasTitle()) {
                if (p.mCustomTitleView != null) {
                    titleContainer.removeAllViews();
                    titleContainer.addView(p.mCustomTitleView);
                } else if (p.mCustomTitleLayoutRes != DialogParams.INVALID) {
                    titleContainer.removeAllViews();
                    inflate.inflate(p.mCustomTitleLayoutRes, titleContainer, true);
                } else {
                    TextView mTitleText = (TextView) titleContainer.findViewById(R.id.mTitleText);
                    DialogBtnCfg.getTitleCfg(p).set(mTitleText, dialog);
                }
            } else {
                titleContainer.setVisibility(View.GONE);
            }
            ViewGroup msgContainer = (ViewGroup) mRootView.findViewById(R.id.msg_container);
            if (hasMessage()) {
                if (p.mCustomMessage != null) {
                    msgContainer.removeAllViews();
                    msgContainer.addView(p.mCustomMessage);
                } else if (p.mCustomMessageLayoutRes != DialogParams.INVALID) {
                    msgContainer.removeAllViews();
                    inflate.inflate(p.mCustomMessageLayoutRes, msgContainer, true);
                } else {
                    TextView mMessageText = (TextView) msgContainer.findViewById(R.id.mMessageText);
                    if (!hasTitle()) {
                        mMessageText.setMinHeight(dp2px(MESSAGE_MIN_HEIGHT_NO_TITLE_SP));
                    }
                    DialogBtnCfg.getMessageCfg(p).set(mMessageText, dialog);
                }
            } else {
                msgContainer.setVisibility(View.GONE);
            }
            View gap = mRootView.findViewById(R.id.ppd_dialog_msg_title_gap);
            if (hasTitle() && hasMessage()) {
                gap.setVisibility(View.VISIBLE);
            } else {
                gap.setVisibility(View.GONE);
            }
        }
        int btnCount = getBtnList().size();
        TextView mSubmit = (TextView) mRootView.findViewById(R.id.mSubmit);
        View mSureCancelLayout = mRootView.findViewById(R.id.mSureCancelLayout);
        View mContentDivider = mRootView.findViewById(R.id.mContentDivider);
        TextView mLeftText = (TextView) mRootView.findViewById(R.id.mLeftText);
        TextView mCenterText = (TextView) mRootView.findViewById(R.id.mCenterText);
        TextView mRightText = (TextView) mRootView.findViewById(R.id.mRightText);
        switch (btnCount) {
            case 0:
                mSubmit.setVisibility(View.GONE);
                mSureCancelLayout.setVisibility(View.GONE);
                mContentDivider.setVisibility(View.GONE);
                break;
            case 1:
                mSureCancelLayout.setVisibility(View.GONE);
                setBtn(mSubmit, dialog);
                break;
            case 2:
                mSubmit.setVisibility(View.GONE);
                mCenterText.setVisibility(View.GONE);
                mRootView.findViewById(R.id.vertical_divider).setVisibility(View.GONE);
                setBtn(mLeftText, mRightText, dialog);
                break;
            case THREE:
                mSubmit.setVisibility(View.GONE);
                setBtn(mLeftText, mCenterText, mRightText, dialog);
                break;
            default:
                break;
        }
        dialog.setContentView(mRootView);
        dialog.setCancelable(mIsCancelable);
        dialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
        if (mOnKeyListener != null) {
            dialog.setOnKeyListener(mOnKeyListener);
        }
        dialog.setOnDismissListener(mOnDismissListener);
        dialog.setOnCancelListener(mOnCancelListener);
        return dialog;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }

}
