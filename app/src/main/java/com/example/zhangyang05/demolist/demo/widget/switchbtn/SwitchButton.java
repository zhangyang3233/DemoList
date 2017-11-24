package com.example.zhangyang05.demolist.demo.widget.switchbtn;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;

import com.example.zhangyang05.demolist.R;


/**
 * Created by zhangyang131 on 2016/11/15.
 */

public class SwitchButton extends CompoundButton implements ValueAnimator.AnimatorUpdateListener {
  private static final float DEFAULT_L = 0.6f;
  private static final int DEFAULT_WIDTH = 149;
  private static final int DEFAULT_HEIGHT = (int) (DEFAULT_WIDTH * DEFAULT_L);
  private static final int ANIMATION_DURATION = 200;

  private static final boolean DEFAULT_IS_CHECKED = true;
  private static final int DEFAULT_ON_COLOR = 0xff53d769;
  private static final int DEFAULT_OFF_COLOR = 0xffdddddd;
  private static final int DEFAULT_BTN_COLOR = 0xffffffff;
  private static final float DEFAULT_STROKE_WIDTH = 2;

  private OnCheckedChangeListener mOnCheckedChangeListener;
  private int fixPaddingTop;
  private int fixPaddingLeft;
  private int fixPaddingRight;
  private int fixPaddingBottom;
  private ValueAnimator anim;

  // 初始化值
  private float mSwitchValue = DEFAULT_IS_CHECKED ? 1 : 0; // 1:开 0：关
  private boolean isChecked = DEFAULT_IS_CHECKED;
  private int onColor = DEFAULT_ON_COLOR; // 打开颜色
  private int offColor = DEFAULT_OFF_COLOR; // 关闭颜色
  private int btnColor = DEFAULT_BTN_COLOR; // 按钮颜色
  float btnStrokeWidth = DEFAULT_STROKE_WIDTH; // 按钮边框宽度

  // 按钮相关
  float btnTop;
  float btnBottom;
  float btnRadius;
  float btnStartcx;
  float btnEndcx;
  float btnCenterY;

  private final Paint paint = new Paint();
  private final Path sPath = new Path();

  private int mWidth, mHeight;
  private float sHeight;
  private float sLeft, sTop, sRight, sBottom;
  private float sCenterY;

  public SwitchButton(Context context) {
    super(context);
    init(null);
  }

