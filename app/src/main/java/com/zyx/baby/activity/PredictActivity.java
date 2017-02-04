package com.zyx.baby.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.fragment.PredictTimeFragment;
import com.zyx.baby.fragment.WarningFragment;
import com.zyx.baby.utils.UserInfoUtils;


/**
 * Created by zyx on 2017/1/28.
 * 尿点预测
 */

public class PredictActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private PredictTimeFragment predictTimeFragment;                     //定义首页predictTimeFragment
    private Fragment isFragment;                         //记录当前正在使用的fragment
    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_chart);
        initFragment(arg0);
    }

    @Override
    protected void setInitData() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
            }
            //toolbar.setNavigationIcon(R.drawable.icon_back);
            toolbar.setTitle("尿点预测");
        }

        if(!UserInfoUtils.getBoolean(getApplicationContext(),"warning2",false)){
            showWarning();
            UserInfoUtils.putBoolean(getApplicationContext(),"warning2",true);
        }
    }


    /**
     * 为页面加载初始状态的fragment
     */
    public void initFragment(Bundle savedInstanceState)
    {
        //判断activity是否重建，如果不是，则不需要重新建立fragment.
        if(savedInstanceState==null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if(predictTimeFragment==null) {
                predictTimeFragment = new PredictTimeFragment();
            }
            isFragment = predictTimeFragment;
            ft.replace(R.id.framelayout, predictTimeFragment).commit();
        }
    }

    @Override
    protected void initEvent() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.predict_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        FragmentManager fm = getSupportFragmentManager();
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                break;
            case R.id.action_warn:
                showWarning();
                break;
            case R.id.action_day:
                if(predictTimeFragment ==null) {
                    predictTimeFragment = new PredictTimeFragment();
                }
                switchContent(isFragment,predictTimeFragment);
                break;
        }
        return true;
    }

    private void showWarning() {
        WarningFragment showFragment = WarningFragment.newInstance(getResources().getString(R.string.warning2));
        showFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Mdialog);
        showFragment.show(getSupportFragmentManager(), "waring2");
        showFragment.setCancelable(false);
    }


    /**
     * 当fragment进行切换时，采用隐藏与显示的方法加载fragment以防止数据的重复加载
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to) {
        if (isFragment != to) {
            isFragment = to;
            FragmentManager fm = getSupportFragmentManager();
            //添加渐隐渐现的动画
            FragmentTransaction ft = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                ft.hide(from).add(R.id.framelayout, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                ft.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }


}
