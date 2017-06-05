package com.zyx.baby.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.bean.Question;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class DrugsAdapter extends RecyclerView.Adapter<DrugsAdapter.MyViewHolder> {

    private Context context;
    private List<Question> questions;

    public DrugsAdapter(Context context , List<Question> questions){
        this.context = context;
        this.questions = questions;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_search_content, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_que.setText(questions.get(position).getQuestion());
        holder.tv_ansCount.setText(String.valueOf(questions.get(position).getAnswerCount()));
        holder.tv_watcherCount.setText(String.valueOf(questions.get(position).getWatcherCount()));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv_que;
        TextView tv_ansCount;
        TextView tv_watcherCount;

        public MyViewHolder(View view)
        {
            super(view);
            tv_que = (TextView) view.findViewById(R.id.tv_que);
            tv_ansCount = (TextView) view.findViewById(R.id.tv_ansCount);
            tv_watcherCount = (TextView) view.findViewById(R.id.tv_watcherCount);
        }
    }
}
