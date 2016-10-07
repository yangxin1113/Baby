package com.zyx.baby.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class HomeData {


    /**
     * _id : 57e8b1165119392e000066f1
     * uid : 200983
     * title : 分为王菲菲
     * createtime : 2016-09-26
     * updatetime : 1474867478
     * content : [["img","CDN/question/201609261324114904.jpg"],["img","CDN/question/201609261324215571.jpg"],["text","人潍坊威锋网"],["text","分为非威风威风威风"],["text","分为分为访问分为非违法未"]]
     * topic : ["英雄联盟"]
     * comment : 0
     * praise_id : []
     * keep : 0
     * is_delete : 0
     * answer : 1
     * status : 1
     * concern : 1
     * is_anonymous : 1
     * name : 哇哈哈
     * signature : 呦，呦，切克闹
     * avator : https://flycat.oss-cn-hangzhou.aliyuncs.com/CDN/image/201609251833418636.jpg
     * praise_up_count : 0
     */

    private String _id;
    private int uid;
    private String title;
    private String createtime;
    private int updatetime;
    private int comment;
    private int keep;
    private int is_delete;
    private int answer;
    private int status;
    private int concern;
    private int is_anonymous;
    private String name;
    private String signature;
    private String avator;
    private int praise_up_count;
    private List<List<String>> content;
    private List<String> topic;
    private List<?> praise_id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(int updatetime) {
        this.updatetime = updatetime;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getKeep() {
        return keep;
    }

    public void setKeep(int keep) {
        this.keep = keep;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getConcern() {
        return concern;
    }

    public void setConcern(int concern) {
        this.concern = concern;
    }

    public int getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(int is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public int getPraise_up_count() {
        return praise_up_count;
    }

    public void setPraise_up_count(int praise_up_count) {
        this.praise_up_count = praise_up_count;
    }

    public List<List<String>> getContent() {
        return content;
    }

    public void setContent(List<List<String>> content) {
        this.content = content;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }

    public List<?> getPraise_id() {
        return praise_id;
    }

    public void setPraise_id(List<?> praise_id) {
        this.praise_id = praise_id;
    }
}
