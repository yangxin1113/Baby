package com.zyx.baby.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zyx.baby.R;
import com.zyx.baby.activity.IndexActivity;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.widget.MyTempView;
import com.zyx.baby.widget.MyTitleBar;
import com.zyx.baby.widget.TempControlView;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.mtb_title)
    MyTitleBar myTitleBar;
    @BindView(R.id.iv_temp)
    TempControlView tempControl;
    @BindView(R.id.iv_demp)
    TempControlView dempControl;

    private static final int CODE_NOTIFICATION = 200; //构建PaddingIntent的请求码,可用于关闭通知

    private NotificationManager mNotificationManager; //通知管理器
    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_home);
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void setInitData() {
        myTitleBar.setText("宝贝尿了");

        tempControl.setTemp(15, 30, 15);
       // mTempView.setTemp("37.5", 39.5f, 35.4f, getActivity(), 380);
        tempControl.setOnTempChangeListener(new TempControlView.OnTempChangeListener() {
            @Override
            public void change(int temp) {
                Toast.makeText(getActivity(), temp + "℃", Toast.LENGTH_SHORT).show();
            }
        });


        dempControl.setTemp(15, 30, 15);
        // mTempView.setTemp("37.5", 39.5f, 35.4f, getActivity(), 380);
        dempControl.setOnTempChangeListener(new TempControlView.OnTempChangeListener() {
            @Override
            public void change(int temp) {
                Toast.makeText(getActivity(), temp + "℃", Toast.LENGTH_SHORT).show();
            }
        });

        mNotificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public void onClick(View v) {

    }


    @OnClick({R.id.link_blue})
    public void homeClick(View view){
        switch (view.getId()){
            case R.id.link_blue:
                createCustomNotification();
                break;
            default:
                break;
        }
    }

    private void createCustomNotification() {
        Intent intent = new Intent(getActivity(), IndexActivity.class);
        //如果第二次获取并且请求码相同,如果原来已解决创建了这个PendingIntent,则复用这个类,并更新intent
        int flag = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 3, intent, flag);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.drawable.logo)
                .setTicker("当前正在播放..")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("歌曲名")
                .setContentText("歌手") //以上的设置是在为了兼容3.0之前的版本
                .setContentIntent(contentIntent)
                .setContent(getRemoteView()); //自定义通知栏view的api是在3.0之后生效
        Notification notification = builder.build();
        //打开通知
        mNotificationManager.notify(CODE_NOTIFICATION, notification);
    }

    /**
     * 创建RemoteViews,3.0之后版本使用
     *
     * @return
     */
    public RemoteViews getRemoteView() {
        RemoteViews remoteViews = new RemoteViews(getActivity().getPackageName(), R.layout.noticefication);
        remoteViews.setTextViewText(R.id.title, "歌曲名");
        remoteViews.setTextViewText(R.id.text, "歌手");
//        //打开上一首
//        remoteViews.setOnClickPendingIntent(R.id.btn_pre, getClickPendingIntent(NOTIFICATION_PRE));
//        //打开下一首
//        remoteViews.setOnClickPendingIntent(R.id.btn_next, getClickPendingIntent(NOTIFICATION_NEXT));
//        //点击整体布局时,打开播放器
//        remoteViews.setOnClickPendingIntent(R.id.ll_root, getClickPendingIntent(NOTIFICATION_OPEN));
        return remoteViews;
    }
}
