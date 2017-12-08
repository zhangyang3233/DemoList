/*
 * filename:    IDialogBuilder.java
 * Description:
 * Copyright:   PPD NCB Copyright(c)2017
 * @author:     zhangyang05
 * @version:    1.0
 * Create at:   17-12-6 上午9:46
 *  
 * Modification History:
 * Date         Author      Version     Description
 * ==================================================================
 * 17-12-6 上午9:46     zhangyang05     1.0       1.0 Version
 */

package com.example.ppddialog;

import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.View;

public interface IDialogBuilder {
	/**
	 *
	 * @param unit
	 *            TypedValue.COMPLEX_UNIT_SP or ..
	 * @param size
	 *            value of size
	 */
	IDialogBuilder setTitleSize(int unit, float size);

	IDialogBuilder setTitleSize(float size);

	IDialogBuilder setTitle(CharSequence text);

	IDialogBuilder setTitle(@StringRes int res);

	IDialogBuilder setTitleBold(boolean isBold);

	IDialogBuilder setTitleColor(int color);

	IDialogBuilder setCustomTitle(View customTitleView);

	IDialogBuilder setMessage(CharSequence text);

	IDialogBuilder setMessage(@StringRes int res);

	IDialogBuilder setMessageBold(boolean isBold);

	IDialogBuilder setMessageColor(int color);

	IDialogBuilder setMessageSize(int unit, float size);

	IDialogBuilder setMessageSize(float size);

	IDialogBuilder setCustomTitleLayoutRes(int customTitleLayoutRes);
	IDialogBuilder setCustomMessageLayoutRes(int customMessageLayoutRes);
	IDialogBuilder setCustomMessageView(View customMessageView);

	//	IDialogBuilder setCustomContent(View customTitleView);

	IDialogBuilder setPositive(CharSequence text, DialogInterface.OnClickListener listener);

	IDialogBuilder setPositive(@StringRes int textRes, DialogInterface.OnClickListener listener);

	IDialogBuilder setPositiveButtonColor(int color);

	IDialogBuilder setPositiveButtonSize(int unit, int size);

	IDialogBuilder setPositiveButtonSize(int color);

	IDialogBuilder setPositiveBold(boolean isBold);

	IDialogBuilder setNegative(CharSequence text, DialogInterface.OnClickListener listener);

	IDialogBuilder setNegative(@StringRes int textRes, DialogInterface.OnClickListener listener);

	IDialogBuilder setNegativeButtonColor(int color);

	IDialogBuilder setNegativeButtonSize(int unit, int size);

	IDialogBuilder setNegativeButtonSize(int color);

	IDialogBuilder setNegativeBold(boolean isBold);

	IDialogBuilder setNeutral(CharSequence text, DialogInterface.OnClickListener listener);

	IDialogBuilder setNeutral(@StringRes int textRes, DialogInterface.OnClickListener listener);

	IDialogBuilder setNeutralButtonColor(int color);

	IDialogBuilder setNeutralButtonSize(int unit, int size);

	IDialogBuilder setNeutralButtonSize(int color);

	IDialogBuilder setNeutralBold(boolean isBold);

	IDialogBuilder setCancelable(boolean cancelable);

	IDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener);

	IDialogBuilder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener);

	IDialogBuilder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener);

	IDialogBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside);

	PPDBaseDialog create();
}
