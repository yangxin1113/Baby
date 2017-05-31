package com.zyx.baby.presenters;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zyx.baby.bean.KnowledgeDetail;
import com.zyx.baby.bean.KnowledgeList;
import com.zyx.baby.http.ApisUtil;
import com.zyx.baby.presenters.viewinface.NewsDetailView;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/22.
 */

public class NewsDetailHelper {
    private static final String TAG = "NewsDetailHelper";
    private Context mContext;
    private NewsDetailView mNewsDetailView;

    public NewsDetailHelper(Context context, NewsDetailView newsView) {
        mContext = context;
        mNewsDetailView = newsView;
    }

    public void reqNews(int id) {
        HashMap<String,String> params = new HashMap<>();
        params.put("id", id+"");
        OkGo.get(ApisUtil.NEW_DETAIL_URL).tag(mContext).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, s);
                try {
                    KnowledgeDetail result = new Gson().fromJson(s, KnowledgeDetail.class);
                    mNewsDetailView.reqSucc(result);

                } catch (Exception e) {
                    e.printStackTrace();
                    mNewsDetailView.reqFail("服务器网络开小差，请稍等。。。");
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mNewsDetailView.reqFail("服务器网络开小差，请稍等。。。");
            }
        });
    }

}
