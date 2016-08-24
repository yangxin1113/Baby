package com.zyx.baby.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.zyx.baby.utils.InputUtils;
import com.zyx.baby.utils.LSUtils;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class SearchView extends CloudEditText {
    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean checkInputSpan(String showText, String returnText) {
       /*if(returnText.contains("@")){
            boolean result = InputUtils.getInstance().isEmail(returnText);
            if(!result){
                LSUtils.showToast(getContext(), "请输入一个邮箱");
            }
            return result;
        }else{
            if(returnText.contains("+")){
                LSUtils.showToast(getContext(), "手机号前请不要加区号");
                return false;
            }else{
                boolean result = InputUtils.getInstance().isMobileNO(returnText);
                if(!result){
                    LSUtils.showToast(getContext(), "请输入一个手机号");
                }
                return result;
            }
        }*/
        return true;
    }
}
