package com.zyx.baby.presenters.viewinface;

import com.zyx.baby.bean.DrugClassifyBean;
import com.zyx.baby.bean.DrugListBean;

/**
 * Created by zyx on 2017/5/29.
 */

public interface DrugClassifyView {
    void reqSucc(DrugClassifyBean drugClassifyBean);
    void reqFail(String msg);
    void reqDrugs(DrugListBean drugListBean);
}
