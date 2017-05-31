package com.zyx.baby.fragment;

import android.Manifest;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RemoteViews;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zyx.baby.R;
import com.zyx.baby.activity.BoothLinkActivity;
import com.zyx.baby.activity.IndexActivity;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.base.BaseFragment2;
import com.zyx.baby.databinding.FragmentHomeBinding;
import com.zyx.baby.event.BoothEvent;
import com.zyx.baby.service.NotificationService;
import com.zyx.baby.utils.DataCleanManager;
import com.zyx.baby.widget.CustomDialog;
import com.zyx.baby.widget.MyTempView;
import com.zyx.baby.widget.MyTitleBar;
import com.zyx.baby.widget.TempControlView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements View.OnClickListener{


    private static final int CODE_NOTIFICATION = 200; //构建PaddingIntent的请求码,可用于关闭通知

    private Dialog dialog;

    @Override
    public int setContent() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        EventBus.getDefault().register(this);
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void blueEventBus(BoothEvent event) {
        if (TextUtils.equals(event.getTag(),"data")){
            String data1 = (String) event.getObj();
            String[] data = data1.split("#");
            String temp = data[0];
            String humity = data[1];
//            String temp1 = data[0];
//            String humity1 = data[1];
            bindingView.ivTemp.setTemp(15, 40, Integer.valueOf(temp.split("\\.")[0]), "℃");
            bindingView.ivDemp.setTemp(15, 80, Integer.valueOf(humity.split("\\.")[0]), "%");

        }

    }

    protected void initData() {
        bindingView.mtbTitle.setText("可瑞尔");

        bindingView.ivTemp.setTemp(20, 40, 20, "℃");
        bindingView.ivDemp.setTemp(20, 80, 20, "%");

        bindingView.ivTemp.setOnClickListener(this);
        bindingView.linkBlue.setOnClickListener(this);
        bindingView.llCall.setOnClickListener(this);


    }


    private  void linkBlue() {
        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        builder.setMessage("打开蓝牙连接设备");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                DataCleanManager.clearAllCache(getActivity());
                BoothLinkActivity.startActivity(getActivity());
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showWarning();
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();

    }


    private void showWarning() {
        WarningFragment showFragment = WarningFragment.newInstance(getResources().getString(R.string.tips));
        showFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Mdialog);
        showFragment.show(getChildFragmentManager(), "tips");
        showFragment.setCancelable(false);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.link_blue:
                linkBlue();
                break;
            case R.id.ll_call:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED){
                    this.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                }else {
                    call();
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "您拒绝了次权限", Toast.LENGTH_LONG).show();
                }
                    break;
        }
    }

    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
