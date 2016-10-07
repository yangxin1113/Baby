package com.zyx.baby.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zyx.baby.R;
import com.zyx.baby.bean.CaogaoData;
import com.zyx.baby.callback.CaogaoClickListenner;

import java.util.List;

import static android.view.View.OnClickListener;

/**
 * 我的草稿
 * Created by Administrator on 2016/8/30 0030.
 */
public class CaogaoAdapter extends RecyclerView.Adapter<CaogaoAdapter.MyViewHolder> {
    private Context context;
    private List<CaogaoData> data;
    private CaogaoClickListenner caogaoClickListenner;

    public CaogaoAdapter(Context context, List<CaogaoData> data){
        this.context = context;
        this.data = data;

    }

    public void setCaogaoClickListenner(CaogaoClickListenner caogaoClickListenner){
        this.caogaoClickListenner = caogaoClickListenner;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_caogao, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {
        holder.tv_question.setText(data.get(i).getQuestion());
        holder.tv_question.setTag(i);
        holder.tv_question.setOnClickListener(mOnClickListener);
        holder.tv_content.setText(data.get(i).getContent());
        holder.tv_content.setTag(i);
        holder.tv_content.setOnClickListener(mOnClickListener);
        holder.ll_shanchu.setTag(i);
        holder.ll_shanchu.setOnClickListener(mOnClickListener);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_question;
        private TextView tv_content;
        private LinearLayout ll_shanchu;

        public MyViewHolder(View itemView) {
            super(itemView);
            if(itemView != null){
                tv_question = (TextView) itemView.findViewById(R.id.tv_question);
                tv_content = (TextView) itemView.findViewById(R.id.tv_content);
                ll_shanchu = (LinearLayout) itemView.findViewById(R.id.ll_shanchu);

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
