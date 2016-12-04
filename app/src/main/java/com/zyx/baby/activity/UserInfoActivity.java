package com.zyx.baby.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okserver.upload.UploadManager;
import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.utils.DataCleanManager;
import com.zyx.baby.widget.CustomDialog;

import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/4 0004.
 */

public class UserInfoActivity extends BaseActivity{

    private Dialog dialog;
    private ImagePicker imagePicker;
    private ArrayList<ImageItem> images;
    private UploadManager uploadManager;

    private String mineData,sexone,mynick;

    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_userinfo);
    }

    @Override
    protected void setInitData() {

    }

    @Override
    protected void initEvent() {

    }

    //退出登录
    @OnClick(R.id.rl_logout)
    void logOut() {
        CustomDialog.Builder builder = new CustomDialog.Builder(UserInfoActivity.this);
//        builder.setTitle("提醒");
        builder.setMessage("您确定要退出登录吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                DataCleanManager.clearAllCache(getApplicationContext());

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();

    }

    //返回
    @OnClick(R.id.iv_left)
    void back() {
        onKeyDown(KeyEvent.KEYCODE_BACK, null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**修改性别*/
    @OnClick(R.id.rl_head)
    public void setHead(){
        imagePicker = ImagePicker.getInstance();
        imagePicker.setShowCamera(true);
        imagePicker.setMultiMode(false);
        imagePicker.setCrop(false);
        Intent intent = new Intent(getApplicationContext(), ImageGridActivity.class);
        startActivityForResult(intent, 101);

    }

    /**修改性别*/
    @OnClick(R.id.rl_sex)
    public void setSex(){
        Intent intent=new Intent(this,ReSetSexActivity.class);
        intent.putExtra("sex",sexone);
        startActivityForResult(intent,1);

    }
    /**修改昵称*/
    @OnClick(R.id.rl_nick)
    public void setNick(){
        Intent intent=new Intent(this,ReSetNickActivity.class);
        startActivityForResult(intent,1);
    }
    /**修改电话*/
    @OnClick(R.id.rl_phone)
    public void setPhone(){
        //如果电话号码为空
        if(TextUtils.isEmpty(mineData)){
            Intent intent=new Intent(this,ReSetPhoneActivity.class);
            startActivityForResult(intent,1);
        }else {
            Intent intent=new Intent(this,ModifyPhoneNumber.class);
            intent.putExtra("phone",mineData);
            startActivityForResult(intent,1);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 101) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
               /* MyAdapter adapter = new MyAdapter(images);
                gridView.setAdapter(adapter);*/
            } else {
                showToast("没有数据");
            }
        }
    }
}
