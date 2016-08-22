package com.zyx.baby.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zyx.baby.utils.MyUtils;

import butterknife.ButterKnife;

import static android.view.View.*;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public abstract class BaseFragmentActivity extends AppCompatActivity implements OnClickListener {

    protected MyUtils utils;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        init(arg0);
        ButterKnife.bind(this);
        setInitData();
        initEvent();
    }

    /**
     * 初始化布局
     */
    protected abstract void init(Bundle arg0);

    /**
     * 初始化数据
     */
    protected abstract void setInitData();

    /**
     * 初始化控件事件
     */
    protected abstract void initEvent();


    /**
     * 切换到指定的Activity 无传递数据
     *
     * @param cls
     *            指定的Activity
     */
    public void showItemActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        //overridePendingTransition(enterAnim, exitAnim);
        // overridePendingTransition(R.anim.push_out, R.anim.scale_out);
    }

    /**
     * 切换到指定的Activity，有数据传
     *
     * @param bundle
     *            传的数据
     * @param cls
     *            指定的Activity
     */
    public void showItemActivity(Bundle bundle, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
        //overridePendingTransition(enterAnim, exitAnim);
        // overridePendingTransition(R.anim.push_out, R.anim.scale_out);
    }

    /**
     * 切换到指定的Activity，无传数据，但需要返回结
     *
     * @param cls
     *            指定的Activity
     * @param requestCode
     *            返回结果代码
     */
    public void showItemActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivityForResult(intent, requestCode);
        //overridePendingTransition(enterAnim, exitAnim);
        // overridePendingTransition(R.anim.push_out, R.anim.scale_out);
    }

    /**
     * 切换到指定的Activity，传递数据，要返回结果
     *
     * @param bundle
     *            传数据
     * @param cls
     *            指定的Activity
     * @param requestCode
     *            返回结果代码
     */
    public void showItemActivityForResult(Bundle bundle, Class<?> cls,
                                          int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
        //overridePendingTransition(enterAnim, exitAnim);
        // overridePendingTransition(R.anim.push_out, R.anim.scale_out);
    }

}
