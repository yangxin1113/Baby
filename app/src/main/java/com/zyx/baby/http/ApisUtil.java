package com.zyx.baby.http;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zyx.baby.event.LoginEvent;
import com.zyx.baby.utils.JsonException;
import com.zyx.baby.utils.MapJsonUtil;
import com.zyx.baby.utils.UserInfoUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/27 0027.
 */
public class ApisUtil {
    public static final String TAG = "ApisUtil";
    public static final String BASE_URL = "http://www.tngou.net/api/";
    public static final String NEWS_URL = BASE_URL + "lore/list";
    public static final String NEW_DETAIL_URL = BASE_URL + "lore/show";
    public static final String BASE_URL_IMG = "http://tnfs.tngou.net/image";
    public static final String SEARCh_URL = BASE_URL + "search";
    public static final String DRUG_CLASSFY_URL = BASE_URL + "drug/classify";
    public static final String DRUG_LIST_URL = BASE_URL + "drug/list";
    public static final String DRUG_DETAIL_URL = BASE_URL + "drug/show";

















    public static final String IP = "http://42.96.150.57:8080/Baby_android/";
    public static final String LOCAL_IP = "http://172.18.4.104:8080/Baby_android/";
    public static final String LOGIN_ACTION = IP+"Login";
    public static final String GETTAGS_ACTION = IP+"getTags";

    public static final String SERVER = "http://server.jeasonlzy.com/OkHttpUtils/";
    //    public static final String SERVER = "http://192.168.1.121:8080/OkHttpUtils/";
    public static final String URL_METHOD = SERVER + "method";
    public static final String URL_CACHE = SERVER + "cache";
    public static final String URL_IMAGE = SERVER + "image";
    public static final String URL_JSONOBJECT = SERVER + "jsonObject";
    public static final String URL_JSONARRAY = SERVER + "jsonArray";
    public static final String URL_FORM_UPLOAD = SERVER + "upload";
    public static final String URL_TEXT_UPLOAD = SERVER + "uploadString";
    public static final String URL_DOWNLOAD = SERVER + "download";
    public static final String URL_REDIRECT = SERVER + "redirect";

    public static final String NEWS = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";
    public static final String APIKEY = "593e074aa96b18276fbe1aec8992f398";
    public static final String login_url = "593e074aa96b18276fbe1aec8992f398";
    public static final String other_login_url = "593e074aa96b18276fbe1aec8992f398";
    public static final String logout_url = "593e074aa96b18276fbe1aec8992f398";


    public static void login(final Context context, final HashMap<String, String> params, final int type) {
        String url = "";
        if (type == 1) {
            url = ApisUtil.login_url;
        } else {
            url = ApisUtil.other_login_url;
        }
        OkGo.post(url).tag(context).params(params).execute(new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.optBoolean("status")) {
                        JsonException.JSON_TYPE json_type = JsonException.getJSONType(jsonObject.optString("data"));
                        if (json_type.equals(JsonException.JSON_TYPE.JSON_TYPE_OBJECT)) {
                            UserInfoUtils.putString(context, "login_type", type + "");
                            UserInfoUtils.putString(context, "login_info", MapJsonUtil.hashMapToJson(params));
                            saveInfo(jsonObject.getJSONObject("data").getJSONObject("userInfo"), context);
                            EventBus.getDefault().post(new LoginEvent());
                            Log.e(TAG, "obj");
                        } else {
                            Log.e(TAG, "err");
                        }
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Log.e(TAG, "服务器异常");
            }
        });
    }

    private static void saveInfo(JSONObject data, Context context) {
        UserInfoUtils.putString(context, "id", data.optString("id"));
        UserInfoUtils.putString(context, "name", data.optString("name"));
        UserInfoUtils.putString(context, "sex", data.optString("sex"));
        UserInfoUtils.putString(context, "isanchor", data.optString("isanchor"));
        UserInfoUtils.putString(context, "signature", data.optString("signature"));
        UserInfoUtils.putString(context, "avator", data.optString("avator"));
        UserInfoUtils.putString(context, "is_realname", data.optString("is_realname"));
        UserInfoUtils.putString(context, "is_pay_pass", data.optString("is_pay_pass"));
        UserInfoUtils.putString(context, "phone", data.optString("phone"));
        UserInfoUtils.putString(context, "is_order_take", data.optString("is_order_take"));//1为接单，2为不接单
        UserInfoUtils.putString(context, "is_contact", "2");//1为已经联系过客服，2为未联系过
        UserInfoUtils.putString(context, "room_id", data.optString("room_id"));//主播房间id
        UserInfoUtils.putString(context, "letter_unread", data.optString("letter_unread"));
        UserInfoUtils.putString(context, "fishbean", data.optString("fishbean"));//鱼豆
    }

    public static void logout(final Context context) {
        OkGo.post(logout_url).tag(context).execute(new StringCallback() {


            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, s);
                OkGo.getInstance().getCookieJar().getCookieStore().removeAllCookie();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);

            }
        });
    }
}
