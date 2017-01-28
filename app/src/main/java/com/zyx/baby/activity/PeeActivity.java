package com.zyx.baby.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.orhanobut.logger.Logger;
import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;
import lecho.lib.hellocharts.model.*;
import lecho.lib.hellocharts.util.ChartUtils;
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

public class PeeActivity extends BaseActivity {

    @BindView(R.id.chart)
    LineChartView chart;
    @BindView(R.id.selectDate)
    TextView selectDate;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;

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
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        // Generate some random values.
        generateValues();

        generateData();
        chart.setViewportCalculationEnabled(false);

        resetViewport();

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

                DatePickerDialog dd = new DatePickerDialog(PeeActivity.this,
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

    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 80f;
            }
        }
    }


    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(Color.RED);
            line.setShape(shape);//折线图上每个数据点的形状
            line.setCubic(isCubic);//曲线是否平滑
            line.setFilled(isFilled);//是否填充曲线的面积
            line.setHasLabels(hasLabels);//曲线的数据坐标是否加上备注
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);//点击数据坐标提示数据
            line.setHasLines(hasLines);//是否用直线显示。如果为false 则没有曲线只有点显示
            line.setHasPoints(hasPoints);//是否显示圆点 如果为false 则没有原点只有点显示
            //line.setHasGradientToTransparent(hasGradientToTransparent);
            if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);
        data.setValueLabelBackgroundColor(Color.RED);//此处设置坐标点旁边的文字背景
        data.setValueLabelsTextColor(Color.GREEN);//此处设置坐标点旁边的文字颜色
        data.setValueLabelTextSize(8);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("日期");
                axisY.setName("次数");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);//设置y轴在表的左边
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);

    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 80;
        v.left = 0;
        v.right = numberOfPoints - 1;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
                break;
        }
        return true;
    }
}
