package com.zyx.baby.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragment2;
import com.zyx.baby.event.MessageEvent;
import com.zyx.baby.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Administrator on 2017/2/3.
 */

public class DiapersFragment extends BaseFragment2 {

    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;
    @BindView(R.id.chart_bottom)
    ColumnChartView chartBottom;
    private View view;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;


    public   String[] months ;


    private final static String formatter = "yyyy--";
    SimpleDateFormat sf;

    private LineChartData lineData;
    private ColumnChartData columnData;

    MessageEvent messageEvent;

    Calendar calendar;
    int year, month, day;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_diapers, null);
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
        months = new String[7];
        for(int i = 0; i<7; i++){
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            sf =  new SimpleDateFormat("MM-dd");
            months[i] = sf.format(calendar.getTime());
        }
        generateColumnData();
    }


    private void generateColumnData() {

        int numSubcolumns = 1;
        int numColumns = months.length;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();

        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float)(Math.random()*5), ChartUtils.pickColor()));
            }

            axisValues.add(new AxisValue(i).setLabel(months[6-i]));
            Column column= new Column();//将值设置给折线
            column.setHasLabels(true);// 是否显示节点数据
//            column.setHasLabelsOnlyForSelected(true);
            columns.add(column.setValues(values));
        }

        columnData = new ColumnChartData(columns);

        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
//        columnData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));


        chartBottom.setColumnChartData(columnData);

        // Set value touch listener that will trigger changes for chartTop.
        chartBottom.setOnValueTouchListener(new ValueTouchListener());

        // Set selection mode to keep selected month column highlighted.
        chartBottom.setValueSelectionEnabled(true);

        chartBottom.setZoomType(ZoomType.HORIZONTAL);

    }



    @OnClick({ R.id.ib_reduce1, R.id.ib_add1})
    public void click(View view) {
        String update = "";
        switch (view.getId()) {
            case R.id.ib_reduce1:
                year -= 1;
                update = TimeUtils.getDateString(year, day, month, formatter);
                postMessageEvent(update);
                break;
            case R.id.ib_add1:
                year += 1;
                update = TimeUtils.getDateString(year, day, month, formatter);
                postMessageEvent(update);
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
        pickerFrag.show(getChildFragmentManager(), "SUBLIME_PICKER_WEEK");
    }

    /**
     * ui更新
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(MessageEvent event) {
        if(TextUtils.equals(event.getTag(),"diapers")){

        }
    }

    public void postMessageEvent(String message) {
        messageEvent = new MessageEvent();
        messageEvent.setTag("diapers");
        messageEvent.setObj(message);
        EventBus.getDefault().post(messageEvent);
    }

    @Override
    protected void lazyLoad() {

    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {

        }

        @Override
        public void onValueDeselected() {


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
}
