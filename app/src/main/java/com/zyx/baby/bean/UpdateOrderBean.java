package com.zyx.baby.bean;

/**
 *
 *@author chenwuchao
 *@time 2017/3/9 17:05
 */
public class UpdateOrderBean {
    private int status;
    private String alert;
    private String other_data;
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getOther_data() {
        return other_data;
    }

    public void setOther_data(String other_data) {
        this.other_data = other_data;
    }

    public static class DataBean {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
