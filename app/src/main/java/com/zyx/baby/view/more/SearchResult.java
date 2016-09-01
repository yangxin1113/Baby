package com.zyx.baby.view.more;

import android.graphics.Color;
import android.os.Bundle;

import android.support.design.widget.TabLayout;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zyx.baby.R;
import com.zyx.baby.adapter.ViewPagerAdapter;
import com.zyx.baby.base.BaseFragmentActivity;



import com.zyx.baby.utils.LSUtils;


import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.v7.widget.SearchView.*;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class SearchResult extends BaseFragmentActivity implements OnQueryTextListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;


    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_search_result);
    }

    @Override
    protected void setInitData() {
        toolbar.setNavigationIcon(ContextCompat.getDrawable(SearchResult.this, R.drawable.icon_fanhui));
        toolbar.setTitle("搜索");
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void initEvent() {

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ContentFragment(), "内容");
        adapter.addFragment(new PersonFragment(), "人");
        viewPager.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        setSearch(searchView);
        searchView.setOnQueryTextListener(this);
        //searchView.setOnCloseListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onKeyDown(KeyEvent.KEYCODE_BACK, null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

    }


    /**
     * 返回
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        LSUtils.showToast(getApplicationContext(),"zyx"+newText);

        return false;
    }

    public void setSearch(SearchView sv_search) {
        // 设置SearchView默认是否自动缩小为图标
        sv_search.setIconifiedByDefault(true);
        // 显示搜索按钮
        sv_search.setIconifiedByDefault(true);

        SpannableString spanText = new SpannableString("请输入需要查询内容");


        // 设置字体大小
        spanText.setSpan(new AbsoluteSizeSpan(16, true), 0, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        // 设置字体颜色
        spanText.setSpan(new ForegroundColorSpan(Color.WHITE), 0,
                spanText.length(),Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sv_search.setQueryHint(spanText);
        /*int id = sv_search.getContext().getResources()
                .getIdentifier("android:@id/search", null, null);
        TextView textView = (TextView) sv_search.findViewById(id);
        textView.setTextSize(20);// 设置输入字体大小
        textView.setTextColor(Color.GREEN);// 设置输入字的显示
        textView.setHeight(50);// 设置输入框的高度
        textView.setGravity(Gravity.BOTTOM);// 设置输入字的位置*/
    }



}
