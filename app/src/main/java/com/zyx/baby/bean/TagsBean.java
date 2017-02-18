package com.zyx.baby.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */

public class TagsBean {


    /**
     * data : {"listAllTags":[{"id":1,"tag":"妈妈分享"},{"id":2,"tag":"经验之谈"},{"id":3,"tag":"宝宝病痛"},{"id":4,"tag":"亲子活动"},{"id":5,"tag":"吃啥补啥"},{"id":6,"tag":"宝宝用品"}],"listTagsByUser1":[{"id":2,"tag":"经验之谈"},{"id":4,"tag":"亲子活动"}]}
     * errorCode : 0
     * message : 获取数据成功
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
        private List<ListAllTagsBean> listAllTags;
        private List<ListTagsByUser1Bean> listTagsByUser1;

        public List<ListAllTagsBean> getListAllTags() {
            return listAllTags;
        }

        public void setListAllTags(List<ListAllTagsBean> listAllTags) {
            this.listAllTags = listAllTags;
        }

        public List<ListTagsByUser1Bean> getListTagsByUser1() {
            return listTagsByUser1;
        }

        public void setListTagsByUser1(List<ListTagsByUser1Bean> listTagsByUser1) {
            this.listTagsByUser1 = listTagsByUser1;
        }

        public static class ListAllTagsBean {
            /**
             * id : 1
             * tag : 妈妈分享
             */

            private int id;
            private String tag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }
        }

        public static class ListTagsByUser1Bean {
            /**
             * id : 2
             * tag : 经验之谈
             */

            private int id;
            private String tag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }
        }
    }
}
