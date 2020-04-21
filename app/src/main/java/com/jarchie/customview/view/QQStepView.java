package com.jarchie.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.jarchie.customview.R;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-21 21:46
 * 邮箱: jarchie520@gmail.com
 * 描述: 仿QQ运动步数效果
 */
public class QQStepView extends View {
    private int mOurterColor = Color.RED;
    private int mInnerColor = Color.BLUE;
    private int mBorderWidth = 20;
    private int mStepTextSize = 16;
    private int mStepTextColor = Color.RED;
    private Paint mOurterPaint, mInnerPaint,mTextPaint;
    private int mStepMax; //总步数
    private int mCurrentStep; //当前步数

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        mOurterColor = array.getColor(R.styleable.QQStepView_outerColor, mOurterColor);
        mInnerColor = array.getColor(R.styleable.QQStepView_innerColor, mInnerColor);
        mBorderWidth = array.getColor(R.styleable.QQStepView_stepBorderWidth, mBorderWidth);
        mStepTextSize = array.getColor(R.styleable.QQStepView_stepTextSize, mStepTextSize);
        mStepTextColor = array.getColor(R.styleable.QQStepView_stepTextColor, mStepTextColor);
        array.recycle();
        //设置外圆画笔
        mOurterPaint = new Paint();
        mOurterPaint.setAntiAlias(true);
        mOurterPaint.setStrokeWidth(mBorderWidth);
        mOurterPaint.setColor(mOurterColor);
        mOurterPaint.setStrokeCap(Paint.Cap.ROUND); //设置下方为圆弧形
        mOurterPaint.setStyle(Paint.Style.STROKE); //画笔空心
        //设置内圆画笔
        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND); //设置下方为圆弧形
        mInnerPaint.setStyle(Paint.Style.STROKE); //画笔空心
        //设置文字画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(mStepTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //调用者在布局文件中可能是wrap_content，可能是宽度高度不一致
        //获取模式 AT_MOST 40dp
        //宽度高度不一致取最小值，确保是个正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画外圆弧
//        int center = getWidth() / 2;
//        int radius = getWidth() / 2 - mBorderWidth / 2;
        @SuppressLint("DrawAllocation")
//        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
                RectF rectF = new RectF(mBorderWidth / 2, mBorderWidth / 2, getWidth() - mBorderWidth / 2, getHeight() - mBorderWidth / 2);
        canvas.drawArc(rectF, 135, 270, false, mOurterPaint);
        //画内圆弧：不能写死，是根据步数计算出来的百分比
        if (mStepMax == 0) return;
        float sweepAngle = (float) mCurrentStep / mStepMax;
        canvas.drawArc(rectF, 135, sweepAngle*270, false, mInnerPaint);
        //画文字
        String stepText = String.valueOf(mCurrentStep);
        @SuppressLint("DrawAllocation")
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(stepText,0,stepText.length(),textBounds);
        int dx = getWidth()/2 - textBounds.width()/2;
        //基线 baseLine
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom-fontMetricsInt.top)/2 - fontMetricsInt.bottom;
        int baseLine = getHeight()/2 + dy;
        canvas.drawText(stepText,dx,baseLine,mTextPaint);
    }

    public synchronized void setStepMax(int stepMax){
        this.mStepMax = stepMax;
    }

    public synchronized void setCurrentStep(int currentStep){
        this.mCurrentStep = currentStep;
        //不断绘制，不断调用onDraw
        invalidate();
    }

}
