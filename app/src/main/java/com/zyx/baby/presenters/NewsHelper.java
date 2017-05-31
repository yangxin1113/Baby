package com.zyx.baby.presenters;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zyx.baby.bean.KnowledgeList;
import com.zyx.baby.http.ApisUtil;
import com.zyx.baby.presenters.viewinface.NewsView;
import com.zyx.baby.presenters.viewinface.RegisterView;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/22.
 */

public class NewsHelper {
    private static final String TAG = "NewsHelper";
    private Context mContext;
    private NewsView mNewsView;

    public NewsHelper(Context context, NewsView newsView) {
        mContext = context;
        mNewsView = newsView;
    }

    public void reqNews(int id, int page, int rows) {
        HashMap<String,String> params = new HashMap<>();
        params.put("id", id+"");
        params.put("page", page+"");
        params.put("rows", rows+"");
        OkGo.get(ApisUtil.NEWS_URL).tag(mContext).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, s);
                try {
                    KnowledgeList result = new Gson().fromJson(s, KnowledgeList.class);
                    mNewsView.reqSucc(result);

                } catch (Exception e) {
                    e.printStackTrace();
                    mNewsView.reqFail("服务器网络开小差，请稍等。。。");
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mNewsView.reqFail("服务器网络开小差，请稍等。。。");
            }
        });
    }

}
