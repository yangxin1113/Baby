package com.zyx.baby.view.me;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragmentActivity;

import com.zyx.baby.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class SettingActivity extends BaseFragmentActivity {

    @BindView(R.id.mtb_title)
    MyTitleBar myTitleBar;
    @BindView(R.id.iv_toggle)
    ImageView iv_toggle;
    @BindView(R.id.re_link)
    RelativeLayout re_link;


    /**
     * 初始化布局
     *
     * @param arg0
     */
    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void initEvent() {
        iv_toggle.setOnClickListener(this);
        iv_toggle.setTag(true);//记录修改状态，默认show
        re_link.setOnClickListener(this);
    }

    @Override
    protected void setInitData() {
        myTitleBar.setText("设置");
        myTitleBar.setLeftImage( R.drawable.icon_back);
        myTitleBar.setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onKeyDown(KeyEvent.KEYCODE_BACK, null);
            }
        });
      /*  //使用说明是否开启
        if(PreferencesUtils.getString(getContext(),"help") == null){
            PreferencesUtils.putString(getContext(),"help","on");
            iv_toggle.setTag(true);

        }else {
            if(PreferencesUtils.getString(getContext(),"help").equals("on")){
                iv_toggle.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.icon_on));
                showAndHideState.isShow(getContext(), true);
            }else{
                iv_toggle.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.icon_off));
                showAndHideState.isShow(getContext(), false);
            }
        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
             /*case R.id.iv_toggle:
               if(iv_toggle.getTag().equals(true)){
                    PreferencesUtils.putString(getContext(),"help","off");
                    iv_toggle.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.icon_off));
                    iv_toggle.setTag(false);//记录修改状态
                    LSUtils.showToast(getContext(),"已关闭");
                    showAndHideState.isShow(getContext(), false);
                }else{
                    PreferencesUtils.putString(getContext(),"help","on");
                    iv_toggle.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.icon_on));
                    iv_toggle.setTag(true);//记录修改状态
                    LSUtils.showToast(getContext(),"已开启");
                    showAndHideState.isShow(getContext(), true);
                }
                break;
            case R.id.re_link:
                Intent i = new Intent(getActivity(),BluetoothActivity.class);
                startActivity(i);
                break;*/

        }

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
}
