package com.zyx.baby.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zyx.baby.R;
import com.zyx.baby.base.BaseHeaderActivity;
import com.zyx.baby.bean.DrugDetailBean;
import com.zyx.baby.bean.DrugListBean;
import com.zyx.baby.databinding.ActivityDrugDetailBinding;
import com.zyx.baby.databinding.HeaderSlideShapeBinding;
import com.zyx.baby.http.ApisUtil;
import com.zyx.baby.presenters.DrugDetailHelper;
import com.zyx.baby.presenters.viewinface.DrugDetailView;
import com.zyx.baby.utils.PerfectClickListener;
import com.zyx.baby.utils.UserInfoUtils;
import com.zyx.baby.widget.TagCloudView;

import net.nightwhistler.htmlspanner.HtmlSpanner;
import net.nightwhistler.htmlspanner.LinkMovementMethodExt;
import net.nightwhistler.htmlspanner.MyImageSpan;

import java.util.ArrayList;
import java.util.Arrays;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Administrator on 2016/12/23.
 * 养身详情
 */


/**
 * （已可以使用：OneMovieDetailActivity.java 替代）
 * 思路：
 * 1、透明状态栏（透明titlebar,使背景图上移）
 * 2、titlebar底部增加和背景一样的高斯模糊图，并上移图片的高度-titlebar的高度（为了使背景图的底部作为titlebar的背景）
 * 3、上移，通过scrollview拿到上移的高度，同时（在背景图的高度内） 调整titlebar的颜色使透明变为不透明，调整背景图的颜色，是不透明变为透明
 * 4、下拉，使上面反过来即可
 */
public class DrugDetailActivity extends BaseHeaderActivity<HeaderSlideShapeBinding,ActivityDrugDetailBinding> implements DrugDetailView{

    private String TAG = "---DrugDetailActivity:";
    // 影片背景图片
    private DrugListBean.TngouBean bean;

    private DrugDetailHelper mDrugDetailHelper;
    HtmlSpanner htmlSpanner;
    ArrayList<String> imglist;
    private String img_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        if (getIntent() != null) {
            bean = (DrugListBean.TngouBean) getIntent().getSerializableExtra("bean");
        }
        initPresenter();
        initClick();

        initSlideShapeTheme(setHeaderImgUrl(), setHeaderImageView());
        setTitle(bean.getName());
        if(bean.getImg().contains("http")){
            img_url = bean.getImg();
        }else {
            img_url = ApisUtil.BASE_URL_IMG+bean.getImg();

        }
        Glide.with(getApplicationContext())
                .load(img_url)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .crossFade(500)
                .bitmapTransform(new BlurTransformation(getApplicationContext(), 23, 4))
                .into(bindingHeaderView.imgItemBg);

        bindingHeaderView.tvSmallContent.setText(bean.getDescription());

    }

    private void initClick() {
        bindingHeaderView.ivFavorite.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if(!UserInfoUtils.getBoolean(getApplicationContext(), "isFavorite", false)){
                    bindingHeaderView.ivFavorite.setImageResource(R.mipmap.icon_collect);
                    UserInfoUtils.putBoolean(getApplicationContext(), "isFavorite", true);
                }else {
                    bindingHeaderView.ivFavorite.setImageResource(R.mipmap.icon_uncollect);
                    UserInfoUtils.putBoolean(getApplicationContext(), "isFavorite", false);
                }
            }
        });
    }


    @Override
    protected int setHeaderLayout() {
        return R.layout.header_slide_shape;
    }

    @Override
    protected String setHeaderImgUrl() {
        if (bean == null) {
            return "";
        }
        return img_url;
    }

    @Override
    protected ImageView setHeaderImageView() {
        return bindingHeaderView.imgItemBg;
    }


    @Override
    protected void onRefresh() {
        mDrugDetailHelper.reqNews(bean.getId());
    }


    private void initPresenter() {
        mDrugDetailHelper = new DrugDetailHelper(getApplicationContext(), this);
        mDrugDetailHelper.reqNews(bean.getId());
        imglist = new ArrayList<>();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        htmlSpanner = new HtmlSpanner(this, dm.widthPixels, handler);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }


/*
    *
     * @param context      activity
     * @param positionData bean
     * @param imageView    imageView
*/

    public static void start(Activity context, DrugListBean.TngouBean contentList, ImageView imageView) {
        Intent intent = new Intent(context, DrugDetailActivity.class);
        intent.putExtra("bean", contentList);
        /*ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        imageView, CommonUtils.getString(R.string.transition_movie_img));//与xml文件对应*/
        context.startActivity(intent);
//        ActivityCompat.startActivity(context, intent, options.toBundle());
    }



    @Override
    public void reqSucc(final DrugDetailBean drugDetailBean) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Spannable spannable = htmlSpanner.fromHtml(drugDetailBean.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bindingContentView.tvContent.setText(spannable);
                        bindingContentView.tvContent.setMovementMethod(LinkMovementMethodExt.getInstance(handler, ImageSpan.class));
                        showContentView();
                    }
                });
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String [] keyWords = drugDetailBean.getKeywords().split("\\s+");
                DrugDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter(keyWords);
                    }
                });
            }
        }).start();
    }

    @Override
    public void reqFail(String msg) {
        showError();
    }


    /**
     * 关键字
     */
    private void setAdapter(final String [] keyWords) {

        bindingContentView.tagCloudView.setTags(Arrays.asList(keyWords));
        bindingContentView.tagCloudView.setOnTagClickListener(new TagCloudView.OnTagClickListener() {
            @Override
            public void onTagClick(int position) {
                KeywordActivity.start(DrugDetailActivity.this, keyWords[position], "drug");
            }
        });
    }

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1://获取图片路径列表
                    String url = (String) msg.obj;
                    Log.e("jj", "url>>" + url);
                    imglist.add(url);
                    break;
                case 2://图片点击事件
                    int position=0;
                    MyImageSpan span = (MyImageSpan) msg.obj;
                    for (int i = 0; i < imglist.size(); i++) {
                        if (span.getUrl().equals(imglist.get(i))) {
                            position = i;
                            break;
                        }
                    }
                    Log.e("jj","position>>"+position);
                    Intent intent=new Intent(DrugDetailActivity.this,ImgPreviewActivity.class);
                    Bundle b=new Bundle();
                    b.putInt("position",position);
                    b.putStringArrayList("imglist",imglist);
                    intent.putExtra("b",b);
                    startActivity(intent);
                    break;
            }
        }

        ;
    };

}
