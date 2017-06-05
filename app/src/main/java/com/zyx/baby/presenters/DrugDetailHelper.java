package com.zyx.baby.presenters;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zyx.baby.bean.DrugDetailBean;
import com.zyx.baby.bean.KnowledgeDetail;
import com.zyx.baby.http.ApisUtil;
import com.zyx.baby.presenters.viewinface.DrugDetailView;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/22.
 */

public class DrugDetailHelper {
    private static final String TAG = "NewsDetailHelper";
    private Context mContext;
    private DrugDetailView mDrugDetailView;

    public DrugDetailHelper(Context context, DrugDetailView newsView) {
        mContext = context;
        mDrugDetailView = newsView;
    }

    public void reqNews(int id) {
        HashMap<String,String> params = new HashMap<>();
        params.put("id", id+"");
        OkGo.get(ApisUtil.DRUG_DETAIL_URL).tag(mContext).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, s);
                try {
                    DrugDetailBean result = new Gson().fromJson(s, DrugDetailBean.class);
                    mDrugDetailView.reqSucc(result);

                } catch (Exception e) {
                    e.printStackTrace();
                    mDrugDetailView.reqFail("服务器网络开小差，请稍等。。。");
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mDrugDetailView.reqFail("服务器网络开小差，请稍等。。。");
            }
        });
    }

}
