package com.zyx.baby.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragmentActivity;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.widget.CircleImageView;
import com.zyx.baby.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by zyx on 2016/10/3.
 */

public class BabyInfoActivity extends BaseFragmentActivity {

    @BindView(R.id.mtb_title)
    MyTitleBar mtbTitle;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.ed_nick)
    EditText edNick;
    @BindView(R.id.ed_birthday)
    EditText edBirthday;
    @BindView(R.id.ed_hight)
    EditText edHight;
    @BindView(R.id.ed_weight)
    EditText edWeight;
    @BindView(R.id.iv_b)
    ImageView ivB;
    @BindView(R.id.ll_b)
    LinearLayout llB;
    @BindView(R.id.iv_g)
    ImageView ivG;
    @BindView(R.id.ll_g)
    LinearLayout llG;
    @BindView(R.id.bt_save)
    Button btSave;

    /**
     * 初始化布局
     *
     * @param arg0
     */
    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_babyinfo);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void setInitData() {
        mtbTitle.setText("宝贝信息");
        mtbTitle.setLeftImage(R.drawable.icon_back);
        mtbTitle.setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onKeyDown(KeyEvent.KEYCODE_BACK, null);
            }
        });
    }

    /**
     * 初始化控件事件
     */
    @Override
    protected void initEvent() {
        btSave.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_save:
                LSUtils.showToast(getApplicationContext(),"保存成功");
                Intent i = new Intent(BabyInfoActivity.this, IndexActivity.class);
                i.putExtra("fragId",1);
                startActivity(i);
                //onKeyDown(KeyEvent.KEYCODE_BACK, null);
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Bundle bundle = new Bundle();
            //xbundle.putString("datefrom", "hello");
            setResult(RESULT_CANCELED, this.getIntent().putExtras(bundle));
            finish();
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
