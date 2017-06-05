package com.zyx.baby.presenters;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zyx.baby.bean.DrugClassifyBean;
import com.zyx.baby.bean.DrugListBean;
import com.zyx.baby.bean.SearchNewsBean;
import com.zyx.baby.http.ApisUtil;
import com.zyx.baby.presenters.viewinface.DrugClassifyView;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/22.
 * 搜索健康知识
 */

public class DrugHelper {
    private static final String TAG = "DrugHelper";
    private Context mContext;
    private DrugClassifyView mDrugClassifyView;

    public DrugHelper(Context context, DrugClassifyView drugClassifyView) {
        mContext = context;
        mDrugClassifyView = drugClassifyView;
    }

    public void reqClassify() {

        OkGo.get(ApisUtil.DRUG_CLASSFY_URL).tag(mContext).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, s);
                try {
                    DrugClassifyBean result = new Gson().fromJson(s, DrugClassifyBean.class);
                    mDrugClassifyView.reqSucc(result);

                } catch (Exception e) {
                    e.printStackTrace();
                    mDrugClassifyView.reqFail("服务器网络开小差，请稍等。。。");
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mDrugClassifyView.reqFail("服务器网络开小差，请稍等。。。");
            }
        });
    }


    public void reqDrugs(int id, int page, int rows) {
        HashMap<String,String> params = new HashMap<>();
        params.put("id", id+"");
        params.put("page", page+"");
        params.put("rows", rows+"");
        OkGo.get(ApisUtil.DRUG_LIST_URL).tag(mContext).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, s);
                try {
                    DrugListBean result = new Gson().fromJson(s, DrugListBean.class);
                    mDrugClassifyView.reqDrugs(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    mDrugClassifyView.reqFail("服务器网络开小差，请稍等。。。");
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mDrugClassifyView.reqFail("服务器网络开小差，请稍等。。。");
            }
        });
    }

}
