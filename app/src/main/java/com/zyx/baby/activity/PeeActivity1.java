package com.zyx.baby.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.orhanobut.logger.Logger;
import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.utils.TimeUtils;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.*;
import lecho.lib.hellocharts.view.LineChartView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zyx on 2017/1/28.
 * 尿尿统计
 */

public class PeeActivity1 extends BaseActivity {

    @BindView(R.id.chart)
    LineChartView chart;
    @BindView(R.id.selectDate)
    TextView selectDate;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private LineChartData data;
    private int numberOfLines ; //折线图条数
    private int maxNumberOfLines; //竖轴间距
    private int numberOfPoints ; //横轴天数对应的点数

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;
    private boolean hasGradientToTransparent = false;

    String[] date = {"10-22","11-22","12-22","1-22","6-22","5-23","5-22","6-22","5-23","5-22"};//X轴的标注
    int[] score= {10,5,8,13,3,1,12,18,9,10};//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private List<AxisValue> mAxisYValues = new ArrayList<AxisValue>();

    Calendar calendar;
    int year, month, day;

    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_pee);

    }

    @Override
    protected void setInitData() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar =  getSupportActionBar();
            if(actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
            }
            //toolbar.setNavigationIcon(R.drawable.icon_back);
            toolbar.setTitle("尿尿统计");
        }
        getAxisXLables();
        getAxisYLables();
        getAxisPoints();
        initLineChart();


    }

    public void initLineChart(){
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        numberOfLines = 1;
        maxNumberOfLines = 1;
        numberOfPoints = TimeUtils.getActualMaximum(year, month); //天数对应的点数
        randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

        Line line = new Line(mPointValues).setColor(getColorPrimary());  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setName("日期");
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextSize(12);//设置字体大小
        axisX.setMaxLabelChars(10); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        axisX.setHasTiltedLabels(false);// 设置X轴文字向左旋转45度
        data.setAxisXBottom(axisX); //x 轴在底部
        axisX.setHasLines(true);// 是否显示X轴网格线
        //data.setAxisXTop(axisX);  //x 轴在顶部

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("次数");
        axisY.setTextSize(12);//设置字体大小
        axisY.setMaxLabelChars(1);
        axisY.setValues(mAxisYValues);
        axisY.setHasLines(true);// 是否显示Y轴网格线
        data.setAxisYLeft(axisY);  //Y轴设置在左边

        //设置行为属性，支持缩放、滑动以及平移
        chart.setInteractive(true);
        chart.setZoomType(ZoomType.HORIZONTAL);
        chart.setMaxZoom((float) 2);//最大方法比例
        chart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        chart.setLineChartData(data);
        chart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        resetViewport();
//        Viewport v = new Viewport(chart.getMaximumViewport());
//        v.bottom = 1;
//        v.top = 30;
//        v.left = 0;
//        v.right= 7;
//        chart.setCurrentViewport(v);
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables(){
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }

    /**
     * 设置Y 轴的显示
     */
    private void getAxisYLables(){



        for (int j = 0; j < 30; j++) {//循环为节点、X、Y轴添加数据
            mAxisYValues.add(new AxisValue(j).setLabel(j+""));// 添加Y轴显示的刻度值
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

        @Override
    protected void initEvent() {

    }

    @OnClick({ R.id.selectDate})
    public void click(View view) {
        switch (view.getId()) {
            /*case R.id.iv_left:
                onKeyDown(KeyEvent.KEYCODE_BACK, null);
                break;
            case R.id.iv_right:
                break;*/
            case R.id.selectDate:

                DatePickerDialog dd = new DatePickerDialog(PeeActivity1.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                try {
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                    String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                    Date date = formatter.parse(dateInString);
                                    formatter = new SimpleDateFormat("yyyy-MM");
                                    Logger.e(formatter.format(date).toString());
                                    selectDate.setText(formatter.format(date).toString());

                                } catch (Exception ex) {

                                }


                            }
                        }, year, month, day);
                dd.getDatePicker().setCalendarViewShown(false);
                dd.show();


                break;
            default:
                break;

        }
    }





    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 1;
        v.top = 30;
        v.left = 0;
        v.right = 7;
        //chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.line_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                break;
        }
        return true;
    }
}
