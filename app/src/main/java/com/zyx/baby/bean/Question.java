package com.zyx.baby.bean;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class Question {

    private int id;
    private String question;
    private int answerCount;
    private int watcherCount;
    private int page;

    public Question(int id, String question, int answerCount, int watcherCount){
        this.id = id;
        this.question = question;
        this.answerCount = answerCount;
        this.watcherCount = watcherCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getWatcherCount() {
        return watcherCount;
    }

    public void setWatcherCount(int watcherCount) {
        this.watcherCount = watcherCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
