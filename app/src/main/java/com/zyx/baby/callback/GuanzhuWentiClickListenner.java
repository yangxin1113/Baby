package com.zyx.baby.callback;

import android.view.View;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public interface GuanzhuWentiClickListenner {

    public void showQuestion(View view, int position);

    public void showAnswer(View view, int position);

    public void showGuanzhu(View view, int position);

}
