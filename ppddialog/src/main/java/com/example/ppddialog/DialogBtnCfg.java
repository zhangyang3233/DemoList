package com.example.ppddialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class DialogBtnCfg {
	public static final int INVALID = -1;
	public static final int INVALID_TAG = 0xff;
	public static final int IS_BOLD = 1;
	public static final int IS_NOT_BOLD = 0;

	protected int mWitch = INVALID_TAG;
	protected CharSequence mText;
	protected float mTextSize = INVALID;
	protected int mIsBold = INVALID;
	protected int mTextColor = INVALID;
	protected DialogInterface.OnClickListener mListener;

	public void set(TextView textView, final Dialog dialog) {
		if (!TextUtils.isEmpty(mText)) {
			textView.setText(mText);
		}
		if (mTextSize != INVALID) {
			textView.setTextSize(mTextSize);
		}
		if (mTextColor != INVALID) {
			textView.setTextColor(mTextColor);
		}
		if (mIsBold != INVALID) {
			textView.setTypeface(Typeface
					.defaultFromStyle(mIsBold == DialogBtnCfg.IS_BOLD ? Typeface.BOLD : Typeface.NORMAL));
		}
		if (mListener != null) {
			textView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mListener.onClick(dialog, mWitch);
				}
			});
		}
		if (mWitch != INVALID_TAG) {
			textView.setTag(mWitch);
		}
	}

	public static DialogBtnCfg getPositiveCfg(DialogParams p) { // 确认
		if (TextUtils.isEmpty(p.mPositiveButtonText)) {
			return null;
		}
		DialogBtnCfg cfg = new DialogBtnCfg();
		cfg.mIsBold = p.mPositiveIsBold;
		cfg.mListener = p.mPositiveButtonListener;
		cfg.mText = p.mPositiveButtonText;
		cfg.mTextColor = p.mPositiveButtonTextColor;
		cfg.mTextSize = p.mPositiveButtonTextSize;
		cfg.mWitch = DialogInterface.BUTTON_POSITIVE;
		return cfg;
	}

	public static DialogBtnCfg getNegativeCfg(DialogParams p) { // 取消
		if (TextUtils.isEmpty(p.mNegativeButtonText)) {
			return null;
		}
		DialogBtnCfg cfg = new DialogBtnCfg();
		cfg.mIsBold = p.mNegativeIsBold;
		cfg.mListener = p.mNegativeButtonListener;
		cfg.mText = p.mNegativeButtonText;
		cfg.mTextColor = p.mNegativeButtonTextColor;
		cfg.mTextSize = p.mNegativeButtonTextSize;
		cfg.mWitch = DialogInterface.BUTTON_NEGATIVE;
		return cfg;
	}

	public static DialogBtnCfg getNeutralCfg(DialogParams p) { // 中间
		if (TextUtils.isEmpty(p.mNeutralButtonText)) {
			return null;
		}
		DialogBtnCfg cfg = new DialogBtnCfg();
		cfg.mIsBold = p.mNeutralIsBold;
		cfg.mListener = p.mNeutralButtonListener;
		cfg.mText = p.mNeutralButtonText;
		cfg.mTextColor = p.mNeutralButtonTextColor;
		cfg.mTextSize = p.mNeutralButtonTextSize;
		cfg.mWitch = DialogInterface.BUTTON_NEUTRAL;
		return cfg;
	}

	public static DialogBtnCfg getTitleCfg(DialogParams p) {
		if (TextUtils.isEmpty(p.mTitleText)) {
			return null;
		}
		DialogBtnCfg cfg = new DialogBtnCfg();
		cfg.mIsBold = p.mTitleIsBold;
		cfg.mText = p.mTitleText;
		cfg.mTextColor = p.mTitleTextColor;
		cfg.mTextSize = p.mTitleTextSize;
		return cfg;
	}

	public static DialogBtnCfg getMessageCfg(DialogParams p) {
		if (TextUtils.isEmpty(p.mMessageText)) {
			return null;
		}
		DialogBtnCfg cfg = new DialogBtnCfg();
		cfg.mIsBold = p.mMessageIsBold;
		cfg.mText = p.mMessageText;
		cfg.mTextColor = p.mMessageButtonTextColor;
		cfg.mTextSize = p.mMessageButtonTextSize;
		return cfg;
	}
}
