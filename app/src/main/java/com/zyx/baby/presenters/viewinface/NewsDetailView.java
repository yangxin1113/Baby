package com.zyx.baby.presenters.viewinface;

import com.zyx.baby.bean.KnowledgeDetail;

/**
 * Created by zyx on 2017/5/29.
 */

public interface NewsDetailView {
    void reqSucc(KnowledgeDetail knowledgeDetail);
    void reqFail(String msg);
}
