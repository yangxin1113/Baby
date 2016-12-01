package com.zyx.baby.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.zyx.baby.utils.MyUtils;

import java.nio.Buffer;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.*;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public abstract class BaseFragment extends Fragment implements OnClickListener{

    protected MyUtils utils;
    protected LayoutInflater inflater;
    private int resLayout;
    protected Bundle savedInstanceState;// bundle对象
    private Unbinder unbinder;


    public int getLayoutRes() {
        return resLayout;
    }

    /**
     * 设置fragment关联布局
     *
     * @param resLayout 关联布局id
     */
    public void setLayoutRes(int resLayout) {
        this.resLayout = resLayout;
    }

    protected abstract void init();

    protected abstract void initEvent();

    protected abstract void setInitData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        try {
            this.inflater = inflater;
            View view = inflater.inflate(resLayout, container, false);
            unbinder =  ButterKnife.bind(this, view);
            setInitData();
            initEvent();
            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        utils = MyUtils.getInstance();
        init();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

}
