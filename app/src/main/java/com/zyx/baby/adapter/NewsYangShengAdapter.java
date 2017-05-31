package com.zyx.baby.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.squareup.picasso.Picasso;
import com.zyx.baby.R;
import com.zyx.baby.activity.KnowDetailActivity;
import com.zyx.baby.activity.WebActivity;
import com.zyx.baby.bean.KnowledgeList;
import com.zyx.baby.bean.NewsModel;
import com.zyx.baby.http.ApisUtil;
import com.zyx.baby.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/4 0004.
 */

public class NewsYangShengAdapter extends BaseQuickAdapter<KnowledgeList.KnowledgeSummary> {
    SimpleDateFormat mFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

    public NewsYangShengAdapter(List<KnowledgeList.KnowledgeSummary> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final KnowledgeList.KnowledgeSummary contentList) {
        baseViewHolder.setText(R.id.title, contentList.getTitle())//
                .setText(R.id.desc, contentList.getDescription())//
                .setText(R.id.pubDate, mFormat.format(contentList.getTime()))//
                .setText(R.id.source, "阅读  "+contentList.getCount()+"");

        final View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KnowDetailActivity.start((Activity) mContext, contentList, (ImageView) baseViewHolder.getView(R.id.iv_one_photo));
//                WebActivity.runActivity(mContext, contentList.title, contentList.link);
            }
        });

        Picasso.with(mContext).load(ApisUtil.BASE_URL_IMG+contentList.getImg()).placeholder(R.mipmap.loading).error(R.mipmap.loading).into((ImageView) baseViewHolder.getView(R.id.iv_one_photo));


       /* NineGridView nineGrid = baseViewHolder.getView(R.id.nineGrid);
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        String image = contentList.getImg();
        ImageInfo info = new ImageInfo();
        info.setThumbnailUrl(ApisUtil.BASE_URL_IMG+image);
        info.setBigImageUrl(ApisUtil.BASE_URL_IMG+image);
        imageInfo.add(info);

        nineGrid.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));

            nineGrid.setSingleImageRatio(image.get(0).width * 1.0f / image.get(0).height);
    */}
}