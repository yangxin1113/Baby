package com.zyx.baby.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.zyx.baby.R;
import com.zyx.baby.activity.DrugDetailActivity;
import com.zyx.baby.activity.KnowDetailActivity;
import com.zyx.baby.bean.DrugListBean;
import com.zyx.baby.bean.KnowledgeList;
import com.zyx.baby.bean.SearchNewsBean;
import com.zyx.baby.http.ApisUtil;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/12/4 0004.
 */

public class DrugsItemAdapter extends BaseQuickAdapter<DrugListBean.TngouBean> {
    SimpleDateFormat mFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

    public DrugsItemAdapter(List<DrugListBean.TngouBean> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final DrugListBean.TngouBean contentList) {
        baseViewHolder.setText(R.id.title, contentList.getName())//
                .setText(R.id.desc, contentList.getDescription())//
                .setText(R.id.pubDate, contentList.getType())//
                .setText(R.id.source, "参考价格"+contentList.getPrice()+"");

        final View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                KnowDetailActivity.start((Activity) mContext, beanAdapter(contentList), (ImageView) baseViewHolder.getView(R.id.iv_one_photo));
                DrugDetailActivity.start((Activity)mContext, contentList, (ImageView) baseViewHolder.getView(R.id.iv_one_photo));

            }
        });

        if(contentList.getImg().contains("http")){
            Picasso.with(mContext).load(contentList.getImg()).placeholder(R.mipmap.stackblur_default).error(R.mipmap.stackblur_default).into((ImageView) baseViewHolder.getView(R.id.iv_one_photo));
        }else {
            Picasso.with(mContext).load(ApisUtil.BASE_URL_IMG+contentList.getImg()).placeholder(R.mipmap.stackblur_default).error(R.mipmap.stackblur_default).into((ImageView) baseViewHolder.getView(R.id.iv_one_photo));
        }

       }

       public KnowledgeList.KnowledgeSummary beanAdapter(SearchNewsBean.TngouBean contentList){
           KnowledgeList.KnowledgeSummary bean = new KnowledgeList.KnowledgeSummary();
           bean.setId(contentList.getId());
           bean.setImg(contentList.getImg());
           bean.setTitle(contentList.getTitle());
           bean.setDescription(contentList.getDescription());
           return bean;
       }


}