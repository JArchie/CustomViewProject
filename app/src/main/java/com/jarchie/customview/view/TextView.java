package com.jarchie.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.jarchie.customview.R;
import com.jarchie.customview.utils.TransferUtil;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-19 16:11
 * 邮箱: jarchie520@gmail.com
 * 描述: 自定义TextView
 */
public class TextView extends View {
    private String mText;
    private int mTextSize = 15;
    private int mTextColor = Color.BLACK;
    private Paint mPaint;

    public TextView(Context context) {
        this(context, null);
    }

    public TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextView);
        mText = array.getString(R.styleable.TextView_archieText);
        mTextColor = array.getColor(R.styleable.TextView_archieTextColor, mTextColor);
        mTextSize = array.getDimensionPixelSize(R.styleable.TextView_archieTextSize, TransferUtil.sp2px(mTextSize));
        array.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true); //抗锯齿
        mPaint.setTextSize(mTextSize); //画笔大小
        mPaint.setColor(mTextColor); //画笔颜色
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高的测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //1、确定的值，这种情况不需要计算，给的多少就是多少
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //2、wrap_content，需要计算
        if (widthMode == MeasureSpec.AT_MOST) {
            //计算的宽度与字体的长度、大小有关，用画笔来
            @SuppressLint("DrawAllocation")
            Rect bounds = new Rect();
            //获取文本的rect
            mPaint.getTextBounds(mText, 0, mText.length(), bounds);
            width = bounds.width() + getPaddingLeft() + getPaddingRight();
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            @SuppressLint("DrawAllocation")
            Rect bounds = new Rect();
            mPaint.getTextBounds(mText, 0, mText.length(), bounds);
            height = bounds.height() + getPaddingTop() + getPaddingBottom();
        }
        //设置控件的宽高
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画文字 text   x:开始位置   y：基线    paint
        //dy代表的是高度的一半到baseLine的距离
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        //top是一个负值 bottom是一个正值 top、bottom的值代表的是baseLine到文字顶部和底部的距离
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;
        int x = getPaddingLeft();
        canvas.drawText(mText, x, baseLine, mPaint);
    }

}