  public SwitchButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs);
  }

  private void init(AttributeSet attrs) {
    if (attrs != null) {
      TypedArray typedArray =
          getContext().obtainStyledAttributes(attrs, R.styleable.SwitchButton);
      onColor = typedArray.getColor(R.styleable.SwitchButton_onColor, DEFAULT_ON_COLOR);
      offColor = typedArray.getColor(R.styleable.SwitchButton_offColor, DEFAULT_OFF_COLOR);
      btnColor = typedArray.getColor(R.styleable.SwitchButton_btnColor, DEFAULT_BTN_COLOR);
      btnStrokeWidth =
          typedArray.getDimension(R.styleable.SwitchButton_btnStrokeWidth, DEFAULT_STROKE_WIDTH);
      isChecked = typedArray.getBoolean(R.styleable.SwitchButton_checked, DEFAULT_IS_CHECKED);
      mSwitchValue = isChecked ? 1 : 0;
      typedArray.recycle();
    }
    setLayerType(LAYER_TYPE_SOFTWARE, null); // 绘制阴影要关闭硬件加速
    isChecked = mSwitchValue == 1 ? true : false;
  }

  public void setOnCheckedChangeListener(OnCheckedChangeListener mOnCheckedChangeListener) {
    this.mOnCheckedChangeListener = mOnCheckedChangeListener;
  }

  private void setSwitchValue(float switchValue) {
    this.mSwitchValue = switchValue;
    if (mSwitchValue == 1 && !isChecked) {
      isChecked = true;
      notifyCheckChanged();
    } else if (mSwitchValue == 0 && isChecked) {
      isChecked = false;
      notifyCheckChanged();
    }
  }

  private void notifyCheckChanged() {
    if (mOnCheckedChangeListener != null) {
      mOnCheckedChangeListener.onCheckedChanged(this, isChecked);
    }
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    getParent().requestDisallowInterceptTouchEvent(true);
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public void setChecked(boolean checked) {
    setChecked(checked, false);
  }


  public void setChecked(boolean checked, boolean withAnim) {
    if(isChecked == checked){
      return;
    }
    stopAnimation();
    if (withAnim) {
      startMyAnimation(true);
    } else {
      setSwitchValue(checked ? 1 : 0);
      invalidate();
    }
  }

  @Override
  public boolean isChecked() {
    return isChecked;
  }

  /**
   * 1.精确模式（MeasureSpec.EXACTLY）
   * 在这种模式下，尺寸的值是多少，那么这个组件的长或宽就是多少。 (给定尺寸，例如：50dp 或者 match_parent)
   * 2.最大模式（MeasureSpec.AT_MOST） （WRAP_CONTENT）
   * 这个也就是父组件，能够给出的最大的空间，当前组件的长或宽最大只能为这么大，当然也可以比这个小。
   * 3.未指定模式（MeasureSpec.UNSPECIFIED）（一般用不到）
   * 这个就是说，当前组件，可以随便用空间，不受限制。
   *
   * @param widthMeasureSpec
   * @param heightMeasureSpec
   */
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int mWidth, mHeight;
    int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
    int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
    int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
    int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
    if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode != MeasureSpec.EXACTLY) { // 宽度是精确值高度不是
      mWidth = widthSpecSize;
      mHeight = (int) (widthSpecSize * DEFAULT_L) + getPaddingTop() + getPaddingBottom();
    } else if (heightSpecMode == MeasureSpec.EXACTLY && widthSpecMode != MeasureSpec.EXACTLY) { // 高度是精确值宽度不是
      mHeight = heightSpecSize;
      mWidth = (int) (mHeight / DEFAULT_L) + getPaddingLeft() + getPaddingRight();
    } else if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) { // 都是精确值
      mWidth = widthSpecSize + getPaddingLeft() + getPaddingRight();
      mHeight = heightSpecSize + getPaddingTop() + getPaddingBottom();
    } else { // 都不是精确值
      mWidth = DEFAULT_WIDTH + getPaddingLeft() + getPaddingRight();
      mHeight = DEFAULT_HEIGHT + getPaddingTop() + getPaddingBottom();
    }
    setMeasuredDimension(mWidth, mHeight);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    initFixPadding(w, h);
    mWidth = w; // 视图自身宽度
    mHeight = h; // 视图自身高度
    sLeft = getPaddingLeft() + fixPaddingLeft;// 田径场 左和上的坐标
    sTop = getPaddingTop() + fixPaddingTop;
    sRight = mWidth - getPaddingRight() - fixPaddingRight; // 田径场 右占自身的全部
    sBottom = mHeight - getPaddingBottom() - fixPaddingBottom; // 田径场底部 占全身的百分之八十，
    // 下面预留百分之二十的空间画按钮阴影。
    sHeight = sBottom - sTop; // 田径场的高度
    sCenterY = (sBottom + sTop) / 2; // 田径场的Y轴中心坐标

    RectF sRectF = new RectF(sLeft, sTop, sLeft+(sBottom - sTop), sBottom);
    sPath.arcTo(sRectF, 90, 180);
    sRectF.left = sRight - (sBottom - sTop);
    sRectF.right = sRight;
    sPath.arcTo(sRectF, 270, 180);
    sPath.close(); // path准备田径场的路径

    // 按钮
    btnTop = sTop + btnStrokeWidth;
    btnBottom = sBottom - btnStrokeWidth;
    btnRadius = (btnBottom - btnTop) / 2;
    btnStartcx = sLeft + btnStrokeWidth + btnRadius;
    btnEndcx = sRight - btnStrokeWidth - btnRadius;
    btnCenterY = sCenterY;
  }

  private void initFixPadding(int w, int h) {
    int contentW, contentH;
    contentW = w - getPaddingLeft() - getPaddingRight();
    contentH = h - getPaddingTop() - getPaddingBottom();
    if ((contentW * DEFAULT_L) < h) {
      fixPaddingBottom = fixPaddingTop = (contentH - (int) (contentW * DEFAULT_L)) / 2;
      fixPaddingLeft = fixPaddingRight = 0;
    } else if ((contentH / DEFAULT_L) < w) {
      fixPaddingLeft = fixPaddingRight = (contentW - (int) (contentH / DEFAULT_L)) / 2;
      fixPaddingBottom = fixPaddingTop = 0;
    } else {
      fixPaddingBottom = fixPaddingTop = fixPaddingLeft = fixPaddingRight = 0;
    }
    fixPaddingBottom += 5; // 阴影部分
  }


  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    // 画出田径场
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(getUndueColor());
    canvas.drawPath(sPath, paint);
    paint.reset();

    // 画出田径场覆盖背景
    canvas.save();
    canvas.scale(getBgScale(), getBgScale(), sRight - btnRadius, sCenterY);
    paint.setAntiAlias(true);
    paint.setColor(offColor);
    canvas.drawPath(sPath, paint);
    canvas.restore();
    paint.reset();

    // 画出按钮
    canvas.save();
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(btnColor);
    paint.setShadowLayer(5, 0, 2, Color.GRAY);
    canvas.drawCircle(getBtnCX(), btnCenterY, btnRadius, paint); // 按钮白底
    // paint.setStyle(Paint.Style.STROKE);
    // paint.setColor(getOnColor());
    // paint.setStrokeWidth(btnStrokeWidth);
    // canvas.drawCircle(getBtnCX(), btnCenterY, btnRadius, paint); // 按钮灰边
    paint.reset();
    canvas.restore();
  }

  private int getUndueColor() {
    ArgbEvaluator argbE = new ArgbEvaluator();
    return (int) argbE.evaluate(mSwitchValue, offColor, onColor);
  }

  /**
   * 获取按钮中心X坐标
   * @return
   */
  private float getBtnCX() {
    return btnStartcx + (btnEndcx - btnStartcx) * mSwitchValue;
  }

  private float getBgScale() {
    return (1 - mSwitchValue) * (1 - btnStrokeWidth * 2 / sHeight);
  }


  float touchX, touchY;
  boolean isClickEvent; // 是否是点击事件, 区分拖动还是点击
  float touchSwitchValue;

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        stopAnimation();
        touchX = event.getX();
        touchY = event.getY();
        touchSwitchValue = mSwitchValue;
        isClickEvent = true;
        return true;
      case MotionEvent.ACTION_MOVE:
        float dx = event.getX() - touchX;
        if (Math.abs(dx) > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
          isClickEvent = false; // 非点击, 判定为拖动事件
        }
        float dd = dx / (btnEndcx - btnStartcx);
        if ((touchSwitchValue + dd) >= 1) {
          mSwitchValue = 1;
        } else if ((touchSwitchValue + dd <= 0)) {
          mSwitchValue = 0;
        } else {
          mSwitchValue = touchSwitchValue + dd;
        }
        invalidate();
        break;
      case MotionEvent.ACTION_CANCEL:
      case MotionEvent.ACTION_UP:
        startMyAnimation(isClickEvent);
        break;
    }
    return super.onTouchEvent(event);
  }

  private void stopAnimation() {
    if (anim != null) {
      anim.end();
    }
  }

  private void startMyAnimation(boolean click) {
    float start;
    float end;
    int duration;
    start = mSwitchValue;
    if (!click) { // 非点击，松手判断
      end = mSwitchValue > 0.5 ? 1 : 0;
    } else {
      end = mSwitchValue > 0.5 ? 0 : 1;
    }
    duration = (int) (Math.abs(end - start) * ANIMATION_DURATION);
    anim = ValueAnimator.ofFloat(start, end);
    anim.setDuration(duration);
    anim.addUpdateListener(this);
    anim.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {}

      @Override
      public void onAnimationEnd(Animator animation) {
        anim = null;
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        anim = null;
      }

      @Override
      public void onAnimationRepeat(Animator animation) {

      }
    });
    anim.start();
  }

  @Override
  public void onAnimationUpdate(ValueAnimator animation) {
    setSwitchValue((float) animation.getAnimatedValue());
    invalidate();
  }
}
