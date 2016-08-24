package com.zyx.baby.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moxun.tagcloudlib.view.TagsAdapter;
import com.zyx.baby.utils.LSUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.View.OnClickListener;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class TextTagsAdapter extends TagsAdapter {

    private List<String> dataSet = new ArrayList<String>();
    private MyOnClickLisenenr myOnClickLisenenr;

    public TextTagsAdapter( MyOnClickLisenenr clickLisenenr, String... data){
        dataSet.clear();
        this.myOnClickLisenenr = clickLisenenr;
        Collections.addAll(dataSet, data);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public View getView(final Context context, final int position, final ViewGroup viewGroup) {
        TextView tv = new TextView(context);
        tv.setText(dataSet.get(position));
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(myOnClickLisenenr);
        tv.setTag(position);
        tv.setTextColor(Color.WHITE);
        return tv;
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        view.setBackgroundColor(themeColor);
    }


    public static abstract class MyOnClickLisenenr implements OnClickListener{
        @Override
        public void onClick(View v) {
            myOnClick((Integer)v.getTag(), v);
        }
        public abstract void myOnClick(int position, View v);
    }
}
