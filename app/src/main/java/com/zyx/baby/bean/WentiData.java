package com.zyx.baby.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29 0029.
 */

public class WentiData {


    /**
     * _id : 57dfc8f0acdbc4028324fd97
     * answer : 3
     * comment : 0
     * concern : 4
     * content : []
     * createtime : 1474283760
     * imgurl :
     * is_anonymous : 0
     * is_delete : 0
     * keep : 0
     * praise_id : []
     * status : 1
     * title : 英雄联盟第一美女是谁
     * topic : 英雄联盟
     * uid : 200984
     * updatetime : 1474283760
     */

    private String _id;
    private int answer;
    private int comment;
    private int concern;
    private int createtime;
    private String imgurl;
    private int is_anonymous;
    private int is_delete;
    private int keep;
    private int status;
    private String title;
    private String topic;
    private int uid;
    private int updatetime;
    private List<?> content;
    private List<?> praise_id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getConcern() {
        return concern;
    }

    public void setConcern(int concern) {
        this.concern = concern;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(int is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public int getKeep() {
        return keep;
    }

    public void setKeep(int keep) {
        this.keep = keep;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(int updatetime) {
        this.updatetime = updatetime;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    public List<?> getPraise_id() {
        return praise_id;
    }

    public void setPraise_id(List<?> praise_id) {
        this.praise_id = praise_id;
    }
}
