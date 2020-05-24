package com.jarchie.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jarchie.customview.R;

/**
 * 作者: 乔布奇
 * 日期: 2020-05-24 15:24
 * 邮箱: jarchie520@gmail.com
 * 描述:
 */
@SuppressLint("AppCompatCustomView")
public class ColorTrackTextView extends TextView {
    //实现一个文字两种颜色，绘制不变色字体的画笔
    private Paint mOriginPaint;
    //实现一个文字两种颜色，绘制变色字体的画笔
    private Paint mChangePaint;
    //实现一个文字两种颜色，当前的进度
    private float mCurrentProgress = 0.0f;

    //实现不同朝向
    private Direction mDirection = Direction.LEFT_TO_RIGHT;

    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }


    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }

    //初始化画笔
    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        int originColor = array.getColor(R.styleable.ColorTrackTextView_originColor, getTextColors().getDefaultColor());
        int changeColor = array.getColor(R.styleable.ColorTrackTextView_changeColor, getTextColors().getDefaultColor());
        mOriginPaint = getPaintByColor(originColor);
        mChangePaint = getPaintByColor(changeColor);
        array.recycle();
    }

    //根据颜色获取画笔
    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        paint.setColor(color); //设置颜色
        paint.setAntiAlias(true); //设置抗锯齿
        paint.setDither(true); //防抖动
        paint.setTextSize(getTextSize());
        return paint;
    }

    //一个文字两种颜色，利用clipRect可以裁剪,左边用一个画笔，右边用另一个画笔
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        //不用系统的，自己来画
        //根据进度把中间值算出来
        int middle = (int) (mCurrentProgress * getWidth());
        if (mDirection == Direction.LEFT_TO_RIGHT) {
            drwaText(canvas, mChangePaint, 0, middle);
            //绘制变色
            drwaText(canvas, mOriginPaint, middle, getWidth());
        } else {
            drwaText(canvas, mChangePaint, getWidth() - middle, getWidth());
            //绘制变色
            drwaText(canvas, mOriginPaint, 0, getWidth() - middle);
        }
    }

    /**
     * 绘制Text
     *
     * @param canvas
     * @param paint
     * @param start
     * @param end
     */
    private void drwaText(Canvas canvas, Paint paint, int start, int end) {
        canvas.save();
        //绘制不变色
        Rect rect = new Rect(start, 0, end, getHeight());
        canvas.clipRect(rect);
        String text = getText().toString();
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        //获取字体的宽度
        int x = getWidth() / 2 - bounds.width() / 2;
        //基线baseLine
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(text, x, baseLine, paint); //这样画其实还是只有一种颜色
        canvas.restore();
    }

    //设置绘制朝向
    public void setDirection(Direction direction) {
        this.mDirection = direction;
    }

    //设置当前进度
    public void setCurrentProgress(float currentProgress) {
        this.mCurrentProgress = currentProgress;
        invalidate();
    }

    //设置更改颜色
    public void setChangeColor(int changeColor) {
        this.mChangePaint.setColor(changeColor);
    }

    //设置未修改颜色
    public void setOriginColor(int originColor) {
        this.mOriginPaint.setColor(originColor);
    }

}
