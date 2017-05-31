package com.zyx.baby.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.orhanobut.logger.Logger;
import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragment2;
import com.zyx.baby.event.MessageEvent;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Administrator on 2017/2/3.
 */

public class PeeDayFragment extends BaseFragment2 {

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

    @BindView(R.id.chart)
    LineChartView chart;
    @BindView(R.id.selectDate)
    TextView selectDate;


    private LineChartData data;
    List<Line> lines;
    Line line;
    private int numberOfLines = 1; //折线条数
    private int numberOfPoints; //横轴天数对应的点数

    int[] score = {10, 5, 8, 13, 3, 1, 12, 18, 9, 10, 1, 1, 12, 18, 9, 10, 1};//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private List<AxisValue> mAxisYValues = new ArrayList<AxisValue>();

    MessageEvent messageEvent;

    Calendar calendar;
    int year, month, day;

    private final static String formatter = "yyyy-MM";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_pee_day, null);
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
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        selectDate.setText(TimeUtils.getDateString(year, day, month, "yyyy-MM"));

        getAxisXLables(year, month);
//        getAxisYLables();
        getAxisPoints();
        initLineChart();
        chart.setOnValueTouchListener(new ValueTouchListener());
    }


    public void initLineChart() {

        lines = new ArrayList<Line>();
        for (int i = 0; i < 1; ++i) {
            line = new Line(mPointValues).setColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));  //折线的颜色
            line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
            line.setCubic(false);//曲线是否平滑，即是曲线还是折线
            line.setFilled(false);//是否填充曲线的面积
            line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
            line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
            line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
            lines.add(line);
        }

        data = new LineChartData(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setName("日期");
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextSize(16);//设置字体大小
        axisX.setMaxLabelChars(12); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        axisX.setHasTiltedLabels(false);// 设置X轴文字向左旋转45度
        data.setAxisXBottom(axisX); //x 轴在底部
        axisX.setHasLines(true);// 是否显示X轴网格线
        //data.setAxisXTop(axisX);  //x 轴在顶部

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("次数");
        axisY.setTextSize(16);//设置字体大小
        axisY.setMaxLabelChars(2);
        //axisY.setValues(mAxisYValues);
        axisY.setHasLines(true);// 是否显示Y轴网格线
        data.setAxisYLeft(axisY);  //Y轴设置在左边

        //设置行为属性，支持缩放、滑动以及平移
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setInteractive(true);
        chart.setZoomType(ZoomType.HORIZONTAL);
        chart.setMaxZoom((float) 10);//最大方法比例
        chart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        chart.setLineChartData(data);
        chart.setVisibility(View.VISIBLE);

        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        resetViewport();
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables(int year, int month) {
        numberOfPoints = TimeUtils.getActualMaximum(year, month); //天数对应的点数
        for (int i = 1; i < numberOfPoints; i++) {
            mAxisXValues.add(new AxisValue(i - 1).setLabel(String.valueOf(month) + "-" + String.valueOf(i)));
        }
    }


    /**
     * 图表的每个点的显示
     */

    private void getAxisPoints() {
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));
        }

    }


    @OnClick({R.id.selectDate, R.id.ib_reduce, R.id.ib_add})
    public void click(View view) {
        String update = "";
        switch (view.getId()) {
            case R.id.ib_reduce:

                if (month <= 0) {
                    year -= 1;
                    month = 12;
                } else {
                    month -= 1;
                }
                update = TimeUtils.getDateString(year, day, month, formatter);
                postMessageEvent(update);
                break;
            case R.id.ib_add:
                if (month >= 12) {
                    year += 1;
                    month = 1;
                } else {
                    month += 1;
                }
                update = TimeUtils.getDateString(year, day, month, formatter);
                postMessageEvent(update);
                break;
            case R.id.selectDate:
               showDatePick();
                break;
            default:
                break;

        }
    }

    private void showDatePick() {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(mFragmentCallback);
        // Options
        Pair<Boolean, SublimeOptions> optionsPair = getOptions();
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
        pickerFrag.setArguments(bundle);
        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(getChildFragmentManager(), "SUBLIME_PICKER_DAY");
    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 1;
        v.top = 31;
        v.left = 0;
        v.right = numberOfPoints;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }


    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {

            String message =  month + "月" + (int) value.getX() + "日尿了" + (int) value.getY() + "次";
            Snackbar snackbar = Snackbar.make(mainLayout,
                    message, Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
            snackbar.show();
            LSUtils.showToast(getActivity(), "Selected: " + value);
            Logger.e("点击" + value);
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

    public void postMessageEvent(String message) {
        messageEvent = new MessageEvent();
        messageEvent.setTag("update");
        messageEvent.setObj(message);
        EventBus.getDefault().post(messageEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(MessageEvent event) {
        if(TextUtils.equals(event.getTag(),"update")){
            selectDate.setText(event.getObj().toString());
        }

    }




    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {
        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate,
                                            int hourOfDay, int minute,
                                            SublimeRecurrencePicker.RecurrenceOption recurrenceOption,
                                            String recurrenceRule) {
            Date date = selectedDate.getFirstDate().getTime();
            String chooseDate = TimeUtils.timeStamp2Date(date.getTime()+"", formatter);
            postMessageEvent(chooseDate);

        }
    };


    // Validates & returns SublimePicker options
    Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = 0;

        displayOptions |= SublimeOptions.ACTIVATE_DATE_PICKER;

        options.setDisplayOptions(displayOptions);

        // Enable/disable the date range selection feature

        return new Pair<>(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
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
