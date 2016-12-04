package com.zyx.baby.event;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public abstract class ObjEvent {

    private String tag;

    private Object obj;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
