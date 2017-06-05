package com.zyx.baby.presenters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zyx.baby.bean.DrugListBean;
import com.zyx.baby.bean.KnowledgeList;
import com.zyx.baby.bean.SearchNewsBean;
import com.zyx.baby.http.ApisUtil;
import com.zyx.baby.presenters.viewinface.SearchNewsView;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/22.
 * 搜索健康知识
 */

public class SearchHelper {
    private static final String TAG = "SearchHelper";
    private Context mContext;
    private SearchNewsView mSearchNewsView;

    public SearchHelper(Context context, SearchNewsView newsView) {
        mContext = context;
        mSearchNewsView = newsView;
    }

    public void searchNews(String keyWord, final String name, int page, int rows, int classify) {
        HashMap<String,String> params = new HashMap<>();
        params.put("keyword", keyWord);
        params.put("name", name);
        params.put("page", page+"");
        params.put("rows", rows+"");
        params.put("classify", classify+"");
        OkGo.get(ApisUtil.SEARCh_URL).tag(mContext).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, s);
                try {　
                    if(TextUtils.equals(name, "drug")){
                        SearchNewsBean result = new Gson().fromJson(s, SearchNewsBean.class);
                        mSearchNewsView.reqSucc(result);
                    }else if(TextUtils.equals(name, "drug")){
                        DrugListBean result = new Gson().fromJson(s, DrugListBean.class);
                        mSearchNewsView.reqSucc(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mSearchNewsView.reqFail("服务器网络开小差，请稍等。。。");
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mSearchNewsView.reqFail("服务器网络开小差，请稍等。。。");
            }
        });
    }

}
