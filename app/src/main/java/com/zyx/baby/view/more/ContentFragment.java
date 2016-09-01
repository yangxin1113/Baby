package com.zyx.baby.view.more;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.zyx.baby.R;
import com.zyx.baby.adapter.SearchContentAdapter;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.bean.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.support.v7.widget.RecyclerView.OnScrollListener;
import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class ContentFragment extends BaseFragment {

    @BindView(R.id.fresh_content)
    SwipeRefreshLayout fresh_content;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    private SearchContentAdapter searchContentAdapter;
    private List<Question> questions;
    private int lastVisibleItem ;
    private LinearLayoutManager mLayoutManager;
    private int page =1;

    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_content);
    }

    @Override
    protected void initEvent() {
        fresh_content.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData(10);
            }
        });

        //recyclerview滚动监听实现自动加载
        rv_content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；

                    setData(18);

            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }

    @Override
    protected void setInitData() {
        mLayoutManager = new LinearLayoutManager(getActivity(),VERTICAL,false);
        rv_content.setLayoutManager(mLayoutManager);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        fresh_content.setProgressViewOffset(false, 0
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                        , 24, getResources().getDisplayMetrics()));
        setData(10);
    }

    @Override
    public void onClick(View v) {

    }


    private void setData(int page) {
        fresh_content.setRefreshing(true);
        questions = new ArrayList<Question>();
        for (int i = 1; i <= page; i++) {
            Question question = new Question(i, i + "脐带脱落后可以立即给婴儿洗澡吗？", i * 2, i * 3);
            questions.add(question);
        }
        if(searchContentAdapter==null){
            rv_content.setAdapter(searchContentAdapter = new SearchContentAdapter(getActivity(),questions));

        }else{
            searchContentAdapter.notifyDataSetChanged();
        }

        fresh_content.setRefreshing(false);
    }
}
