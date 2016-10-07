package com.zyx.baby.callback;

import android.view.View;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public interface HomeClickListenner {

    public void showTopic(View view, int position);

    public void showQuestion(View view, int position);

    public void showAnswer(View view, int position);

    public void showTopicImg(View view, int position);

    public void showhead(View view, int position);

    public void showUsername(View view, int position);

    public void showAgreecount(View view, int position);

    public void showTalkcount(View view, int position);
}
