package com.zyx.baby.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.zyx.baby.R;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class CycleView extends View {

    private Paint mPaint;
    private int progress = 0;
    private int start_degree = 130;
    private int background_int;
    private int progress_int;
    private float layout_width_float;
    private int textColor_int;
    private float textSize_float;
    private int max_int;
    private Drawable thumb_double;
    private int thumbSize_int;
    private String text; //温度或湿度
    private RectF oval;


    public CycleView(Context context) {
        super(context);
    }

    public CycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitResources(context, attrs);
    }

    public CycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void InitResources(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.windows,0,0);

        background_int = typedArray.getColor(R.styleable.windows_background1, 0xFF87cfe8);
        progress_int = typedArray.getColor(R.styleable.windows_progressDrawable, 0xFFabd800);
        layout_width_float = typedArray.getDimension(R.styleable.windows_layout_width, 24);
        textColor_int = typedArray.getColor(R.styleable.windows_textColor, 0xFFada1de);
        textSize_float = typedArray.getDimension(R.styleable.windows_textSize, 50);
        max_int = typedArray.getInt(R.styleable.windows_max, 100);
        progress = typedArray.getInt(R.styleable.windows_progress, 20);
        thumb_double = typedArray.getDrawable(R.styleable.windows_thumb);
        thumbSize_int = typedArray.getInt(R.styleable.windows_thumbSize, 30);
        text = typedArray.getString(R.styleable.windows_texttext);
        typedArray.recycle();

        if(thumb_double == null){
            Bitmap bitmap = Bitmap.createBitmap(thumbSize_int,thumbSize_int, Bitmap.Config.ARGB_8888);

            //图片动画
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(0xFF68ba32);
            int center = thumbSize_int / 2;
            int radius = center - 4;
            canvas.drawCircle(center, center, radius, paint);
            thumb_double = new BitmapDrawable(getResources(), bitmap);
        }
        mPaint= new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawProgressView(canvas);
    }

    private void drawProgressView(Canvas canvas) {
        InitOval(canvas);
        drawBackground(canvas);
        drawProgress(canvas);
        drawProgressText(canvas);
    }


    private void InitOval(Canvas canvas) {
        int center = getWidth() / 2;
        int radius = (int)(center - thumbSize_int / 2);
        int left_top = center - radius;
        int right_bottom = center + radius;
        if(oval == null){
            oval = new RectF(left_top, left_top, right_bottom, right_bottom);
        }
    }

    private void drawBackground(Canvas canvas) {

        int center = getWidth() / 2;
        int radius = (int)(center - thumbSize_int / 2);


        mPaint.setStrokeWidth(layout_width_float);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        //设置背景
        mPaint.setColor(background_int);
        canvas.drawArc(oval, start_degree, 280, false, mPaint);
    }

    private void drawProgress(Canvas canvas){
        mPaint.setColor(progress_int);
        mPaint.setStrokeWidth(layout_width_float);
        float seek = 0;
        if(max_int > 0){
            seek = (float) progress / max_int * 360;
        }
        canvas.drawArc(oval, start_degree, seek, false, mPaint);

        canvas.save();
        int center = getWidth() / 2;
        int radius = (int)(center - thumbSize_int / 2);

        double cycle_round = (seek + start_degree) * Math.PI / 180;
        float x = (float) (Math.cos(cycle_round) * (radius) + center - thumbSize_int / 2);
        float y = (float) (Math.sin(cycle_round) * (radius) +center - thumbSize_int / 2);
        thumb_double.setBounds(0, 0, thumbSize_int, thumbSize_int);
        canvas.translate(x, y);
        thumb_double.draw(canvas);
        canvas.restore();
    }

    private void drawProgressText(Canvas canvas) {
        mPaint.setTextSize(textSize_float);
        mPaint.setColor(textColor_int);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        String progresstext = progress + text;
        float text_left = (getWidth() - mPaint.measureText(progresstext)) / 2;
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        // 绘制文字是底部对齐
        float text_top = (float) ((getHeight() / 2 + Math.ceil(fontMetrics.descent - fontMetrics.ascent) / 2));
        canvas.drawText(progresstext, text_left, text_top, mPaint);
    }

    /**
     * 查看Seekbar源码
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress > max_int) {
            progress = max_int;
        }
        this.progress = progress;
        postInvalidate();
    }

    public int getProgress() {
        return this.progress;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (thumb_double != null) {
            thumb_double.setCallback(null);
            thumb_double = null;
        }
    }
}
