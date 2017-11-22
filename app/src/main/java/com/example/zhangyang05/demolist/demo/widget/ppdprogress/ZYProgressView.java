/*
 * filename:    PPDProgressView.java
 * Description:
 * Copyright:   PPD NCB Copyright(c)2017
 * @author:     zhangyang05
 * @version:    1.0
 * Create at:   17-10-9 上午11:54
 *
 * Modification History:
 * Date         Author      Version     Description
 * ==================================================================
 * 17-10-9 上午11:54     zhangyang05     1.0       1.0 Version
 */
package com.example.zhangyang05.demolist.demo.widget.ppdprogress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.zhangyang05.demolist.R;

public class ZYProgressView extends View {
    private static final int DEFAULT_BACK_GROUND_PROGRESS_COLOR = 0xffe5e5e5;
    private static final int DEFAULT_PROGRESS_HEIGHT = 6;
    private static final int DEFAULT_TOUCHED_THUMB_COLOR = 0xfff0f0f0;
    private static final int DEFAULT_TEXT_MARGIN = 6;
    private static final int DEFAULT_UP_PROGRESS_TEXT = 12;
    private static final int DEFAULT_DOWN_PROGRESS_TEXT = 12;
    private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_MIN_WIDTH = 100;
    private static final int SHADOW_RADIUS = 8;
    private static final int SHADOW_RADIUS_TOUCHED = 4;
    private static final String MEASURE_TEXT = "Measure";
    private static final int STYLE_PROGRESS = 1;
    private static final int STYLE_SEEKBAR = 2;
    private static final int ANIMTOR_DURATION = 150;

    int mWidth, mHeight;
    Drawable mThumbDrawable;
    float mRadius, mScale;
    RectF mProgressRectF, mViewRect;
    Paint mProgressPaint, mStrokePaint, mBackgroundProgressPaint, mTextPaint, mThumbPaint;
    int mProgressColor, mBackProgressColor, mStrokeColor, mStrokeWidth;
    int mMax, mProgress;
    float mDrawProgress;
    PorterDuffXfermode mPorterDuffXfermode;
    int mAboveTextColor, mAboveTextSize, mFollowTextColor, mFollowTextSize, mTextMargin;
    int mProgressHeight;
    int mStyle;
    float mThumbScale = 2;
    boolean touched;
    boolean mShowProgressText, mTopTextBold = true;
    TextRulerFormatter mTextRulerFormatter;
    OnProgressChangeListener mOnProgressChangeListener;

    float topTextH, topTextW, textMargin, topTextLeft, topTextTop;
    float leftTextH, leftTextW, leftTextLeft, leftTextTop;
    float rightTextH, rightTextW, rightTextLeft, rightTextTop;

