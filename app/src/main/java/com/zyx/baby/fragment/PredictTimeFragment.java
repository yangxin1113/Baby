package com.zyx.baby.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragment2;
import com.zyx.baby.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by Administrator on 2017/2/3.
 */

public class PredictTimeFragment extends BaseFragment2 {

    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    private View view;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;
    @BindView(R.id.chart_predict)
    ColumnChartView chartPredict;

//    String[] time = {"00:00-00:20", "00:20-00:40", "00:40-01:00", "01:00-00:20", "01:20-01:40", "01:40-02:00" };//图表的数据点
    String[] time ;//图表的数据点
    String  timetemp = "";//图表的数据点 2017-02-04 00:00:00
    private ColumnChartData columnData;

    MessageEvent messageEvent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_predict_time, null);
            ButterKnife.bind(this, view);
            initData();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    private void initData() {
        generateColumnData();

    }


    private void generateColumnData() {

        int numSubcolumns = 1;
        time = getResources().getStringArray(R.array.time_array);
        int numColumns = time.length;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();

        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 5f + 5, ChartUtils.pickColor()));
//                values.add(new SubcolumnValue((float) Math.random() * 5f + 5, ContextCompat.getColor(getActivity(), R.color.colorPrimary)));

            }

            axisValues.add(new AxisValue(i).setLabel(time[i]));
            Column column= new Column();//将值设置给折线
            column.setHasLabels(true);// 是否显示节点数据
//            column.setHasLabelsOnlyForSelected(true);
            columns.add(column.setValues(values));
        }

        columnData = new ColumnChartData(columns);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setName("日期");
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextSize(16);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(axisValues);  //填充X轴的坐标名称
        axisX.setHasTiltedLabels(false);// 设置X轴文字向左旋转45度
        columnData.setAxisXBottom(axisX); //x 轴在底部
        axisX.setHasLines(true);// 是否显示X轴网格线
        //data.setAxisXTop(axisX);  //x 轴在顶部

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("次数");
        axisY.setTextSize(16);//设置字体大小
        //axisY.setValues(mAxisYValues);
        axisY.setHasLines(true);// 是否显示Y轴网格线
        columnData.setAxisYLeft(axisY);  //Y轴设置在左边

//        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
//        columnData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));


        chartPredict.setColumnChartData(columnData);

        // Set value touch listener that will trigger changes for chartTop.
        chartPredict.setOnValueTouchListener(new ValueTouchListener());

        // Set selection mode to keep selected month column highlighted.
        chartPredict.setValueSelectionEnabled(true);

        chartPredict.setZoomType(ZoomType.HORIZONTAL);

        resetViewport();

    }


    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chartPredict.getMaximumViewport());
//        v.top = 31;
        v.right = 10;
        chartPredict.setCurrentViewport(v);
    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int i, int i1, SubcolumnValue subcolumnValue) {
            String message = "您的宝宝：在" + time[i] + "时间段尿了" + (int)subcolumnValue.getValue() + "次";
            Snackbar snackbar = Snackbar.make(mainLayout,
                    message, Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor( subcolumnValue.getColor());
            snackbar.show();
        }
    }

    public void postMessageEvent(String message) {
        messageEvent = new MessageEvent();
        messageEvent.setTag("update1");
        messageEvent.setObj(message);
        EventBus.getDefault().post(messageEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(MessageEvent event) {
        if(TextUtils.equals(event.getTag(),"update1")){
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void lazyLoad() {

    }
}
