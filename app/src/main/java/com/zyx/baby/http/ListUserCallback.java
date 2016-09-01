package com.zyx.baby.http;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;
import com.zyx.baby.bean.User;
import com.zyx.baby.utils.LSUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/28 0028.
 */
public abstract class ListUserCallback extends Callback<List<User>> {

    @Override
    public List<User> parseNetworkResponse(Response response, int i) throws IOException {
        String string = response.body().string();
        List<User> user = new Gson().fromJson(string, new TypeToken<List<User>>(){}.getType());
        return user;
    }
}
