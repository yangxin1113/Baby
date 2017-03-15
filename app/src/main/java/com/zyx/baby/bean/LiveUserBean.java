package com.zyx.baby.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class LiveUserBean {

    /**
     * status : 1
     * data : [{"_id":"586119fdacdbc42ebf3e93dd","anchor_id":209870,"uid":1025,"status":1,"createtime":1482758653,"updatetime":1482758755,"userinfo":{"id":"1025","deleted":"0","nickname":"符文之地","username":"test5","realname":"老王","phone":"13732273863","password":"e07ea24ac3e86c23fb107f591bebe5ec","email":"","key":"","avator":"http://flashfish.oss-cn-hangzhou.aliyuncs.com/CDN/image/android_1481175522658.jpg","oauthavator":"","level":"","islock":"0","lockreason":"","inner":"0","openid":"","isshop":"","created":"1353075091","isanchor":"1","qq":"","autograph":"","tell":"0","parent_id":"","is_order_state":"1","is_notice_state":"1","concern":"1","be_concern":"4"}}]
     * alert : 获取禁言列表成功
     * other_data :
     */

    private int status;
    private String alert;
    private String other_data;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * _id : 586119fdacdbc42ebf3e93dd
         * anchor_id : 209870
         * uid : 1025
         * status : 1
         * createtime : 1482758653
         * updatetime : 1482758755
         * userinfo : {"id":"1025","deleted":"0","nickname":"符文之地","username":"test5","realname":"老王","phone":"13732273863","password":"e07ea24ac3e86c23fb107f591bebe5ec","email":"","key":"","avator":"http://flashfish.oss-cn-hangzhou.aliyuncs.com/CDN/image/android_1481175522658.jpg","oauthavator":"","level":"","islock":"0","lockreason":"","inner":"0","openid":"","isshop":"","created":"1353075091","isanchor":"1","qq":"","autograph":"","tell":"0","parent_id":"","is_order_state":"1","is_notice_state":"1","concern":"1","be_concern":"4"}
         */

        private String _id;
        private int anchor_id;
        private int uid;
        private int status;
        private int createtime;
        private int updatetime;
        private UserinfoBean userinfo;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getAnchor_id() {
            return anchor_id;
        }

        public void setAnchor_id(int anchor_id) {
            this.anchor_id = anchor_id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean {
            /**
             * id : 1025
             * deleted : 0
             * nickname : 符文之地
             * username : test5
             * realname : 老王
             * phone : 13732273863
             * password : e07ea24ac3e86c23fb107f591bebe5ec
             * email :
             * key :
             * avator : http://flashfish.oss-cn-hangzhou.aliyuncs.com/CDN/image/android_1481175522658.jpg
             * oauthavator :
             * level :
             * islock : 0
             * lockreason :
             * inner : 0
             * openid :
             * isshop :
             * created : 1353075091
             * isanchor : 1
             * qq :
             * autograph :
             * tell : 0
             * parent_id :
             * is_order_state : 1
             * is_notice_state : 1
             * concern : 1
             * be_concern : 4
             */

            private String id;
            private String deleted;
            private String nickname;
            private String username;
            private String realname;
            private String phone;
            private String password;
            private String email;
            private String key;
            private String avator;
            private String oauthavator;
            private String level;
            private String islock;
            private String lockreason;
            private String inner;
            private String openid;
            private String isshop;
            private String created;
            private String isanchor;
            private String qq;
            private String autograph;
            private String tell;
            private String parent_id;
            private String is_order_state;
            private String is_notice_state;
            private String concern;
            private String be_concern;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDeleted() {
                return deleted;
            }

            public void setDeleted(String deleted) {
                this.deleted = deleted;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getAvator() {
                return avator;
            }

            public void setAvator(String avator) {
                this.avator = avator;
            }

            public String getOauthavator() {
                return oauthavator;
            }

            public void setOauthavator(String oauthavator) {
                this.oauthavator = oauthavator;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getIslock() {
                return islock;
            }

            public void setIslock(String islock) {
                this.islock = islock;
            }

            public String getLockreason() {
                return lockreason;
            }

            public void setLockreason(String lockreason) {
                this.lockreason = lockreason;
            }

            public String getInner() {
                return inner;
            }

            public void setInner(String inner) {
                this.inner = inner;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getIsshop() {
                return isshop;
            }

            public void setIsshop(String isshop) {
                this.isshop = isshop;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getIsanchor() {
                return isanchor;
            }

            public void setIsanchor(String isanchor) {
                this.isanchor = isanchor;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getAutograph() {
                return autograph;
            }

            public void setAutograph(String autograph) {
                this.autograph = autograph;
            }

            public String getTell() {
                return tell;
            }

            public void setTell(String tell) {
                this.tell = tell;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getIs_order_state() {
                return is_order_state;
            }

            public void setIs_order_state(String is_order_state) {
                this.is_order_state = is_order_state;
            }

            public String getIs_notice_state() {
                return is_notice_state;
            }

            public void setIs_notice_state(String is_notice_state) {
                this.is_notice_state = is_notice_state;
            }

            public String getConcern() {
                return concern;
            }

            public void setConcern(String concern) {
                this.concern = concern;
            }

            public String getBe_concern() {
                return be_concern;
            }

            public void setBe_concern(String be_concern) {
                this.be_concern = be_concern;
            }
        }
    }
}
