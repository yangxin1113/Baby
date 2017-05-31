package com.zyx.baby.presenters.viewinface;

import com.zyx.baby.bean.KnowledgeList;

/**
 * Created by zyx on 2017/5/29.
 */

public interface NewsView {
    void reqSucc(KnowledgeList knowledgeList);
    void reqFail(String msg);
}
