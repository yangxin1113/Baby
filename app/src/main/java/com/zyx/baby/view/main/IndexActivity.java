package com.zyx.baby.view.main;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zyx.baby.adapter.ShowAndHideState;
import com.zyx.baby.base.BaseFragmentActivity;
import com.zyx.baby.R;
import com.zyx.baby.view.home.HomeFragment;
import com.zyx.baby.view.me.MeFragment;
import com.zyx.baby.view.more.BeiWenTab;
import com.zyx.baby.view.more.MoreFragment;
import com.zyx.baby.view.settings.SettingsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


public class IndexActivity extends BaseFragmentActivity implements ShowAndHideState{

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private ArrayList<Fragment> fragments;
    private BadgeItem numberBadgeItem;

    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_index);
    }

    @Override
    protected void setInitData() {

         numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColor(Color.RED)
                .setText("5")
                .setHideOnSelect(false);

        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor(R.color.white) //红色
                .setInActiveColor(R.color.white) //未选中
                .setBarBackgroundColor(R.color.top_bar_color)
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "贝问").setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "宝宝"))
                .addItem(new BottomNavigationItem(R.drawable.ic_link_white_24dp, "设置"))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.show();
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framelayout,new HomeFragment());
        transaction.commit();
    }
    @Override
    protected void initEvent() {
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                if (fragments != null) {
                    if (position < fragments.size()) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment fragment = fragments.get(position);
                        if (fragment.isAdded()) {
                            ft.replace(R.id.framelayout, fragment);
                        } else {
                            ft.add(R.id.framelayout, fragment);
                        }
                        ft.commitAllowingStateLoss();
                    }
                }
            }
            @Override
            public void onTabUnselected(int position) {
                if (fragments != null) {
                    if (position < fragments.size()) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment fragment = fragments.get(position);
                        ft.remove(fragment);
                        ft.commitAllowingStateLoss();
                    }
                }
            }
            @Override
            public void onTabReselected(int position) {
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MoreFragment(this));
        fragments.add(new MeFragment());
        fragments.add(new SettingsFragment(this));
        return fragments;
    }

    @Override
    public void isShow(Context context, boolean b) {
        if(b){
            bottomNavigationBar.show();
        }else {
            bottomNavigationBar.hide();
        }
    }
}
