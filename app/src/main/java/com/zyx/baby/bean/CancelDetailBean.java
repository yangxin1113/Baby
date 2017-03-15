package com.zyx.baby.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/9.
 */
public class CancelDetailBean {

    private String status;
    private DataBean data;
    private String alert;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public static class DataBean {
        private String _id;
        private String uid;
        private int touid;
        private String order_id;
        private String reason;
        private String images;
        private int before_status;
        private int status;
        private int create_time;
        private int update_time;
        //        订单买家退回金额 或 打手获得赔偿金
        private String return_money;
        private String super_id;
        //安全保证金
        private float security_deposit;
        //效率保证金
        private float efficiency_deposit;
        //        退回保证金
        private String return_deposit;
        //订单买家支付价格
        private String price;
        private int type;
        //1-》买家 2-》打手
        //图片集合形式
        private ArrayList<String> img;


        public ArrayList<String> getImg() {
            return img;
        }

        public void setImg(ArrayList<String> img) {
            this.img = img;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getTouid() {
            return touid;
        }

        public void setTouid(int touid) {
            this.touid = touid;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getBefore_status() {
            return before_status;
        }

        public void setBefore_status(int before_status) {
            this.before_status = before_status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public String getReturn_money() {
            return return_money;
        }

        public void setReturn_money(String return_money) {
            this.return_money = return_money;
        }

        public String getSuper_id() {
            return super_id;
        }

        public void setSuper_id(String super_id) {
            this.super_id = super_id;
        }

        public float getSecurity_deposit() {
            return security_deposit;
        }

        public void setSecurity_deposit(float security_deposit) {
            this.security_deposit = security_deposit;
        }

        public float getEfficiency_deposit() {
            return efficiency_deposit;
        }

        public void setEfficiency_deposit(float efficiency_deposit) {
            this.efficiency_deposit = efficiency_deposit;
        }

        public String getReturn_deposit() {
            return return_deposit;
        }

        public void setReturn_deposit(String return_deposit) {
            this.return_deposit = return_deposit;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
