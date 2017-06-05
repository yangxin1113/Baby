package com.zyx.baby.presenters.viewinface;

import com.zyx.baby.bean.KnowledgeList;
import com.zyx.baby.bean.SearchNewsBean;

/**
 * Created by zyx on 2017/5/29.
 */

public interface SearchNewsView {
    void reqSucc(Object searchNewsBean);
    void reqFail(String msg);
}
