package com.zyx.baby.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.orhanobut.logger.Logger;
import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.utils.ConfigUtils;
import com.zyx.baby.utils.UserInfoUtils;
import com.zyx.baby.widget.ViewPagerHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class GuideActivity extends BaseActivity {

    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    Snackbar snackbar;

    private List<View> views = null;

    @BindView(R.id.vp_guide)
    public ViewPager mViewPager;
    @BindView(R.id.dots_parent)
    public LinearLayout viewPoints;
    private boolean isFirst = true;


    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_guide);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void setInitData() {

        //首次进入APP显示引导页
        if (ConfigUtils.getBoolean(getApplicationContext(), "isFirst", true)) {
            setViewPager();
            ConfigUtils.putBoolean(getApplicationContext(), "isFirst", false);
        } else {
            //只显示启动页
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                rlMain.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_guide1));
            }else {
                rlMain.setBackgroundDrawable(getResources().getDrawable(R.drawable.img_guide1));
            }
            //未登录用户先登录
//            if(!UserInfoUtils.getString(getApplicationContext(), "phone", "").equals("")){
//                Intent intent = new Intent(GuideActivity.this, IndexActivity.class);
//                startActivity(intent);
//            }else {
//                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }

            Intent intent = new Intent(GuideActivity.this, IndexActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void initEvent() {

    }


    /**
     * 初始化数据
     */
    private void setViewPager() {
        views = new ArrayList<View>();


        View view1 = LayoutInflater.from(this).inflate(R.layout.page_guide_first, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.page_guide_second, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.page_guide_third, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemActivity(LoginActivity.class);
            }
        });

        new ViewPagerHelper(false, mViewPager, views, viewPoints, R.drawable.page_indicator_focused, R.drawable.page_indicator_unfocused);
    }


}
