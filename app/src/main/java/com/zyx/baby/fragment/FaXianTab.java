package com.zyx.baby.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.*;

import android.widget.*;
import com.zyx.baby.R;
import com.zyx.baby.adapter.CaogaoAdapter;
import com.zyx.baby.adapter.HomeAdapter1;
import com.zyx.baby.adapter.ViewPagerAdapter;
import com.zyx.baby.adapter.YonghuAdapter;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.base.BaseFragmentActivity;
import com.zyx.baby.bean.CaogaoData;
import com.zyx.baby.bean.HomeData;
import com.zyx.baby.bean.YonghuData;
import com.zyx.baby.callback.CaogaoClickListenner;
import com.zyx.baby.callback.GuanzhuYonghuClickListenner;
import com.zyx.baby.callback.HomeClickListenner;
import com.zyx.baby.utils.BluetoothUtils;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.activity.BluetoothChat;
import com.zyx.baby.widget.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;


import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class FaXianTab extends BaseFragment {


    @BindView(R.id.rv_ques)
    RecyclerView rvQues;
    @BindView(R.id.scroll_home)
    HomeScrollView scrollHome;
    @BindView(R.id.swipelayout)
    SwipeRefreshLayout swipelayout;

    private List<HomeData> homeDatas = new ArrayList<HomeData>();
    private HomeAdapter1 homeAdapter;
    private FullyLinearLayoutManager mLayoutManager;


    @Override
    protected void init() {
        setLayoutRes(R.layout.tab_faxian);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void setInitData() {

        scrollHome.setSwipeRefreshLayout(swipelayout);
        swipelayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        getData();

        mLayoutManager = new FullyLinearLayoutManager(getActivity(), VERTICAL, false);
        rvQues.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onClick(View v) {

    }


    private void getData() {


        for(int i = 1; i<=10; i++){
            HomeData homeData = new HomeData();
            homeData.set_id(i+"");
            homeData.setTitle("宝宝为何喜欢咬书和撕书？");
            homeData.setAvator("http://tp2.sinaimg.cn/1619687665/50/5642384100/1");
            homeData.setName("倪妈妈");
            homeData.setPraise_up_count(2+10*2);
            homeData.setComment(22+10*3);
            homeDatas.add(homeData);
        }
        homeAdapter = new HomeAdapter1(getContext(), homeDatas);
        rvQues.setAdapter(homeAdapter);
        rvQues.addItemDecoration(new ListItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvQues.setFocusable(false);
        scrollHome.smoothScrollTo(0, 0);
        itemOnClickListenner(); //item监听事件

    }

    /**
     * 接口回调实现RecyclerView的item布局中每个控件的点击事件
     */
    private void itemOnClickListenner() {
        homeAdapter.setHomeClickListenner(new HomeClickListenner() {
            @Override
            public void showTopic(View view, int position) {

            }

            @Override
            public void showQuestion(View view, int position) {
                //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getQuestion());

            }

            @Override
            public void showAnswer(View view, int position) {
                //LSUtils.showToast(getContext(), "点击了我" + homeDatas.getData().get(position).getContent());

            }

            @Override
            public void showTopicImg(View view, int position) {
                LSUtils.showToast(getContext(), "点击了我图片");
            }

            @Override
            public void showhead(View view, int position) {
                LSUtils.showToast(getContext(), "点击了我头像");

            }

            @Override
            public void showUsername(View view, int position) {
                //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getUsername());
            }

            @Override
            public void showAgreecount(View view, int position) {
                //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getAgreecount());
            }

            @Override
            public void showTalkcount(View view, int position) {
                //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getTalkcount());
            }
        });
    }

    /**
     * 我的成就
     * Created by Administrator on 2016/7/28 0028.
     */
    public static class AchievedActivity extends BaseFragmentActivity {

        @BindView(R.id.iv_left)
        ImageView iv_left;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_top)
        RelativeLayout ivTop;
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_nick)
        TextView tvNick;
        @BindView(R.id.tv_friends)
        TextView tvFriends;
        @BindView(R.id.tv_fans)
        TextView tvFans;
        @BindView(R.id.ll_account)
        LinearLayout llAccount;
        @BindView(R.id.v_line)
        View vLine;
        @BindView(R.id.tv_zantong)
        TextView tvZantong;
        @BindView(R.id.ll_zantong)
        LinearLayout llZantong;
        @BindView(R.id.tv_shoucang)
        TextView tvShoucang;
        @BindView(R.id.ll_shoucang)
        LinearLayout llShoucang;
        @BindView(R.id.tv_fenxaing)
        TextView tvFenxaing;
        @BindView(R.id.ll_fenxiang)
        LinearLayout llFenxiang;
        @BindView(R.id.ll_lan)
        LinearLayout llLan;
        @BindView(R.id.tv_dongtai)
        TextView tvDongtai;
        @BindView(R.id.tv_dongtaicount)
        TextView tvDongtaicount;
        @BindView(R.id.iv_dongtai_jiantou)
        ImageView ivDongtaiJiantou;
        @BindView(R.id.ll_dongtai)
        RelativeLayout llDongtai;
        @BindView(R.id.tv_huida)
        TextView tvHuida;
        @BindView(R.id.tv_huidacount)
        TextView tvHuidacount;
        @BindView(R.id.iv_huida_jiantou)
        ImageView ivHuidaJiantou;
        @BindView(R.id.ll_huida)
        RelativeLayout llHuida;
        @BindView(R.id.tv_tiwen)
        TextView tvTiwen;
        @BindView(R.id.tv_tiwencount)
        TextView tvTiwencount;
        @BindView(R.id.iv_tiwen_jiantou)
        ImageView ivTiwenJiantou;
        @BindView(R.id.ll_tiwen)
        RelativeLayout llTiwen;
        @BindView(R.id.main)
        LinearLayout main;
        @BindView(R.id.mainLayout)
        LinearLayout mainLayout;


        @Override
        protected void init(Bundle arg0) {
            setContentView(R.layout.activity_achieved);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //getData();
        }

        @Override
        protected void setInitData() {

        }

        @Override
        protected void initEvent() {
            iv_left.setOnClickListener(this);
            ivHead.setOnClickListener(this);
            llAccount.setOnClickListener(this);
            llZantong.setOnClickListener(this);
            llShoucang.setOnClickListener(this);
            llFenxiang.setOnClickListener(this);
            llAccount.setOnClickListener(this);
            llDongtai.setOnClickListener(this);
            llHuida.setOnClickListener(this);
            llTiwen.setOnClickListener(this);
            llAccount.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_left:
                    onKeyDown(KeyEvent.KEYCODE_BACK, null);
                    break;
                case R.id.iv_head:
                    showItemActivity(FriendsActivity.class);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case R.id.ll_account:
                    showItemActivity(FriendsActivity.class);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                    break;
                case R.id.ll_zantong:
                    showItemActivity(FriendsActivity.class);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case R.id.ll_shoucang:
                    showItemActivity(FriendsActivity.class);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case R.id.ll_fenxiang:
                    showItemActivity(FriendsActivity.class);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case R.id.ll_dongtai:
                    showItemActivity(FriendsActivity.class);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case R.id.ll_huida:
                    //showItemActivity(MyAnswersActivity.class);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case R.id.ll_tiwen:
                    //showItemActivity(MyTiwenActivity.class);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;

            }
        }


        /*//用户信息
        private void getData() {
            final String USERINFO_URL = ((MyApplication) getApplication()).getApis().get("Host").toString() +
                    ((MyApplication) getApplication()).getApis().get("UserInfo").toString();

            OkHttpUtils.post(USERINFO_URL)
                    //.headers("cookies", PreferencesUtils.getString(getApplicationContext(), "Cookies"))
                    .params("uid","me")
                    .execute(new StringDialogCallback(AchievedActivity.this) {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            try {
                                JSONObject json = new JSONObject(s);
                                setUserInfo(json.getJSONObject("data"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                        }
                    });

        }

        private void setUserInfo(JSONObject data) {
            try {
                //Picasso.with(getContext()).load(parse.isNull(userInfo.get("avator").toString().replace("http://flycat.oss-cn-hangzhou.aliyuncs.com/",""))).into(iv_head);
                Picasso.with(getApplicationContext()).load(parse.isNull(data.getString("avator")).toString().replace("http://flycat.oss-cn-hangzhou.aliyuncs.com/","")).into(ivHead);
                tvNick.setText(data.getString("name"));
                tvZantong.setText(data.getString("praise_count"));
                tvShoucang.setText(data.getString("keep_count"));
                tvFenxaing.setText(data.getString("share_count"));
                tvDongtaicount.setText(data.getString("dyncount"));
                tvHuidacount.setText(data.getString("answer_count"));
                tvTiwencount.setText(data.getString("ask_count"));
                tvFriends.setText(data.getString("concern_count"));
                tvFans.setText(data.getString("be_concern_count"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * Created by Administrator on 2016/8/15 0015.
     */
    public static class BluetoothActivity extends BaseFragmentActivity {

        private Context mContext;
        private final int REQUEST_ENALBLE = 1;
        private final String BLUETOOTH_UUID = "00001101-0000-1000-8000-00805F9B34FB";

        private ListView mBluetoothListView;
        private FrameLayout mLoadingFramelayout;
        private ArrayAdapter mArrayAdapter;

        private Button  mFindDeviceButton;
        private Button mConnectButton;
        private Button  mDisconnectButton;
        private Button  mCloseButton;
        private Button  mOpenButton;

        private BluetoothUtils mBluetoothUtils;
        private BluetoothAdapter mBluetoothAdapter;
        private BluetoothDevice mDevice;
        private BluetoothSocket mBluetoothSocket;

        @Override
        protected void init(Bundle arg0) {
            setContentView(R.layout.activity_bluetooth);
            mContext = this;
            mBluetoothListView = (ListView)findViewById(R.id.bluetooth_listview);
            mFindDeviceButton = (Button)findViewById(R.id.find_device_btn);
            mConnectButton = (Button)findViewById(R.id.connect_btn);
            mDisconnectButton = (Button)findViewById(R.id.disconnect_btn);
            mOpenButton = (Button)findViewById(R.id.open_btn);
            mCloseButton = (Button)findViewById(R.id.close_btn);
            mLoadingFramelayout = (FrameLayout)findViewById(R.id.loading_framelayout);
        }

        @Override
        protected void setInitData() {
            mBluetoothUtils = new BluetoothUtils(mContext);
            mBluetoothAdapter = mBluetoothUtils.mBluetoothAdapter;
            mArrayAdapter = new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1);
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mReceiver, filter);
        }

        @Override
        protected void initEvent() {
            mConnectButton.setOnClickListener(this);
            mFindDeviceButton.setOnClickListener(this);
            mDisconnectButton.setOnClickListener(this);
            mCloseButton.setOnClickListener(this);
            mOpenButton.setOnClickListener(this);

            mBluetoothListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mBluetoothUtils.endDiscoverBluetooth();

                    mArrayAdapter.getItem(position);
                    Toast.makeText(mContext, "   position: " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        /**
         * 开启蓝牙功能
         */
        private void openBluetoothDevice(){
            if(!mBluetoothAdapter.isEnabled()){
                Intent enalbeBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                // 设置蓝牙可见性，最多300秒
                enalbeBluetoothIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                startActivityForResult(enalbeBluetoothIntent, REQUEST_ENALBLE);
            } else {
                mBluetoothUtils.startDiscoverBluetooth();
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == REQUEST_ENALBLE && resultCode == RESULT_OK){
                mBluetoothUtils.startDiscoverBluetooth();
                Toast.makeText(mContext,"蓝牙查找" + mBluetoothUtils.startDiscoverBluetooth(),Toast.LENGTH_SHORT).show();
            } else if (requestCode == REQUEST_ENALBLE && resultCode == RESULT_CANCELED){
                Toast.makeText(mContext,"蓝牙被关闭",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onClick(View v) {
            CommunicateThread communicateThread = null;
            switch (v.getId()){
                case R.id.find_device_btn:
                    if(mBluetoothUtils.mBluetoothAdapter != null){
                        openBluetoothDevice();
                    } else {
                        Toast.makeText(mContext,"设备没有蓝牙功能",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.connect_btn:
    //                mConnectThread = new ConnectThread(mDevice);
    //                mConnectThread.start();
                    ConnectAsynctask connectAsyctask = new ConnectAsynctask();
                    connectAsyctask.execute(mDevice);
                    break;
                case R.id.disconnect_btn:
    //                if(mConnectThread != null){
    //                    mConnectThread.cancle();
    //                }

                    break;
                case R.id.open_btn:
                    communicateThread = new CommunicateThread(mBluetoothSocket);
                    byte[] openBytes = new byte[5];
                    openBytes[0] = (byte)0xA1;
                    openBytes[1] = (byte)0xFD;
                    openBytes[2] = (byte)0x0B;
                    openBytes[3] = (byte)0x02;
                    openBytes[4] = (byte)0xDF;
                    communicateThread.write(openBytes);
                    break;
                case R.id.close_btn:
                    communicateThread = new CommunicateThread(mBluetoothSocket);
                    byte[] closeBytes = new byte[5];
                    closeBytes[0] = (byte)0xA1;
                    closeBytes[1] = (byte)0xFD;
                    closeBytes[2] = (byte)0x0B;
                    closeBytes[3] = (byte)0x01;
                    closeBytes[4] = (byte)0xDF;
                    communicateThread.write(closeBytes);
                    break;
                default:
                    break;

            }
        }

        private BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    Toast.makeText(mContext,"蓝牙被发现",Toast.LENGTH_SHORT).show();
                    mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    mArrayAdapter.add("蓝牙名：" + mDevice.getName() + "\n" + mDevice.getAddress());
                    mBluetoothListView.setAdapter(mArrayAdapter);
                }
            }
        };


        public final class ConnectAsynctask extends AsyncTask<BluetoothDevice, Integer, BluetoothSocket> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mLoadingFramelayout.setVisibility(View.VISIBLE);
            }

            @Override
            protected BluetoothSocket doInBackground(BluetoothDevice... params) {
                BluetoothSocket bluetoothSocket = null;
                BluetoothDevice bluetoothDevice = params[0];
                try {
                    bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(BLUETOOTH_UUID));
                } catch (IOException e) {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return null;
                }
                try {
                    bluetoothSocket.connect();
                } catch (IOException e) {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return null;
                }
                return bluetoothSocket;
            }

            @Override
            protected void onPostExecute(BluetoothSocket bluetoothSocket) {
                super.onPostExecute(bluetoothSocket);
                mLoadingFramelayout.setVisibility(View.GONE);
                if (bluetoothSocket == null) {
                    Toast.makeText(mContext, "连接失败", Toast.LENGTH_SHORT).show();
                } else {
                    mBluetoothSocket = bluetoothSocket;
                }
            }
        }

            /**
             * 与蓝牙设备进行交流
             */
            private final class CommunicateThread extends Thread{

                BluetoothSocket  bluetoothSocket = null;

                InputStream inputStream = null;

                OutputStream outputStream = null;


                public CommunicateThread(BluetoothSocket bluetoothSocket){
                    try{
                        bluetoothSocket = bluetoothSocket;

                        inputStream = bluetoothSocket.getInputStream();

                        outputStream = bluetoothSocket.getOutputStream();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void run() {
                    byte[] buffer = new byte[1024];
                    int bytes;
                    while (true){
                        try {
                            bytes = inputStream.read(buffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void write(byte[] bytes){
                    try {
                        outputStream.write(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                public void cancel(){
                    try {
                        bluetoothSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void onDestroy() {
                super.onDestroy();
                unregisterReceiver(mReceiver);
            }

            private byte[] getHexBytes(String message) {
                int len = message.length() / 2;
                char[] chars = message.toCharArray();
                String[] hexStr = new String[len];
                byte[] bytes = new byte[len];
                for (int i = 0, j = 0; j < len; i += 2, j++) {
                    hexStr[j] = "" + chars[i] + chars[i + 1];
                    bytes[j] = (byte) Integer.parseInt(hexStr[j], 16);
                }
                return bytes;
            }
    }

    /**
     * 我的成就
     * Created by Administrator on 2016/7/28 0028.
     */
    public static class CaoGaoActivity extends BaseFragmentActivity {

        @BindView(R.id.iv_left)
        ImageView ivleft;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_top)
        RelativeLayout ivTop;
        @BindView(R.id.rv_caogao)
        RecyclerView rvCaogao;

        private CaogaoAdapter caogaoAdapter;
        private List<CaogaoData> caogaoDatas;


        @Override
        protected void init(Bundle arg0) {
            setContentView(R.layout.activity_caogao);

        }

        @Override
        protected void setInitData() {

            //设置LinearLayoutManager布局管理器，实现ListView效果
            rvCaogao.setLayoutManager(new LinearLayoutManager(CaoGaoActivity.this));
            rvCaogao.addItemDecoration(new ListItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
            getData();
            caogaoAdapter = new CaogaoAdapter(CaoGaoActivity.this, caogaoDatas);
            rvCaogao.setAdapter(caogaoAdapter);

        }

        @Override
        protected void initEvent() {
            ivleft.setOnClickListener(this);
            itemOnClickListenner(); //item监听事件
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_left:
                    onKeyDown(KeyEvent.KEYCODE_BACK, null);
                    break;
            }
        }

        private void itemOnClickListenner() {
            caogaoAdapter.setCaogaoClickListenner(new CaogaoClickListenner() {
                @Override
                public void showQuestion(View view, int position) {
                    LSUtils.showToast(getApplicationContext(), "问题");
                }

                @Override
                public void showContent(View view, int position) {
                    LSUtils.showToast(getApplicationContext(), "内容");
                }

                @Override
                public void showDelete(View view, int position) {
                    LSUtils.showToast(getApplicationContext(), "删除");
                }
            });
        }


        public void getData() {
            caogaoDatas = new ArrayList<CaogaoData>();
            for(int i=0; i<10; i++){
                CaogaoData caogao = new CaogaoData();
                caogao.setId(i);
                caogao.setQuestion("高招：宝宝不喝奶粉怎么办"+i);
                caogao.setContent("可以去医院问下医生，看看是不是那里不正常，不喝奶粉尽量母乳啊！母乳喂养经济健康小孩长得快，实在不行吸奶器储存也比喝奶粉强啊");
                caogaoDatas.add(caogao);
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

    /**
     * 粉丝列表
     * Created by Administrator on 2016/8/29 0029.
     */
    public static class FansListFragment extends BaseFragment {


        @BindView(R.id.yonghu)
        RecyclerView rvYonghu;
        private List<YonghuData> yonghuDatas = new ArrayList<YonghuData>();
        private YonghuAdapter yonghuAdapter;


        @Override
        protected void init() {
            setLayoutRes(R.layout.fragment_person);

        }

        @Override
        protected void initEvent() {
            itemOnClickListenner();
        }

        @Override
        protected void setInitData() {

            //设置LinearLayoutManager布局管理器，实现ListView效果
            rvYonghu.setLayoutManager(new LinearLayoutManager(getActivity()));

        }




        @Override
        public void onClick(View v) {
            switch (v.getId()) {

            }

        }


        /**
         * 接口回调实现RecyclerView的item布局中每个控件的点击事件
         */
        private void itemOnClickListenner() {
            yonghuAdapter.setGuanzhuYonghuClickListenner(new GuanzhuYonghuClickListenner() {


                @Override
                public void showHead(View view, int position) {
                    LSUtils.showToast(getContext(),yonghuDatas.get(position).getAvator());
                }

                @Override
                public void showNick(View view, int position) {
                    LSUtils.showToast(getContext(),yonghuDatas.get(position).getName());
                }

                @Override
                public void showQianming(View view, int position) {
                    LSUtils.showToast(getContext(),yonghuDatas.get(position).getSignature());
                }

                @Override
                public void showIsGuanzhu(View view, int position) {


                }
            });
        }



    }

    /**
     * 好友列表
     * Created by Administrator on 2016/7/28 0028.
     */
    public static class FriendsActivity extends BaseFragmentActivity {

        @BindView(R.id.iv_left)
        ImageView ivleft;
        @BindView(R.id.tabLayout)
        TabLayout tabLayout;
        @BindView(R.id.viewPager)
        ViewPager viewPager;

        @Override
        protected void init(Bundle arg0) {
            setContentView(R.layout.activity_friends);

        }

        @Override
        protected void setInitData() {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }

        private void setupViewPager(ViewPager viewPager) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new FriendsListFragment(), "我的关注");
            adapter.addFragment(new FansListFragment(), "我的粉丝");
            viewPager.setAdapter(adapter);
        }

        @Override
        protected void initEvent() {
            ivleft.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_left:
                    onKeyDown(KeyEvent.KEYCODE_BACK, null);
                    break;

            }
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
    }

    /**
     * 粉丝列表
     * Created by Administrator on 2016/8/29 0029.
     */
    public static class FriendsListFragment extends BaseFragment {


        @BindView(R.id.yonghu)
        RecyclerView rvYonghu;
        @BindView(R.id.swiperefresh)
        SwipeRefreshLayout swiperefresh;
        private List<YonghuData> yonghuDatas = new ArrayList<YonghuData>();
        private YonghuAdapter yonghuAdapter;

        private int lastVisibleItem;
        private LinearLayoutManager mLayoutManager;
        private int page = 1;

        @Override
        protected void init() {
            setLayoutRes(R.layout.fragment_person);

        }

        @Override
        protected void initEvent() {
            swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    setData(10);
                }
            });

            //recyclerview滚动监听实现自动加载
            rvYonghu.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
    //                0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；

                    setData(18);

                }
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                }
            });
        }

        @Override
        protected void setInitData() {

            mLayoutManager = new LinearLayoutManager(getActivity(),VERTICAL,false);
            rvYonghu.setLayoutManager(mLayoutManager);
            // 这句话是为了，第一次进入页面的时候显示加载进度条
            swiperefresh.setProgressViewOffset(false, 0
                    , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                            , 24, getResources().getDisplayMetrics()));
            setData(10);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {

            }

        }


        /**
         * 接口回调实现RecyclerView的item布局中每个控件的点击事件
         */
        private void itemOnClickListenner() {
            yonghuAdapter.setGuanzhuYonghuClickListenner(new GuanzhuYonghuClickListenner() {


                @Override
                public void showHead(View view, int position) {
                    LSUtils.showToast(getContext(), yonghuDatas.get(position).getAvator());
                }

                @Override
                public void showNick(View view, int position) {
                    LSUtils.showToast(getContext(), yonghuDatas.get(position).getName());
                }

                @Override
                public void showQianming(View view, int position) {
                    LSUtils.showToast(getContext(), yonghuDatas.get(position).getSignature());
                }

                @Override
                public void showIsGuanzhu(View view, int position) {


                }
            });
        }

        private void setData(int page) {
            swiperefresh.setRefreshing(true);
            yonghuDatas = new ArrayList<YonghuData>();
            for (int i = 1; i <= page; i++) {
                YonghuData yonghuData = new YonghuData();
                yonghuData.setId(i);
                yonghuData.setAvator("http://tp2.sinaimg.cn/1310331977/50/39997113790/0");
                yonghuData.setName("亲亲宝贝");
                yonghuData.setSignature("我家宝贝最可爱，哈哈哈");
                yonghuData.setIs_cancel(0);
                yonghuDatas.add(yonghuData);
            }
            if(yonghuAdapter==null){
                rvYonghu.setAdapter(yonghuAdapter = new YonghuAdapter(getActivity(),yonghuDatas));

            }else{
                yonghuAdapter.notifyDataSetChanged();
            }

            swiperefresh.setRefreshing(false);
            itemOnClickListenner();
        }
    }

    /**
     * 我的关注
     * Created by Administrator on 2016/7/28 0028.
     */
    public static class GuanZhuActivity extends BaseFragmentActivity {

        @BindView(R.id.iv_left)
        ImageView ivleft;
        @BindView(R.id.tabLayout)
        TabLayout tabLayout;
        @BindView(R.id.viewPager)
        ViewPager viewPager;

        @Override
        protected void init(Bundle arg0) {
            setContentView(R.layout.activity_guanzhu);

        }

        @Override
        protected void setInitData() {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }

        private void setupViewPager(ViewPager viewPager) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new WentiFragment(), "问题");
            adapter.addFragment(new YonghuFragment(), "用户");
            viewPager.setAdapter(adapter);
        }

        @Override
        protected void initEvent() {
            ivleft.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_left:
                    onKeyDown(KeyEvent.KEYCODE_BACK, null);
                    break;

            }
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
    }

    /**
     * Created by Administrator on 2016/8/15 0015.
     */
    public static class MeFragment extends BaseFragment {


        @BindView(R.id.mtb_title)
        MyTitleBar mtbTitle;
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_nick)
        TextView tvNick;
        @BindView(R.id.tv_qianming)
        TextView tvQianming;
        @BindView(R.id.ll_logined)
        LinearLayout llLogined;
        @BindView(R.id.tv_login)
        TextView tvLogin;
        @BindView(R.id.iv_xiaoxi)
        ImageView ivXiaoxi;
        @BindView(R.id.ll_xiaoxi)
        RelativeLayout llXiaoxi;
        @BindView(R.id.iv_chengjiu)
        ImageView ivChengjiu;
        @BindView(R.id.tv_chengjiu)
        TextView tvChengjiu;
        @BindView(R.id.ll_chengjiu)
        RelativeLayout llChengjiu;
        @BindView(R.id.iv_guanzhu)
        ImageView ivGuanzhu;
        @BindView(R.id.tv_guanzhu)
        TextView tvGuanzhu;
        @BindView(R.id.ll_guanzhu)
        RelativeLayout llGuanzhu;
        @BindView(R.id.iv_shoucang)
        ImageView ivShoucang;
        @BindView(R.id.tv_shoucang)
        TextView tvShoucang;
        @BindView(R.id.ll_shoucang)
        RelativeLayout llShoucang;
        @BindView(R.id.iv_caogao)
        ImageView ivCaogao;
        @BindView(R.id.tv_caogao)
        TextView tvCaogao;
        @BindView(R.id.ll_caogao)
        RelativeLayout llCaogao;
        @BindView(R.id.iv_shezhi)
        ImageView ivShezhi;
        @BindView(R.id.tv_shezhi)
        TextView tvShezhi;
        @BindView(R.id.ll_shezhi)
        RelativeLayout llShezhi;
        @BindView(R.id.main)
        LinearLayout main;
        @BindView(R.id.mainLayout)
        LinearLayout mainLayout;

        @Override
        protected void init() {
            setLayoutRes(R.layout.fragment_me);
        }

        @Override
        protected void initEvent() {
            llShezhi.setOnClickListener(this);
        }

        @Override
        protected void setInitData() {
            mtbTitle.setText("我的");
            llChengjiu.setOnClickListener(this);
            llXiaoxi.setOnClickListener(this);
            llGuanzhu.setOnClickListener(this);
            llShoucang.setOnClickListener(this);
            llCaogao.setOnClickListener(this);
            llShezhi.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_chengjiu:
                    if(TextUtils.isEmpty(tvNick.getText())){
                        LSUtils.showToast(getContext(),"请登录");
                    }else {
                        Intent i = new Intent(getActivity(), AchievedActivity.class);
                        startActivity(i);
                        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }
                    break;
                case R.id.ll_guanzhu:
                    if(TextUtils.isEmpty(tvNick.getText())){
                        LSUtils.showToast(getContext(),"请登录");
                    }else {
                        Intent i = new Intent(getActivity(), GuanZhuActivity.class);
                        startActivity(i);
                        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }
                    break;
                case R.id.ll_shoucang:
                    if(TextUtils.isEmpty(tvNick.getText())){
                        LSUtils.showToast(getContext(),"请登录");
                    }else {
                        Intent i = new Intent(getActivity(), ShoucangActivity.class);
                        startActivity(i);
                        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }
                    break;
                case R.id.ll_caogao:
                    if(TextUtils.isEmpty(tvNick.getText())){
                        LSUtils.showToast(getContext(),"请登录");
                    }else {
                        Intent i = new Intent(getActivity(), CaoGaoActivity.class);
                        startActivity(i);
                        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }
                    break;

                case R.id.ll_shezhi:
                    Intent i = new Intent(getActivity(), SettingActivity.class);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;

            }

        }

    }

    /**
     * Created by Administrator on 2016/8/15 0015.
     */
    public static class SettingActivity extends BaseFragmentActivity {

        @BindView(R.id.mtb_title)
        MyTitleBar myTitleBar;
        @BindView(R.id.iv_toggle)
        ImageView iv_toggle;
        @BindView(R.id.re_link)
        RelativeLayout re_link;
        @BindView(R.id.iv_account)
        ImageView ivAccount;
        @BindView(R.id.re_account)
        RelativeLayout reAccount;
        @BindView(R.id.iv_link)
        ImageView ivLink;
        @BindView(R.id.iv_call_mody)
        ImageView ivCallMody;
        @BindView(R.id.iv_shuoming)
        ImageView ivShuoming;
        @BindView(R.id.iv_clear)
        ImageView ivClear;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.iv_xiangyou)
        ImageView ivXiangyou;
        @BindView(R.id.ll_clear)
        RelativeLayout llClear;
        @BindView(R.id.iv_fankui)
        ImageView ivFankui;
        @BindView(R.id.iv_about)
        ImageView ivAbout;


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
            myTitleBar.setLeftImage(R.drawable.icon_back);
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
            switch (v.getId()) {
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
                    break;*/
                case R.id.re_link:
                    Intent i = new Intent(SettingActivity.this,BluetoothChat.class);
                    startActivity(i);
                    break;

            }

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

    }

    /**
     * Created by Administrator on 2016/8/22 0022.
     */
    public static class ShoucangActivity extends BaseFragmentActivity {


        @BindView(R.id.rv_ques)
        RecyclerView rvQues;
        @BindView(R.id.scroll_home)
        HomeScrollView scrollHome;
        @BindView(R.id.swipelayout)
        SwipeRefreshLayout swipelayout;
        @BindView(R.id.iv_left)
        ImageView ivLeft;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_top)
        RelativeLayout ivTop;

        private List<HomeData> homeDatas = new ArrayList<HomeData>();
        private HomeAdapter1 homeAdapter;
        private FullyLinearLayoutManager mLayoutManager;


        /**
         * 初始化布局
         *
         * @param arg0
         */
        @Override
        protected void init(Bundle arg0) {
            setContentView(R.layout.activity_shoucang);
        }

        @Override
        protected void initEvent() {
            ivLeft.setOnClickListener(this);
        }


        @Override
        protected void setInitData() {

            scrollHome.setSwipeRefreshLayout(swipelayout);
            swipelayout.setColorSchemeResources(
                    android.R.color.holo_blue_light,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
            );
            getData();

            mLayoutManager = new FullyLinearLayoutManager(ShoucangActivity.this, VERTICAL, false);
            rvQues.setLayoutManager(mLayoutManager);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_left:
                    onKeyDown(KeyEvent.KEYCODE_BACK, null);
                    break;
            }

        }


        private void getData() {


            for (int i = 1; i <= 10; i++) {
                HomeData homeData = new HomeData();
                homeData.set_id(i + "");
                homeData.setTitle("宝宝为何喜欢咬书和撕书？");
                homeData.setAvator("http://tp2.sinaimg.cn/1619687665/50/5642384100/1");
                homeData.setName("倪妈妈");
                homeData.setPraise_up_count(2 + 10 * 2);
                homeData.setComment(22 + 10 * 3);
                homeDatas.add(homeData);
            }
            homeAdapter = new HomeAdapter1(ShoucangActivity.this, homeDatas);
            rvQues.setAdapter(homeAdapter);
            rvQues.addItemDecoration(new ListItemDecoration(ShoucangActivity.this, LinearLayoutManager.VERTICAL));
            rvQues.setFocusable(false);
            scrollHome.smoothScrollTo(0, 0);
            itemOnClickListenner(); //item监听事件

        }

        /**
         * 接口回调实现RecyclerView的item布局中每个控件的点击事件
         */
        private void itemOnClickListenner() {
            homeAdapter.setHomeClickListenner(new HomeClickListenner() {
                @Override
                public void showTopic(View view, int position) {

                }

                @Override
                public void showQuestion(View view, int position) {
                    //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getQuestion());

                }

                @Override
                public void showAnswer(View view, int position) {
                    //LSUtils.showToast(getContext(), "点击了我" + homeDatas.getData().get(position).getContent());

                }

                @Override
                public void showTopicImg(View view, int position) {
                    LSUtils.showToast(ShoucangActivity.this, "点击了我图片");
                }

                @Override
                public void showhead(View view, int position) {
                    LSUtils.showToast(ShoucangActivity.this, "点击了我头像");

                }

                @Override
                public void showUsername(View view, int position) {
                    //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getUsername());
                }

                @Override
                public void showAgreecount(View view, int position) {
                    //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getAgreecount());
                }

                @Override
                public void showTalkcount(View view, int position) {
                    //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getTalkcount());
                }
            });
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
    }
}
