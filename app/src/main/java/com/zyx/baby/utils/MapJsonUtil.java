package com.zyx.baby.utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MapJsonUtil {
    /**把数据源HashMap转换成json
     * @param map
     */
    public static String hashMapToJson(HashMap map) {
        String string = "{";
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry e = (Map.Entry) it.next();
            string += "'" + e.getKey() + "':";
            string += "'" + e.getValue() + "',";
        }
        string = string.substring(0, string.lastIndexOf(","));
        string += "}";
        return string;
    }

    /**把数据源json转换成HashMap
     * @param map
     */
    public static Map<String,String>  JsonTohashMap(JSONObject data) {
        Map<String,String> map = null;
        try {
            map = new HashMap<String, String>();
            map.put("from", data.optString("from"));
            map.put("username", data.optString("username"));
            map.put("password", data.optString("password"));
            map.put("key", data.optString("key"));
            map.put("avator", data.optString("avator"));
            map.put("type", data.optString("type"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return map;
    }

}
