package com.example.ppddialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public class DialogParams {
	public static final int INVALID = -1;
	public static final int IS_BOLD = 1;
	public static final int IS_NOT_BOLD = 0;

	protected Context mContext;
	protected CharSequence mTitleText;
	protected CharSequence mMessageText;

	protected CharSequence mPositiveButtonText;
	protected CharSequence mNegativeButtonText;
	protected CharSequence mNeutralButtonText;

	protected float mTitleTextSize = INVALID;
	protected float mMessageButtonTextSize = INVALID;
	protected float mPositiveButtonTextSize = INVALID;
	protected float mNegativeButtonTextSize = INVALID;
	protected float mNeutralButtonTextSize = INVALID;

	protected int mTitleIsBold = INVALID;
	protected int mMessageIsBold = INVALID;
	protected int mPositiveIsBold = INVALID;
	protected int mNegativeIsBold = INVALID;
	protected int mNeutralIsBold = INVALID;

	protected int mTitleTextColor = INVALID;
	protected int mMessageButtonTextColor = INVALID;
	protected int mPositiveButtonTextColor = INVALID;
	protected int mNegativeButtonTextColor = INVALID;
	protected int mNeutralButtonTextColor = INVALID;

	protected DialogInterface.OnClickListener mPositiveButtonListener;
	protected DialogInterface.OnClickListener mNegativeButtonListener;
	protected DialogInterface.OnClickListener mNeutralButtonListener;

	protected View mCustomTitleView;
	protected View mCustomMessage;
	protected View mCustomView;
	protected int mCustomTitleLayoutRes = INVALID;
	protected int mCustomMessageLayoutRes = INVALID;
	protected int mCustomLayoutRes = INVALID;

	protected DialogParams(Context context) {
		this.mContext = context;
	}

	protected static DialogInterface.OnClickListener mDefaultButtonListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
		}
	};

}
