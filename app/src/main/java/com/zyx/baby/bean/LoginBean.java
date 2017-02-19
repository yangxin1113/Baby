package com.zyx.baby.bean;

/**
 * Created by ${$USER} on 2017/2/19.
 */

public class LoginBean {

    /**
     * data : {"nickname":"Mrs.Zhang","phone":"13411111111","pwd":"123456","sex":"女"}
     * errorCode : 0
     * message : 登陆成功
     */

    private DataBean data;
    private String errorCode;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * nickname : Mrs.Zhang
         * phone : 13411111111
         * pwd : 123456
         * sex : 女
         */

        private String nickname;
        private String phone;
        private String pwd;
        private String sex;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
