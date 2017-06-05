package com.zyx.baby.presenters.viewinface;

import com.zyx.baby.bean.DrugDetailBean;
import com.zyx.baby.bean.KnowledgeDetail;

/**
 * Created by zyx on 2017/5/29.
 */

public interface DrugDetailView {
    void reqSucc(DrugDetailBean drugDetailBean);
    void reqFail(String msg);
}