    public ZYProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initPaint();
        initTextRuler();
    }

    public OnProgressChangeListener getOnProgressChangeListener() {
        return mOnProgressChangeListener;
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.mOnProgressChangeListener = onProgressChangeListener;
    }

    public TextRulerFormatter getTextRuler() {
        return mTextRulerFormatter;
    }

    public void setTextRuler(TextRulerFormatter textRulerFormatter) {
        this.mTextRulerFormatter = textRulerFormatter;
        postInvalidate();
    }

    private void initTextRuler() {
        mTextRulerFormatter = getDefaultTextRuler();
    }

    private TextRulerFormatter getDefaultTextRuler() {
        return new TextRulerFormatter() {
            @Override
            public String progressTextRuler(int progress, int max) {
                return String.valueOf(progress);
            }

            @Override
            public String leftTextRuler(int progress, int max) {
                return String.valueOf(0);
            }

            @Override
            public String rightTextRuler(int progress, int max) {
                return String.valueOf(max);
            }
        };
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ZYProgressView);
        mThumbDrawable = ta.getDrawable(R.styleable.ZYProgressView_ppd_thumb);
        mProgressColor = ta.getColor(R.styleable.ZYProgressView_ppd_progressColor, getColorPrimary());
        mProgressHeight = ta.getDimensionPixelSize(R.styleable.ZYProgressView_ppd_progress_height,
                dp2px(DEFAULT_PROGRESS_HEIGHT));
        // 纠正防锯齿丢失的高度
        mProgressHeight += 2;
        mBackProgressColor = ta.getColor(R.styleable.ZYProgressView_ppd_back_progressColor,
                DEFAULT_BACK_GROUND_PROGRESS_COLOR);
        mProgress = ta.getColor(R.styleable.ZYProgressView_ppd_progress, 0);
        mDrawProgress = mProgress;
        mMax = ta.getColor(R.styleable.ZYProgressView_ppd_max, DEFAULT_MAX);
        mStrokeColor = ta.getColor(R.styleable.ZYProgressView_ppd_strokeColor, getDarkColorPrimary());
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.ZYProgressView_ppd_strokeWidth, 0);
        mTextMargin = ta.getDimensionPixelSize(R.styleable.ZYProgressView_ppd_text_margin,
                dp2px(DEFAULT_TEXT_MARGIN));
        mStyle = ta.getInt(R.styleable.ZYProgressView_ppd_progress_style, STYLE_PROGRESS);
        // 上面的文字
        mAboveTextColor = ta.getColor(R.styleable.ZYProgressView_ppd_progress_above_text_color, Color.BLACK);
        mAboveTextSize = ta.getDimensionPixelSize(R.styleable.ZYProgressView_ppd_progress_above_text_size,
                sp2px(DEFAULT_UP_PROGRESS_TEXT));
        mShowProgressText = ta.getBoolean(R.styleable.ZYProgressView_ppd_show_text, false);
        mTopTextBold = ta.getBoolean(R.styleable.ZYProgressView_ppd_top_text_bold, true);
        // 下面的文字
        mFollowTextColor = ta.getColor(R.styleable.ZYProgressView_ppd_progress_following_text_color,
                Color.BLACK);
        mFollowTextSize = ta.getDimensionPixelSize(
                R.styleable.ZYProgressView_ppd_progress_following_text_size,
                sp2px(DEFAULT_DOWN_PROGRESS_TEXT));
        ta.recycle();
    }

    protected void initPaint() {
        mBackgroundProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundProgressPaint.setColor(mBackProgressColor);
        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint.setColor(mProgressColor);

        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setColor(mStrokeColor);
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

        mThumbPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureTextY();
        int width = measureDimension(true, widthMeasureSpec);
        int height = measureDimension(false, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    protected void measureTextY() {
        if (!mShowProgressText) {
            textMargin = 0;
            topTextH = 0;
            leftTextH = 0;
            rightTextH = leftTextH;
            return;
        }
        textMargin = getTextMargin();
        topTextH = getTextHeightBySize(mAboveTextSize, MEASURE_TEXT, mTopTextBold);
        leftTextH = getTextHeightBySize(mFollowTextSize, MEASURE_TEXT, false);
        rightTextH = leftTextH;
        leftTextTop = getPaddingTop() + topTextH + textMargin * 2 + mProgressHeight;
        rightTextTop = leftTextTop;
    }

    protected void measureTextX() {
        if (!mShowProgressText) {
            return;
        }
        topTextW = getTextWidthBySize(mAboveTextSize, getTopText(), mTopTextBold);
        topTextLeft = (mWidth - getPaddingLeft() - getPaddingRight()) * getTouchScale() + getPaddingLeft()
                - topTextW / 2 - mRadius;
        if (topTextLeft < getPaddingLeft()) {
            topTextLeft = getPaddingLeft();
        } else if (topTextLeft > mWidth - getPaddingRight() - topTextW) {
            topTextLeft = mWidth - getPaddingRight() - topTextW;
        }
        topTextTop = getPaddingTop();

        leftTextW = getTextWidthBySize(mFollowTextSize, getLeftText(), false);
        rightTextW = getTextWidthBySize(mFollowTextSize, getRightText(), false);
        leftTextLeft = getPaddingLeft();

        rightTextLeft = mWidth - getPaddingRight() - rightTextW;
    }

    protected float getThumbHeight() {
        return mStyle == STYLE_PROGRESS ? 0 : mProgressHeight * mThumbScale;
    }

    protected int measureDimension(boolean isWidth, int measureSpec) {
        int result;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) { // FILL_PARENT or 50dp..
            result = specSize;
        } else {
            result = isWidth ? getMinimumWidth() : getMinimumHeight(); //UNSPECIFIED
            if (specMode == MeasureSpec.AT_MOST) { // WRAP_CONTENT
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    public int getMinimumWidth() {
        return getPaddingLeft() + getPaddingRight() + dp2px(DEFAULT_MIN_WIDTH);
    }

    @Override
    public int getMinimumHeight() {
        return (int) (getPaddingTop() + getPaddingBottom() + topTextH + textMargin * 2 + leftTextH
                + mProgressHeight);
    }

    private int dp2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return Math.round(dipValue * scale);
    }

    private int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return Math.round(spValue * fontScale);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRadius = mProgressHeight / 2.0f;
        mProgressRectF = new RectF(getPaddingLeft(), getPaddingTop() + topTextH + textMargin,
                mWidth - getPaddingRight(), mHeight - getPaddingBottom() - leftTextH - textMargin);
        mViewRect = new RectF(0, 0, mWidth, mHeight);
    }

    private float getTextHeightBySize(int textSize, String text, boolean bold) {
        if (!mShowProgressText) {
            return 0;
        }
        if (text == null) {
            text = MEASURE_TEXT;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setFakeBoldText(bold);
        textPaint.setTextSize(textSize);
        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        int height = rect.height(); //文字高
        return height;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    private float getTextWidthBySize(int textSize, String text, boolean bold) {
        if (!mShowProgressText) {
            return 0;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setFakeBoldText(bold);
        textPaint.setTextSize(textSize);
        return textPaint.measureText(text);
    }

    private int getTextMargin() {
        return mShowProgressText ? mTextMargin : 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            mProgress = DEFAULT_MAX / 2;
            mDrawProgress = mProgress;
            mMax = DEFAULT_MAX;
            mScale = mProgress / (float) mMax;
        } else if (mMax == 0) {
            mScale = 0.0f;
        } else {
            mScale = (float) mProgress / (float) mMax;
        }
        drawBackgroundProgressColor(canvas);
        float touchScale = getTouchScale();
        drawProgress(canvas, touchScale, mProgressPaint);
        drawStroke(canvas);
        drawText(canvas);
        drawThumb(canvas, touchScale);

    }

    protected float getTouchScale() {
        return mDrawProgress / getMax();
    }

    protected void drawThumb(Canvas canvas, float scale) {
        if (mStyle == STYLE_PROGRESS) {
            return;
        }
        int thumbLeft, thumbRight, thumbTop, thumbBottom;
        int thumbHeight = mProgressHeight * 2;
        int thumbCenterX = (int) ((mWidth - getPaddingLeft() - getPaddingRight()) * scale + getPaddingLeft());
        int thumbCenterY = (int) (getPaddingTop() + topTextH + textMargin + mProgressHeight / 2);
        if (thumbCenterX - thumbHeight / 2 < 0) {
            thumbCenterX = thumbHeight / 2;
        } else if (thumbCenterX + thumbHeight / 2 > mWidth) {
            thumbCenterX = mWidth - thumbHeight / 2;
        }
        thumbLeft = thumbCenterX - thumbHeight / 2;
        thumbRight = thumbCenterX + thumbHeight / 2;
        thumbTop = thumbCenterY - thumbHeight / 2;
        thumbBottom = thumbCenterY + thumbHeight / 2;
        if (mThumbDrawable != null) {
            mThumbDrawable.setBounds(thumbLeft, thumbTop, thumbRight, thumbBottom);
            mThumbDrawable.draw(canvas);
        } else {
            if (canvas.isHardwareAccelerated()) {
                setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
            if (touched) {
                mThumbPaint.setColor(DEFAULT_TOUCHED_THUMB_COLOR);
                mThumbPaint.setShadowLayer(SHADOW_RADIUS_TOUCHED, 0, 1, Color.BLACK);
            } else {
                mThumbPaint.setColor(Color.WHITE);
                mThumbPaint.setShadowLayer(SHADOW_RADIUS, 0, 2, Color.BLACK);
            }
            canvas.drawCircle(thumbCenterX, thumbCenterY, thumbHeight / 2, mThumbPaint);
        }
    }

    protected void drawText(Canvas canvas) {
        if(!mShowProgressText){
            return;
        }
        measureTextX();
        //绘制顶端文字
        mTextPaint.setTextSize(mAboveTextSize);
        mTextPaint.setColor(mAboveTextColor);
        Rect rect = new Rect();
        mTextPaint.getTextBounds(MEASURE_TEXT, 0, MEASURE_TEXT.length(), rect);
        if (mTopTextBold) {
            mTextPaint.setFakeBoldText(true);
        }
        canvas.drawText(getTopText(), topTextLeft, getPaddingTop() + topTextH, mTextPaint);

        //绘制左侧文字
        mTextPaint.setFakeBoldText(false);
        mTextPaint.setTextSize(mFollowTextSize);
        mTextPaint.setColor(mFollowTextColor);
        mTextPaint.getTextBounds(MEASURE_TEXT, 0, MEASURE_TEXT.length(), rect);
        float y = getPaddingTop() + 2 * textMargin + mProgressHeight + topTextH + leftTextH;
        canvas.drawText(getLeftText(), leftTextLeft, y, mTextPaint);

        //绘制右侧文字
        canvas.drawText(getRightText(), rightTextLeft, y, mTextPaint);
    }

    protected void drawBackgroundProgressColor(Canvas canvas) {
        canvas.drawRoundRect(mProgressRectF, mRadius, mRadius, mBackgroundProgressPaint);
    }

    protected void drawStroke(Canvas canvas) {
        if (mStrokeWidth <= 0) {
            return;
        }
        canvas.drawRoundRect(mProgressRectF, mRadius, mRadius, mStrokePaint);
    }

    //绘制进度条
    protected void drawProgress(Canvas canvas, float scale, Paint paint) {
        if (scale == 0.0f) {
            return;
        }
        Bitmap clipBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas clipCanvas = new Canvas(clipBitmap);

        Bitmap progressBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas progressCanvas = new Canvas(progressBitmap);

        RectF pRect = new RectF(getPaddingLeft(), getPaddingTop() + topTextH + textMargin,
                (mWidth - getPaddingLeft() - getPaddingRight()) * scale + getPaddingLeft(),
                mHeight - getPaddingBottom() - leftTextH - textMargin);

        progressCanvas.drawRoundRect(mProgressRectF, mRadius, mRadius, paint);
        clipCanvas.drawRoundRect(pRect, mRadius, mRadius, paint);
        paint.setXfermode(mPorterDuffXfermode);
        clipCanvas.drawBitmap(progressBitmap, null, mViewRect, paint);
        canvas.drawBitmap(clipBitmap, 0, 0, null);
        paint.setXfermode(null);
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        this.mMax = max;
        postInvalidate();
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        if (this.mProgress != progress) {
            this.mProgress = progress;
            if (!touched) {
                this.mDrawProgress = progress;
            }
            if (mOnProgressChangeListener != null) {
                mOnProgressChangeListener.onProgressChanged(progress);
            }
            postInvalidate();
        }
    }

    private int getColorPrimary() {
        if (isInEditMode()) {
            return Color.BLUE;
        }
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    private int getDarkColorPrimary() {
        if (isInEditMode()) {
            return Color.BLUE;
        }
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }

    public interface TextRulerFormatter {
        String progressTextRuler(int progress, int max);

        String leftTextRuler(int progress, int max);

        String rightTextRuler(int progress, int max);
    }

    private String getTopText() {
        return mTextRulerFormatter.progressTextRuler(mProgress, mMax);
    }

    private String getLeftText() {
        return mTextRulerFormatter.leftTextRuler(mProgress, mMax);
    }

    private String getRightText() {
        return mTextRulerFormatter.rightTextRuler(mProgress, mMax);
    }

    int dx, dy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mStyle == STYLE_PROGRESS) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touched = true;
                dx = (int) event.getX();
                dy = (int) event.getY();
                if (!isAvailableClick(dy)) {
                    return false;
                }
                updateProgressByTouch(dx);
                break;
            case MotionEvent.ACTION_MOVE:
                updateProgressByTouch((int) event.getX());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                touched = false;
                updateProgressByTouch((int) event.getX());
                animateProgress();
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    protected void updateProgressByTouch(int touchX) {
        mDrawProgress = ((touchX - getPaddingLeft()) / (float) (mWidth - getPaddingRight() - getPaddingLeft())
                * mMax);
        if (mDrawProgress < 0) {
            mDrawProgress = 0;
        } else if (mDrawProgress > mMax) {
            mDrawProgress = mMax;
        }
        int progress = Math.round(mDrawProgress);
        setProgress(progress);
        postInvalidate();
    }

    ValueAnimator animator;

    protected void animateProgress() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
        animator = ValueAnimator.ofFloat(mDrawProgress, getProgress());
        animator.setDuration(ANIMTOR_DURATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDrawProgress = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    protected boolean isAvailableClick(int cy) {
        int thumbCenterTop = (int) (getPaddingTop() + topTextH + textMargin);
        int thumbCenterBottom = (int) (getPaddingTop() + topTextH + textMargin + getThumbHeight());
        //		return cy > thumbCenterTop && cy < thumbCenterBottom;
        return true;
    }

    public interface OnProgressChangeListener {
        void onProgressChanged(int progress);
    }

}
