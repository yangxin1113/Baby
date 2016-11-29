package com.zyx.baby.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.zyx.baby.base.BaseFragmentActivity;
import com.zyx.baby.R;
import com.zyx.baby.utils.PreferencesUtils;

import com.zyx.baby.widget.ViewPagerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class GuideActivity extends BaseFragmentActivity {

    private List<View> views = null;

    @BindView(R.id.vp_guide)
    public ViewPager mViewPager;
    @BindView(R.id.dots_parent)
    public LinearLayout viewPoints;

    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_guide);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void setInitData() {
        PreferencesUtils.putString(GuideActivity.this, "help", "on");
        setViewPager();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View v) {

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
