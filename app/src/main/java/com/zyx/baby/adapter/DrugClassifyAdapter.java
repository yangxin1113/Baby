package com.zyx.baby.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.bean.DrugClassifyBean;
import com.zyx.baby.bean.Question;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class DrugClassifyAdapter extends RecyclerView.Adapter<DrugClassifyAdapter.MyViewHolder> {

    private Context context;
    private List<DrugClassifyBean.TngouBean> mData;
    private OnClickListener mOnClickListener;
    private int selectPosition = 0;
    public DrugClassifyAdapter(Context context , List<DrugClassifyBean.TngouBean> classify, OnClickListener clickListener){
        this.context = context;
        this.mData = classify;
        this.mOnClickListener = clickListener;
    }

    public interface OnClickListener{
        void click(int position);
    }

    public void selectItem(int postion){
        selectPosition = postion;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_text, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if(selectPosition == position){
            holder.tv_classify.setBackgroundColor(context.getResources().getColor(R.color.colorTheme));
            holder.tv_classify.setTextColor(context.getResources().getColor(R.color.white));
        }else {
            holder.tv_classify.setBackgroundResource(R.color.white);
            holder.tv_classify.setTextColor(context.getResources().getColor(R.color.color_black1));
        }
        holder.tv_classify.setText(mData.get(position).getName());
        if(mOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.click(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv_classify;

        public MyViewHolder(View view)
        {
            super(view);
            tv_classify = (TextView) view.findViewById(R.id.classify);
        }
    }
}
