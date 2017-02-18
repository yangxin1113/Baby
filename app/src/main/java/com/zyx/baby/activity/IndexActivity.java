package com.zyx.baby.activity;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zyx.baby.adapter.ShowAndHideState;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.R;
import com.zyx.baby.fragment.*;

import java.util.ArrayList;

import butterknife.BindView;



public class IndexActivity extends BaseActivity implements ShowAndHideState{

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private ArrayList<Fragment> fragments;
    private BadgeItem numberBadgeItem;
    private int fragId ;

    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_index);
        fragId = getIntent().getIntExtra("fragId",0);
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
                .addItem(new BottomNavigationItem(R.drawable.icon_baby, "宝贝"))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "贝问").setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_me, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
//        LSUtils.showToast(getApplicationContext(),String.valueOf(fragId));
        setDefaultFragment(fragId);
        bottomNavigationBar.show();
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment(int fragId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch(fragId){
            case 0:
                transaction.replace(R.id.framelayout,new HomeFragment());
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.framelayout,new BabyFragment());
                transaction.commit();
                break;
        }


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

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new BabyFragment());
        fragments.add(new MoreFragment());
        fragments.add(new MeFragment());
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
