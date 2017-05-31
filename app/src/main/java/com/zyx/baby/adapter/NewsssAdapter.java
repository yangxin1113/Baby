package com.zyx.baby.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.zyx.baby.R;
import com.zyx.baby.bean.CaogaoData;
import com.zyx.baby.bean.KnowledgeList;
import com.zyx.baby.callback.CaogaoClickListenner;
import com.zyx.baby.http.ApisUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import static android.view.View.OnClickListener;

/**
 * 我的草稿
 * Created by Administrator on 2016/8/30 0030.
 */
public class NewsssAdapter extends RecyclerView.Adapter<NewsssAdapter.MyViewHolder> {
    private Context context;
    private List<KnowledgeList.KnowledgeSummary> data;
    private CaogaoClickListenner caogaoClickListenner;
    SimpleDateFormat mFormat = new SimpleDateFormat("yy-MM-dd HH:mm");


    public NewsssAdapter(Context context, List<KnowledgeList.KnowledgeSummary> data){
        this.context = context;
        this.data = data;

    }

    public void setCaogaoClickListenner(CaogaoClickListenner caogaoClickListenner){
        this.caogaoClickListenner = caogaoClickListenner;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent,
                false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {
        Picasso.with(context).load(ApisUtil.BASE_URL_IMG+data.get(i).getImg()).into(holder.iv_img);
        holder.tv_title.setText(data.get(i).getTitle());
        holder.tv_desc.setText(data.get(i).getDescription());
        holder.tv_time.setText(mFormat.format(data.get(i).getTime()));
        holder.tv_rcount.setText(mFormat.format(data.get(i).getCount()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_img;
        private TextView tv_title;
        private TextView tv_desc;
        private TextView tv_time;
        private TextView tv_rcount;

        public MyViewHolder(View itemView) {
            super(itemView);
            if(itemView != null){
                iv_img = (ImageView) itemView.findViewById(R.id.iv_one_photo);
                tv_title = (TextView) itemView.findViewById(R.id.title);
                tv_desc = (TextView) itemView.findViewById(R.id.desc);
                tv_time = (TextView) itemView.findViewById(R.id.pubDate);
                tv_rcount = (TextView) itemView.findViewById(R.id.source);

            }
        }
    }


    private  OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (caogaoClickListenner != null) {
                int position = (Integer) v.getTag();
                switch (v.getId()){

                    case R.id.tv_question:
                        //问题
                        caogaoClickListenner.showQuestion(v, position);
                        break;
                    case R.id.tv_content:
                        //内容
                        caogaoClickListenner.showContent(v, position);
                        break;
                    case R.id.ll_shanchu:
                        //删除
                        caogaoClickListenner.showDelete(v, position);
                        break;

                }

            }
        }
    };

}
