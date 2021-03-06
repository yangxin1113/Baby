package com.zyx.baby.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.zyx.baby.R;
import com.zyx.baby.databinding.ActivityTransitionBinding;
import com.zyx.baby.utils.CommonUtils;
import com.zyx.baby.utils.ConstantsImageUrl;
import com.zyx.baby.utils.PerfectClickListener;

import java.util.Random;

/**
 * @author ZhengYangxin
 * @time 2017/3/15
 */

public class TransitionActivity extends AppCompatActivity{

    private static final String TAG = "TransitionActivity";
    private ActivityTransitionBinding mBinding;
    private boolean isIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0){
            finish();
            return;
        }
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_transition);
        int i = new Random().nextInt(ConstantsImageUrl.TRANSITION_URLS.length);
        //先显示启动页
        mBinding.ivDefultPic.setImageDrawable(CommonUtils.getDrawable(R.mipmap.img_splash1));
                Glide.with(this)
                .load(R.mipmap.img_splash1)
                .placeholder(R.mipmap.img_splash1)
                .error(R.mipmap.img_splash1)
                .into(mBinding.ivPic);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.ivDefultPic.setVisibility(View.GONE);
            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        }, 2000);

        mBinding.tvJump.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                toMainActivity();
//                animationEnd();
            }
        });
    }

    private void toMainActivity() {
        if (isIn) {
            return;
        }
        Intent intent = new Intent(this, IndexActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
        isIn = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
