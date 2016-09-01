package com.zyx.baby.view.settings;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zyx.baby.R;
import com.zyx.baby.adapter.ShowAndHideState;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.utils.PreferencesUtils;
import com.zyx.baby.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class SettingsFragment extends BaseFragment {

    @BindView(R.id.mtb_title)
    MyTitleBar myTitleBar;
    @BindView(R.id.iv_toggle)
    ImageView iv_toggle;
    @BindView(R.id.re_link)
    RelativeLayout re_link;

    private ShowAndHideState showAndHideState;

    public SettingsFragment(ShowAndHideState showAndHideState){
        this.showAndHideState = showAndHideState;
    }
    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_settings);
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
        //使用说明是否开启
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
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_toggle:
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
                break;

        }

    }
}
