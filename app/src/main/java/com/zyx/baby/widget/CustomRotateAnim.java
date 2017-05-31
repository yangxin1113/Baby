package com.zyx.baby.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author ZhengYangxin
 * @time 2017/4/15
 * 左右摇摆动画
 */

public class CustomRotateAnim extends Animation {

    private int mWidth;

    private int mHeight;

    private static CustomRotateAnim rotateAnim;

    /**
     * 获取动画实例
     * @return 实例
     */
    public static CustomRotateAnim getCustomRotateAnim() {
        if (null == rotateAnim) {
            rotateAnim = new CustomRotateAnim();
        }
        return rotateAnim;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        this.mWidth = width;
        this.mHeight = height;
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        t.getMatrix().setRotate((float)(Math.sin(interpolatedTime*Math.PI*2)*30), mWidth/2, mHeight/2);
        super.applyTransformation(interpolatedTime, t);
    }
}
