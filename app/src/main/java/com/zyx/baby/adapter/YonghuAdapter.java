package com.zyx.baby.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zyx.baby.R;
import com.zyx.baby.bean.YonghuData;
import com.zyx.baby.callback.GuanzhuYonghuClickListenner;


import java.util.List;

import static android.view.View.OnClickListener;

/**
 * 我关注的用户
 * Created by Administrator on 2016/8/30 0030.
 */
public class YonghuAdapter extends RecyclerView.Adapter<YonghuAdapter.MyViewHolder> {
    private Context context;
    private List<YonghuData> data;
    private GuanzhuYonghuClickListenner guanzhuYonghuClickListenner;

    public YonghuAdapter(Context context, List<YonghuData> data){
        this.context = context;
        this.data = data;

    }

    public void setGuanzhuYonghuClickListenner(GuanzhuYonghuClickListenner guanzhuYonghuClickListenner){
        this.guanzhuYonghuClickListenner = guanzhuYonghuClickListenner;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context
        ).inflate(R.layout.item_yonghu, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {

        Picasso.with(context).load(data.get(i).getAvator()).into(holder.iv_head);
        holder.iv_head.setTag(i);
        holder.iv_head.setOnClickListener(mOnClickListener);
        holder.tv_nick.setText(data.get(i).getName());
        holder.tv_nick.setTag(i);
        holder.tv_nick.setOnClickListener(mOnClickListener);
        holder.tv_qianming.setText(data.get(i).getSignature());
        holder.tv_qianming.setTag(i);
        holder.tv_qianming.setOnClickListener(mOnClickListener);
        if (data.get(i).getIs_cancel() == 0){
            holder.ll_guanzhu.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_gaunzhu0));
            holder.iv_guanzhu.setVisibility(View.GONE);
            holder.tv_guanzhu.setText("已关注");
        }else {
            holder.ll_guanzhu.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_gaunzhu1));
            holder.iv_guanzhu.setVisibility(View.VISIBLE);
            holder.tv_guanzhu.setText("关注");
        }
        final int isGuanzhu =data.get(i).getIs_cancel();
        holder.ll_guanzhu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGuanzhu == 0){
                    holder.ll_guanzhu.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_gaunzhu1));
                    holder.iv_guanzhu.setVisibility(View.VISIBLE);
                    holder.tv_guanzhu.setText("关注");
                    data.get(i).setIs_cancel(1);
                }else {
                    holder.ll_guanzhu.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_gaunzhu0));
                    holder.iv_guanzhu.setVisibility(View.GONE);
                    holder.tv_guanzhu.setText("已关注");
                    data.get(i).setIs_cancel(0);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_head;
        private TextView tv_nick;
        private TextView tv_qianming;
        private ImageView iv_guanzhu;
        private TextView tv_guanzhu;
        private LinearLayout ll_guanzhu;
        public MyViewHolder(View itemView) {
            super(itemView);
            if(itemView != null){
                iv_head = (ImageView) itemView.findViewById(R.id.iv_head);
                tv_nick = (TextView) itemView.findViewById(R.id.tv_nick);
                tv_qianming = (TextView) itemView.findViewById(R.id.tv_qianming);
                iv_guanzhu = (ImageView) itemView.findViewById(R.id.iv_guanzhu);
                tv_guanzhu = (TextView) itemView.findViewById(R.id.tv_guanzhu);
                ll_guanzhu = (LinearLayout) itemView.findViewById(R.id.ll_guanzhu);
            }
        }
    }


    private  OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (guanzhuYonghuClickListenner != null) {
                int position = (Integer) v.getTag();
                switch (v.getId()){

                    case R.id.iv_head:
                        //头像
                        guanzhuYonghuClickListenner.showHead(v, position);
                        break;
                    case R.id.tv_nick:
                        //昵称
                        guanzhuYonghuClickListenner.showNick(v, position);
                        break;
                    case R.id.tv_qianming:
                        //签名
                        guanzhuYonghuClickListenner.showQianming(v, position);
                        break;
                    /*case R.id.ll_guanzhu:
                        //是否关注
                        guanzhuYonghuClickListenner.showIsGuanzhu(v, position);
                        break;*/

                }

            }
        }
    };

}
