package com.zyx.baby.http;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zyx.baby.bean.LoginBean;
import com.zyx.baby.bean.NewsModel;
import com.zyx.baby.bean.NewsResponse;
import com.zyx.baby.bean.TagsBean;
import com.zyx.baby.callback.NewsCallback;
import com.zyx.baby.event.ErrorEvent;
import com.zyx.baby.event.LoginEvent;
import com.zyx.baby.event.TagsEvent;
import com.zyx.baby.utils.LSUtils;

import org.greenrobot.eventbus.EventBus;

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
        OkGo.post(ApisUtil.URL_DOWNLOAD).execute(new NewsCallback<NewsResponse<NewsModel>>() {
            @Override
            public void onSuccess(NewsResponse<NewsModel> newsModelNewsResponse, Call call, Response response) {

            }
        });
    }

    public static void login(Map<String, String> params, final String tag){
        OkGo.post(ApisUtil.LOGIN_ACTION).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                LoginEvent loginEvent = new LoginEvent();
                LoginBean loginBean = gson.fromJson(s,LoginBean.class);
                loginEvent.setTag(tag);
                loginEvent.setObj(loginBean);
                EventBus.getDefault().post(loginEvent);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                ErrorEvent errorEvent = new ErrorEvent();
                errorEvent.setTag(tag);
                errorEvent.setObj("服务器请求错误");
                EventBus.getDefault().post(errorEvent);
            }
        });
    }

    public static void getTags(Map<String, String> params, final String tag){
        OkGo.get(ApisUtil.GETTAGS_ACTION).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                TagsEvent tagsEvent = new TagsEvent();
                TagsBean tagsBean = gson.fromJson(s,TagsBean.class);
                LSUtils.e(tag,s);
                tagsEvent.setTag(tag);
                tagsEvent.setObj(tagsBean);
                EventBus.getDefault().post(tagsEvent);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                ErrorEvent errorEvent = new ErrorEvent();
                errorEvent.setTag(tag);
                errorEvent.setObj("服务器请求错误");
                EventBus.getDefault().post(errorEvent);
            }
        });
    }
}
