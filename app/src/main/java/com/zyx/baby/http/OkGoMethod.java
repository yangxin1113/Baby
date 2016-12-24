package com.zyx.baby.http;

import com.lzy.okgo.OkGo;
import com.zyx.baby.bean.NewsModel;
import com.zyx.baby.bean.NewsResponse;
import com.zyx.baby.callback.NewsCallback;
import com.zyx.baby.event.ErrorEvent;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/23.
 */

public class OkGoMethod {

    private static final String TAG ="OkGoMethod";

    public static void isUserExit(Map<String, String> params, String tag){
        final ErrorEvent errorEvent = new ErrorEvent();
        OkGo.post(Apis.URL_DOWNLOAD).execute(new NewsCallback<NewsResponse<NewsModel>>() {
            @Override
            public void onSuccess(NewsResponse<NewsModel> newsModelNewsResponse, Call call, Response response) {

            }
        });
    }
}
