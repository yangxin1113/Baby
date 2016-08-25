package com.zyx.baby.view.more;

import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;

import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.zyx.baby.R;
import com.zyx.baby.adapter.ViewPagerAdapter;
import com.zyx.baby.base.BaseFragmentActivity;
import com.zyx.baby.utils.LSUtils;


import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class SearchResult extends BaseFragmentActivity implements SearchView.OnQueryTextListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    /*@BindView(R.id.search_view)
    MaterialSearchView searchView;*/
  /*  @BindView(R.id.search_view)
    SearchView searchView;*/
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
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void initEvent() {


        /*searchView.setVoiceSearch(false);
        //searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setEllipsize(true);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
                searchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
                searchView.setVisibility(View.GONE);
            }
        });*/
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DummyFragment(), "内容");
        adapter.addFragment(new DummyFragment(), "人");
        viewPager.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.search_view);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
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
}
